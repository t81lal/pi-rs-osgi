package org.nullbool.pi.core.engine.impl.transformers;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.nullbool.pi.core.engine.api.transform.IAPIHelper;
import org.nullbool.pi.core.hook.api.ClassHook;
import org.nullbool.pi.core.hook.api.HookMap;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class DescUtil implements Opcodes {

	/** Used for getting the opcode for loading a value on the stack in a method. **/
	public static final int TYPE_LOAD = 1;
	/** Used for getter the opcode for returning a value from a method. **/
	public static final int TYPE_RETURN = 2;

	public static boolean isPrimitive(String desc) {
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

	/**
	 * Gets the associated opcode for returning a value in a method
	 * 
	 * @param typeDesc
	 *            Desc of the field
	 * @return The opcode used for returning the field type
	 */
	public static int getReturnOpcode(String typeDesc) {
		return getOpcode(typeDesc, TYPE_RETURN);
	}

	/**
	 * Gets the associated opcode for loading a value on the stack in a method
	 * 
	 * @param typeDesc
	 *            Desc of the field
	 * @return The opcode used for loading the field type
	 */
	public static int getLoadOpcode(String typeDesc) {
		return getOpcode(typeDesc, TYPE_LOAD);
	}

	/**
	 * Gets the opcode associated for the type of loading/returning a desc of a field in a method
	 * 
	 * @param typeDesc
	 *            Desc of the field
	 * @param opcodeType
	 *            TYPE_LOAD or TYPE_RETURN
	 * @return The opcode associated for the type of loading/returning a desc of a field in a method or -1 if type isn't valid
	 */
	public static int getOpcode(String typeDesc, int opcodeType) {
		if ((opcodeType != TYPE_LOAD) && (opcodeType != TYPE_RETURN))
			return -1;
		char c = typeDesc.charAt(0);
		switch (c) {
			case 'F':
				return opcodeType == TYPE_LOAD ? FLOAD : opcodeType == TYPE_RETURN ? FRETURN : -1;
			case 'D':
				return opcodeType == TYPE_LOAD ? DLOAD : opcodeType == TYPE_RETURN ? DRETURN : -1;
			case 'J':
				return opcodeType == TYPE_LOAD ? LLOAD : opcodeType == TYPE_RETURN ? LRETURN : -1;
			case 'I':
			case 'B':
			case 'Z':
			case 'S':
				return opcodeType == TYPE_LOAD ? ILOAD : opcodeType == TYPE_RETURN ? IRETURN : -1;
			default:
				return opcodeType == TYPE_LOAD ? ALOAD : opcodeType == TYPE_RETURN ? ARETURN : -1;
		}
	}

	public static int array(String desc) {
		int c = 0;
		char[] chars = desc.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c1 = chars[i];
			if (c1 == '[') {
				c++;
			} else {
				break;
			}
		}
		return c;
	}

	public static String makeArray(int j, String desc) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < j; i++) {
			sb.append(desc);
		}
		return sb.toString();
	}

	public static String convertSingleBytecodeStyle(HookMap hooks, IAPIHelper helper, String desc) {
		return convertSingleBytecodeStyle(hooks, helper, desc.replace("[", ""), array(desc));
	}

	public static String convertSingleBytecodeStyle(HookMap hooks, IAPIHelper helper, String desc, int arr) {
		StringBuilder sb = new StringBuilder();
		sb.append(makeArray(arr, "["));
		if (isPrimitive(desc))
			sb.append(desc);
		else if (desc.startsWith("L") && desc.endsWith(";"))
			sb.append(getBytecodeDesc(hooks, helper, desc));
		return sb.toString();
	}

	public static String convertMultiBytecodeStyle(HookMap hooks, IAPIHelper helper, String desc) {
		if (desc == null)
			return null;

		Type ret = Type.getReturnType(desc);
		String retVal = "";
		if (isVoid(ret)) {
			retVal = "V";
		} else {
			retVal = convertSingleBytecodeStyle(hooks, helper, ret.getDescriptor());
		}

		Set<String> args = parseArgs(desc);
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		Iterator<String> it = args.iterator();
		while (it.hasNext()) {
			String arg = it.next();
			sb.append(convertSingleBytecodeStyle(hooks, helper, arg));
		}
		sb.append(")");
		return String.format("%s%s", sb.toString(), retVal);
	}

	public static boolean isVoid(Type t) {
		return isVoid(t.getDescriptor());
	}

	public static boolean isVoid(String desc) {
		return desc.equals("V");
	}

	public static Set<String> parseArgs(String desc) {
		if (desc == null)
			return null;
		Type[] args = Type.getArgumentTypes(desc);
		// needs to be ordered
		Set<String> set = new LinkedHashSet<String>(args.length);
		for (Type arg : args) {
			set.add(arg.getDescriptor());
		}
		return set;
	}

	public static String standardise(String s) {
		return s.replace(".", "/");
	}

	public static String getBytecodeDesc(HookMap hooks, IAPIHelper helper, String name) {
		if (name == null)
			return null;
		name = standardise(name);

		String className = name.substring(1, name.length() - 1);
		//System.out.println(className);
		ClassHook hook = hooks.forName(className, true);
		if(hook != null) {
			className = hook.refactored();
			//System.out.println(className);
		}

		for(String simple : helper.simpleNames()) {
			if(standardise(simple).equals(className)) {
				return String.format("L%s%s;", helper.accessorBase(), helper.canonicalName(simple));
			}
		}

		return name;
	}
}