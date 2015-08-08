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

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;

/**
 * @author Tyler Sedlar
 */
public class InsnQuery implements Opcodes {

    public int distance = -1;

    public final int opcode;
    protected AbstractInsnNode insn;

    public InsnQuery(int opcode) {
        this.opcode = opcode;
    }

    public boolean matches(AbstractInsnNode ain) {
        return ain.opcode() == opcode;
    }

    public void setInstruction(AbstractInsnNode insn) {
        this.insn = insn;
    }

    public AbstractInsnNode insn() {
        return insn;
    }

    public InsnQuery distance(int distance) {
        this.distance = distance;
        return this;
    }
}
