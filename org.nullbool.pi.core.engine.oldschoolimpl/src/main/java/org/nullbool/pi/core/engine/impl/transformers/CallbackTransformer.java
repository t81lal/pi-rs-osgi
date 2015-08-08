/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

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
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class CallbackTransformer extends AbstractTransformer {

	public CallbackTransformer(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) {
		super(classes, hooks, helper);
	}

	@Override
	public void run(ClassNode cn) {
		ClassHook ch = hooks.forName(cn.name, true);
		if(ch != null) {
			for(MethodHook mh : ch.methods()) {
				MethodNode mn = new MethodNode(cn, ACC_PUBLIC, mh.refactored(), mh.val(Constants.REFACTORED_DESC), null, null);
				InsnList insns = mn.instructions;
				
				boolean isStatic = Boolean.valueOf(mh.val(Constants.STATIC));
				if(!isStatic) {
					insns.add(new VarInsnNode(ALOAD, 0));
				}
				
				Type[] args = Type.getArgumentTypes(mn.desc);
				for(int i=0; i < args.length; i++) {
					Type arg = args[i];
					String d = arg.getDescriptor();
					int load = DescUtil.getLoadOpcode(d);
					insns.add(new VarInsnNode(load, i + 1));
				}
				
				String calleeDesc = mh.val(Constants.DESC);
				String _hasOpaque = mh.val(Constants.HAS_OPAQUE);
				if(_hasOpaque != null && !_hasOpaque.isEmpty()) {
					boolean hasOpaque = Boolean.valueOf(_hasOpaque);
					if(hasOpaque) {
						String opaqueVal = mh.val(Constants.SAFE_OPAQUE);
						
						Type[] realArgs = Type.getArgumentTypes(calleeDesc);
						String valType = realArgs[realArgs.length - 1].getDescriptor();
						Number n = null;
						if(valType.equals("I")) {
							n = Integer.valueOf(opaqueVal);
						} else if(valType.equals("S")) {
							n = Short.valueOf(opaqueVal);
						} else if(valType.equals("B")) {
							n = Byte.valueOf(opaqueVal);
						} else {
							throw new RuntimeException();
						}
						
						insns.add(new LdcInsnNode(n));
					}
				}
				
				int callOpcode = isStatic ? INVOKESTATIC : INVOKEVIRTUAL;
				insns.add(new MethodInsnNode(callOpcode, mh.val(Constants.REAL_OWNER), mh.obfuscated(), calleeDesc, false));
				
				Type ret = Type.getReturnType(mn.desc);
				String rDesc = ret.getDescriptor();
				if(!rDesc.equals("V")) {
					insns.add(new InsnNode(DescUtil.getReturnOpcode(rDesc)));
				} else {
					insns.add(new InsnNode(RETURN));
				}
				
				String stripped = rDesc.replace("[", "");
				int arrs = rDesc.length() - stripped.length();
				if(!DescUtil.isVoid(stripped) && !DescUtil.isPrimitive(stripped)) {
					StringBuilder sb = new StringBuilder();
					sb.append("(");
					for(Type arg : args) {
						sb.append(arg.getDescriptor());
					}
					sb.append(")");
					for(int i=0; i < arrs; i++) {
						sb.append("[");
					}
					
					String newRet = stripped;
					newRet = newRet.substring(1);
					newRet = newRet.substring(0, newRet.length() - 1);
					String r = interfaceType(newRet);
					r = ("L" + r + ";");
					sb.append(r);
					
					mn.desc = sb.toString();
					System.out.println("newdesc: " + mn.desc);
				}
				
				
				cn.methods.add(mn);

				// System.out.println(mn);
				// InstructionPrinter.consolePrint(mn);
			}
		}
	}
	
	private String interfaceType(String real) {
		ClassHook ch = hooks.forName(real, true);
		if(ch == null)
			return real;
		String name = ch.refactored();
		
		String interfaceName = helper.canonicalName(name);
		if (interfaceName != null) {
			String fullName = (interfaceName.startsWith(helper.accessorBase()) ? "" : helper.accessorBase()) + interfaceName;
			return fullName;
		}
		return real;
	}
}
