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

/**
 * @author Tyler Sedlar
 */
public class ConversionNode extends AbstractNode {
	private static final long serialVersionUID = 4993791878851883165L;

	public ConversionNode(NodeTree tree, AbstractInsnNode insn, int collapsed, int producing) {
		super(tree, insn, collapsed, producing);
	}

	public boolean fromInt() {
		return opcode() == I2B || opcode() == I2C || opcode() == I2S || opcode() == I2L || opcode() == I2D;
	}

	public boolean toInt() {
		return opcode() == D2I || opcode() == L2I || opcode() == F2I;
	}

	public boolean toChar() {
		return opcode() == I2C;
	}

	public boolean toShort() {
		return opcode() == I2S;
	}

	public boolean fromDouble() {
		return opcode() == D2I || opcode() == D2F || opcode() == D2L;
	}

	public boolean toDouble() {
		return opcode() == I2D || opcode() == L2D || opcode() == F2D;
	}

	public boolean fromLong() {
		return opcode() == L2I || opcode() == L2F || opcode() == L2D;
	}

	public boolean toLong() {
		return opcode() == I2L || opcode() == D2L || opcode() == F2L;
	}

	public boolean fromFloat() {
		return opcode() == F2I || opcode() == F2D || opcode() == F2L;
	}

	public boolean toFloat() {
		return opcode() == I2F || opcode() == D2F || opcode() == L2F;
	}
}
