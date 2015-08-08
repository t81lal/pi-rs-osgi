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

package org.objectweb.asm.commons.cfg.tree.node;

import org.objectweb.asm.commons.cfg.tree.NodeTree;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ReferenceNode extends AbstractNode {
	private static final long serialVersionUID = 4993791878851883165L;

    public ReferenceNode(NodeTree tree, AbstractInsnNode insn, int collapsed, int producing) {
        super(tree, insn, collapsed, producing);
    }

    public boolean isStatic() {
        return opcode() == GETSTATIC || opcode() == PUTSTATIC || opcode() == INVOKESTATIC;
    }

    public String key() {
        AbstractInsnNode ain = insn();
        if (ain instanceof FieldInsnNode) {
            FieldInsnNode fin = (FieldInsnNode) ain;
            return fin.owner + "." + fin.name;
            //return fin.key();
            //return fin.owner + "." + fin.name + fin.desc;
        } else if (ain instanceof MethodInsnNode) {
            MethodInsnNode min = (MethodInsnNode) ain;
            return min.owner + "." + min.name + min.desc;
        }
        return null;
    }

    public String owner() {
        AbstractInsnNode insn = insn();
        if (this instanceof FieldMemberNode) {
            return ((FieldInsnNode) insn).owner;
        } else if (this instanceof MethodMemberNode) {
            return ((MethodInsnNode) insn).owner;
        }
        return null;
    }

    public String name() {
        AbstractInsnNode ain = insn();
        if (ain instanceof FieldInsnNode) {
            return ((FieldInsnNode) ain).name;
        } else if (ain instanceof MethodInsnNode) {
            return ((MethodInsnNode) ain).name;
        }
        return null;
    }

    public String desc() {
        AbstractInsnNode ain = insn();
        if (this instanceof FieldMemberNode) {
            return ((FieldInsnNode) ain).desc;
        } else if (this instanceof MethodMemberNode) {
            return ((MethodInsnNode) ain).desc;
        }
        return null;
    }

    public boolean referenced(MethodNode mn) {
        for (AbstractInsnNode ain : mn.instructions.toArray()) {
            if (ain instanceof FieldInsnNode) {
                FieldInsnNode fin = (FieldInsnNode) ain;
                if (key().equals(fin.owner + "." + fin.name)) return true;
            } else if (ain instanceof MethodInsnNode) {
                MethodInsnNode min = (MethodInsnNode) ain;
                if (key().equals(min.owner + "." + min.name + min.desc)) return true;
            }
        }
        return false;
    }
}
