package org.nullbool.pi.core.engine.impl.transformers;

import java.math.BigInteger;
import java.util.Map;

import org.nullbool.pi.core.engine.api.transform.IAPIHelper;
import org.nullbool.pi.core.hook.api.Constants;
import org.nullbool.pi.core.hook.api.FieldHook;
import org.nullbool.pi.core.hook.api.HookMap;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class GetterSetterTransformer extends AbstractTransformer {

	public GetterSetterTransformer(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) {
		super(classes, hooks, helper);
	}

	@Override
	public void run(ClassNode cn) {
		// System.out.println(hook.fields().size());

		for (FieldHook f : hook.fields()) {
			// The field owner may be different to the call owner
			// eg, Client.get.. returns Other.other;
			String desc = f.val(Constants.DESC);
			String refactoredDesc = DescUtil.convertSingleBytecodeStyle(hooks, helper, desc);
			
			boolean mint = desc.equalsIgnoreCase("I");
			boolean mlong = desc.equalsIgnoreCase("J");
			
			ClassNode owner = classes.get(f.owner().obfuscated());
			MethodNode getter = new MethodNode(owner, ACC_PUBLIC, getterName(f.refactored(), desc), "()" + refactoredDesc, null, null);
			
			boolean isStatic = Boolean.valueOf(f.val(Constants.STATIC));
			if (!isStatic) {
				getter.instructions.add(new VarInsnNode(ALOAD, 0));
			}
			
			int getOpcode = isStatic ? GETSTATIC : GETFIELD;
			getter.instructions.add(new FieldInsnNode(getOpcode, f.val(Constants.REAL_OWNER), f.obfuscated(), desc));

			if(mint || mlong) {
				if(mlong) {
					getter.instructions.add(new LdcInsnNode(Long.valueOf(f.val(Constants.ENCODER))));
					getter.instructions.add(new InsnNode(LMUL));
				} else {
					getter.instructions.add(new LdcInsnNode(Integer.valueOf(f.val(Constants.ENCODER))));
					getter.instructions.add(new InsnNode(IMUL));
				}
			}

			getter.instructions.add(new InsnNode(DescUtil.getReturnOpcode(desc)));
			cn.methods.add(getter);
			
			String stripped = desc.replace("[", "");
			if(isPrimitive(stripped) || stripped.indexOf('/') != -1) {
				MethodNode setter = new MethodNode(owner, ACC_PUBLIC, setterName(f.refactored(), desc), "(" + desc + ")V", null, null);
				if(!isStatic) {
					setter.instructions.add(new VarInsnNode(ALOAD, 0));
				}
				setter.instructions.add(new VarInsnNode(DescUtil.getLoadOpcode(desc), 1));
				
				if(mint || mlong) {
					if(mlong) {
						long dec = Long.valueOf(f.val(Constants.ENCODER));
						long enc = inverse(dec, true);
						setter.instructions.add(new LdcInsnNode(enc));
						setter.instructions.add(new InsnNode(LMUL));
					} else {
						int dec = Integer.valueOf(f.val(Constants.ENCODER));
						int enc = (int)inverse(dec, false);
						setter.instructions.add(new LdcInsnNode(enc));
						setter.instructions.add(new InsnNode(IMUL));
					}
				}
				
				int setOpcode = isStatic ? PUTSTATIC : PUTFIELD;
				setter.instructions.add(new FieldInsnNode(setOpcode, f.val(Constants.REAL_OWNER), f.obfuscated(), desc));
				setter.instructions.add(new InsnNode(RETURN));
				
				cn.methods.add(setter);	
			}

			//System.out.println("injecting " + method + " for " + f.val(Constants.REAL_OWNER) + " " + f.obfuscated() + " " + desc);
			//System.out.printf("Injecting getter for %s.%s %s as %s.%s %s.%n" + owner.name, f.obfuscated(), desc, cn.name, method.name, method.desc);
		}
	}
	
	private static boolean isPrimitive(String desc) {
		switch (desc) {
			case "I":
			case "D":
			case "F":
			case "B":
			case "S":
			case "J":
			case "Z":
				return true;
			default:
				return false;
		}
	}
	
	private static long inverse(long k, boolean long_) {
		try {
			if(!long_) {
				k = (int)k;
			}
			final BigInteger num = BigInteger.valueOf(k);
			return num.modInverse(new BigInteger(String.valueOf(1L << (long_ ? 64 : 32)))).longValue();
		} catch (final Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private static String upper(String name) {
		char first = name.charAt(0);
		char upper = Character.toUpperCase(first);
		name = name.substring(1);
		return upper + name;
	}

	private static String getterName(String name, String desc) {
		if(name.startsWith("has")) {
			char first = name.charAt(3);
			if(Character.isUpperCase(first))
				return name;
		}

		if(desc.equals("Z")) {
			return "is" + upper(name);
		} else {
			return "get" + upper(name);
		}
	}

	private static String setterName(String name, String desc) {
		return "set" + upper(name);
	}
}