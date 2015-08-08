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

package org.topdank.banalysis.filter.insn;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.topdank.banalysis.filter.IntegerFilter;
import org.topdank.banalysis.filter.OpcodeFilter;
import org.topdank.banalysis.filter.ZeroCancelIntegerFilter;

public class VarInstructionFilter extends OpcodeInstructionFilter {
	
	private IntegerFilter varFilter;
	
	public VarInstructionFilter() {
		this(-1, -1);
	}
	
	public VarInstructionFilter(OpcodeFilter filter, IntegerFilter filter1) {
		super(filter);
		varFilter = filter1;
	}
	
	public VarInstructionFilter(int opcode, int var) {
		this(new OpcodeFilter(opcode), new ZeroCancelIntegerFilter(var));
	}
	
	@Override
	public boolean accept(AbstractInsnNode t) {
		if (!(t instanceof VarInsnNode))
			return false;
		VarInsnNode vin = (VarInsnNode) t;
		return opcodeFilter.accept(vin) && varFilter.accept(vin.var);
	}
}
