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
import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.topdank.banalysis.filter.ConstantFilter;
import org.topdank.banalysis.filter.InstructionFilter;
import org.topdank.banalysis.filter.IntegerFilter;
import org.topdank.banalysis.filter.ZeroCancelIntegerFilter;

public class MultiANewArrayInstructionFilter implements InstructionFilter {
	
	protected ConstantFilter<String> descFilter;
	protected IntegerFilter dimsFilter;
	
	public MultiANewArrayInstructionFilter(String desc, int dims) {
		descFilter = new ConstantFilter<String>(desc);
		dimsFilter = new ZeroCancelIntegerFilter(dims);
	}
	
	@Override
	public boolean accept(AbstractInsnNode t) {
		if (!(t instanceof MultiANewArrayInsnNode))
			return false;
		return descFilter.accept(((MultiANewArrayInsnNode) t).desc) && dimsFilter.accept(((MultiANewArrayInsnNode) t).dims);
	}
}
