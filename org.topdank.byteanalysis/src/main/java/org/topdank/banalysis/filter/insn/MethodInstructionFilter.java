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
import org.objectweb.asm.tree.MethodInsnNode;
import org.topdank.banalysis.filter.ConstantFilter;
import org.topdank.banalysis.filter.OpcodeFilter;

public class MethodInstructionFilter extends OpcodeInstructionFilter {
	
	private ConstantFilter<String> ownerFilter;
	private ConstantFilter<String> nameFilter;
	private ConstantFilter<String> descFilter;
	
	public MethodInstructionFilter(OpcodeFilter filter, String owner, String name, String desc) {
		super(filter);
		ownerFilter = createFilter(owner);
		nameFilter = createFilter(name);
		descFilter = createFilter(desc);
	}
	
	public MethodInstructionFilter(int opcode, String owner, String name, String desc) {
		super(new OpcodeFilter(opcode));
		ownerFilter = createFilter(owner);
		nameFilter = createFilter(name);
		descFilter = createFilter(desc);
	}
	
	private ConstantFilter<String> createFilter(String s) {
		if (s == null)
			return new ConstantFilter<String>();
		return new ConstantFilter<String>(s);
	}
	
	@Override
	public boolean accept(AbstractInsnNode t) {
		if (!(t instanceof MethodInsnNode))
			return false;
		if (!opcodeFilter.accept(t))
			return false;
		MethodInsnNode min = (MethodInsnNode) t;
		if (!ownerFilter.accept(min.owner))
			return false;
		if (!nameFilter.accept(min.name))
			return false;
		if (!descFilter.accept(min.desc))
			return false;
		return true;
	}
}
