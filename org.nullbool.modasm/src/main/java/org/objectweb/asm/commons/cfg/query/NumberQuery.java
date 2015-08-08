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
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * @author Tyler Sedlar
 */
public class NumberQuery extends InsnQuery {

    private int number = -1;

    public NumberQuery(int opcode) {
        super(opcode);
    }

    public NumberQuery(int opcode, int number) {
        this(opcode);
        this.number = number;
    }

    @Override
    public boolean matches(AbstractInsnNode ain) {
        if (!(ain instanceof IntInsnNode) && !(ain instanceof LdcInsnNode) && !(ain instanceof VarInsnNode))
            return false;
        if (ain instanceof IntInsnNode) {
            return number == -1 || ((IntInsnNode) ain).operand == number;
        } else if (ain instanceof LdcInsnNode) {
            Object cst = ((LdcInsnNode) ain).cst;
            return number == -1 || cst instanceof Number && ((Number) cst).intValue() == number;
        } else {
            return number == -1 || ((VarInsnNode) ain).var == number;
        }
    }
}
