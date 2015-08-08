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

package org.objectweb.asm.commons.cfg.query;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

/**
 * @author Tyler Sedlar
 */
public class MemberQuery extends InsnQuery {

    protected final String owner, name, desc;

    public MemberQuery(int opcode, String owner, String name, String desc) {
        super(opcode);
        this.owner = owner;
        this.name = name;
        this.desc = desc;
    }

    public MemberQuery(int opcode, String owner, String desc) {
        this(opcode, owner, null, desc);
    }

    public MemberQuery(int opcode, String desc) {
        this(opcode, null, desc);
    }

    public MemberQuery(String desc) {
        this(-1, desc);
    }

    public MemberQuery(int opcode) {
        this(opcode, null, null, null);
    }

    @Override
    public boolean matches(AbstractInsnNode ain) {
        if (!(ain instanceof FieldInsnNode) && !(ain instanceof MethodInsnNode)) return false;
        int opcode = ain.opcode();
        String owner, name, desc;
        if (ain instanceof FieldInsnNode) {
            FieldInsnNode fin = (FieldInsnNode) ain;
            owner = fin.owner;
            name = fin.name;
            desc = fin.desc;
        } else {
            MethodInsnNode min = (MethodInsnNode) ain;
            owner = min.owner;
            name = min.name;
            desc = min.desc;
        }
        if (this.opcode == -1 || this.opcode == opcode) {
            if (this.owner == null || this.owner.equals(owner)) {
                if (this.name == null || this.name.equals(name)) {
                    if (this.desc == null || this.desc.equals(desc) || desc.matches(this.desc)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
