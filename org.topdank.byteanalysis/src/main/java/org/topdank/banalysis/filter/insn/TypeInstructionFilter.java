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
import org.objectweb.asm.tree.TypeInsnNode;
import org.topdank.banalysis.filter.ConstantFilter;
import org.topdank.banalysis.filter.OpcodeFilter;

public class TypeInstructionFilter extends OpcodeInstructionFilter {
	
	private ConstantFilter<String> descFilter;
	
	public TypeInstructionFilter(OpcodeFilter filter, String desc) {
		super(filter);
		descFilter = new ConstantFilter<String>(desc);
	}
	
	public TypeInstructionFilter(int opcode, String desc) {
		super(new OpcodeFilter(opcode));
		descFilter = new ConstantFilter<String>(desc);
	}
	
	@Override
	public boolean accept(AbstractInsnNode t) {
		if (!(t instanceof TypeInsnNode))
			return false;
		TypeInsnNode tin = (TypeInsnNode) t;
		return opcodeFilter.accept(tin) && descFilter.accept(tin.desc);
	}
}
