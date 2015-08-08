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
import org.nullbool.pi.core.engine.impl.DelegateCanvas;
import org.nullbool.pi.core.hook.api.HookMap;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class CanvasReplacerTransformer extends AbstractTransformer {
	
	private static final String NEW_CANVAS_CLASS = DelegateCanvas.class.getCanonicalName().replace(".", "/");;
	
	public CanvasReplacerTransformer(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) {
		super(classes, hooks, helper);
	}

	@Override
	public void run(ClassNode cn) {
		if(cn.superName.equals("java/awt/Canvas")) {
			cn.superName = NEW_CANVAS_CLASS;
			for(MethodNode m : cn.methods) {
				if(m.name.equals("<init>")) {
					for(AbstractInsnNode ain : m.instructions.toArray()) {
						if(ain.opcode() == INVOKESPECIAL) {
							MethodInsnNode min = (MethodInsnNode) ain;
							if(min.owner.equals("java/awt/Canvas") && min.name.equals("<init>")) {
								min.owner = NEW_CANVAS_CLASS;
							}
						}
					}
				}
			}
		}
	}
}
