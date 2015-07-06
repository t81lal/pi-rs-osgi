package org.nullbool.pi.core.engine.impl.transformers;

import java.util.Map;

import org.nullbool.pi.core.engine.api.transform.IAPIHelper;
import org.nullbool.pi.core.hook.api.Constants;
import org.nullbool.pi.core.hook.api.FieldHook;
import org.nullbool.pi.core.hook.api.HookMap;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class GetterTransformer extends AbstractTransformer {

	public GetterTransformer(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) {
		super(classes, hooks, helper);
	}

	@Override
	public void run(ClassNode cn) {
		System.out.println(hook.fields().size());
		
		for (FieldHook f : hook.fields()) {
			// The field owner may be different to the call owner
			// eg, Client.get.. returns Other.other;
			String desc = f.val(Constants.DESC);
			// String refactoredDesc =
			// DynamicDesc.convertSingleBytecodeStyle(getClasses(),
			// desc.getObfuscated());
			String refactoredDesc = DescUtil.convertSingleBytecodeStyle(hooks, helper, desc);
			MethodNode method = new MethodNode(classes.get(f.owner().obfuscated()), ACC_PUBLIC, f.refactored(), "()" + refactoredDesc, null, null);
			InsnList insns = new InsnList();
			boolean isStatic = Boolean.valueOf(f.val(Constants.STATIC));
			if (!isStatic) {
				insns.add(new VarInsnNode(ALOAD, 0));
			}
			int getOpcode = isStatic ? GETSTATIC : GETFIELD;
			insns.add(new FieldInsnNode(getOpcode, f.val(Constants.REAL_OWNER), f.obfuscated(), desc));

			// Casting is not necessary
			/*
			 * int arr = APIGenerator.array(refactoredDesc); String cleanedDesc
			 * = refactoredDesc.replace("[", ""); if (!isPrimitive(cleanedDesc))
			 * { if (arr == 0) { Type returnType =
			 * Type.getReturnType(cleanedDesc); String castType =
			 * returnType.getInternalName(); System.out.println(refactoredDesc +
			 * " -> " + castType); insns.add(new TypeInsnNode(CHECKCAST,
			 * castType)); } else { String castType = refactoredDesc;
			 * System.out.println(refactoredDesc + " -> " + castType);
			 * insns.add(new TypeInsnNode(CHECKCAST, castType)); } }
			 */

			insns.add(new InsnNode(DescUtil.getReturnOpcode(desc)));
			method.instructions = insns;

			method.maxLocals = 1;
			method.maxStack = 1;
			if (desc.equals("J") || desc.equals("D"))
				method.maxStack++;

			cn.methods.add(method);

			System.out.println("injecting " + method + " for " + f.val(Constants.REAL_OWNER) + " " + f.obfuscated() + " " + desc);
//			System.out.printf("Injecting getter for %s.%s %s as %s.%s %s.%n" + owner.name, f.obfuscated(), desc, cn.name, method.name, method.desc);
		}
	}
}