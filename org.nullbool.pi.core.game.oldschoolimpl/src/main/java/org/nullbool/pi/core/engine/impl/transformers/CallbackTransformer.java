package org.nullbool.pi.core.engine.impl.transformers;

import java.util.Map;

import org.nullbool.pi.core.engine.api.transform.IAPIHelper;
import org.nullbool.pi.core.hook.api.ClassHook;
import org.nullbool.pi.core.hook.api.Constants;
import org.nullbool.pi.core.hook.api.HookMap;
import org.nullbool.pi.core.hook.api.MethodHook;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.topdank.banalysis.asm.insn.InstructionPrinter;

public class CallbackTransformer extends AbstractTransformer {

	public CallbackTransformer(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) {
		super(classes, hooks, helper);
	}

	@Override
	public void run(ClassNode cn) {

		// TODO: dynamic
		ClassHook callbackOwnerHook = hook;

		for(MethodHook mh : hook.methods()) {
			String type = mh.val(Constants.METHOD_TYPE);
			if(Constants.CALLBACK.equals(type))
				continue;

			String _desc = mh.val(Constants.DESC);
			String desc = DescUtil.convertMultiBytecodeStyle(hooks, helper, _desc);
			ClassNode originalMethodOwner = classes.get(mh.val(Constants.REAL_OWNER));
			ClassNode callbackOwner = classes.get(callbackOwnerHook.obfuscated());
			MethodNode method = new MethodNode(callbackOwner, ACC_PUBLIC, mh.refactored(), desc, null, null);
						
			InsnList insns = new InsnList();
			Type[] args = Type.getArgumentTypes(_desc);
			boolean isStatic = Boolean.valueOf(mh.val(Constants.STATIC));
			// int varOffset = isStatic ? 0 : 1;
			// int varOffset = 0; // caller is always virtual
			/*
			 * Here the var table is {0 = int1, 1 = int2}
			 * static void test(int int1, int int2){
			 * 
			 * } 
			 * 
			 * Here the var table is {1 = int1, 2 = int2}
			 * void test(int1, int2){
			 * 
			 * }   
			 * 
			 * Virtual methods are offsetted by +1.
			 */

			// for (int i = varOffset; i < (args.length + varOffset); i++) {
			// Type arg = args[i - varOffset];
			// int loadOpcode = GetterTransformer.getLoadOpcode(arg.getDescriptor());
			// insns.add(new VarInsnNode(loadOpcode, i));
			// }

			if (!isStatic) {
				insns.add(new VarInsnNode(ALOAD, 0));
				method.maxStack++;
				method.maxLocals++;
			}

			for (int i = 0; i < args.length; i++) {
				Type arg = args[i];
				int loadOpcode = DescUtil.getLoadOpcode(arg.getDescriptor());
				if(loadOpcode == -1) {
					System.err.println(arg.getDescriptor());
				}
				insns.add(new VarInsnNode(loadOpcode, i + 1));
				
				if(arg.getDescriptor().equals("J") || arg.getDescriptor().equals("D"))
					method.maxStack += 2;
				else
					method.maxStack += 1;
			}
			
			method.maxLocals += args.length;

			int callOpcode = isStatic ? INVOKESTATIC : INVOKEVIRTUAL;
			insns.add(new MethodInsnNode(callOpcode, originalMethodOwner.name, mh.obfuscated(), _desc, false));

			Type returnType = Type.getReturnType(desc);
			if (!returnType.getDescriptor().equals("V")) {
				insns.add(new InsnNode(DescUtil.getReturnOpcode(returnType.getDescriptor())));
			} else {
				insns.add(new InsnNode(RETURN));
			}

			method.instructions = insns;

			callbackOwner.methods.add(method);

			System.out.println("Callback: " + method.key() + "  ->  " + String.format("%s%s.%s%s", isStatic ? "static " : "", originalMethodOwner.name, mh.obfuscated(), _desc));
			InstructionPrinter.consolePrint(method);
		}
	}
}