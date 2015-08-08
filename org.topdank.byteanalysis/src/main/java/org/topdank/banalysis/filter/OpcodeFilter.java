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

package org.topdank.banalysis.filter;

import org.objectweb.asm.tree.AbstractInsnNode;

public class OpcodeFilter implements InstructionFilter {
	
	protected int opcode;
	
	public OpcodeFilter(int opcode) {
		this.opcode = opcode;
	}
	
	@Override
	public boolean accept(AbstractInsnNode t) {
		if (opcode == -1)
			return true;
		return opcode == t.opcode();
	}
}
