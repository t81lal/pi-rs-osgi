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
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;

public class NumberNode extends AbstractNode {
	private static final long serialVersionUID = 4993791878851883165L;

	public NumberNode(NodeTree tree, AbstractInsnNode insn, int collapsed, int producing) {
		super(tree, insn, collapsed, producing);
	}

	public int number() {
		AbstractInsnNode insn = insn();
		int op = insn.opcode();
		switch (op) {
			case NEWARRAY:
			case BIPUSH:
			case SIPUSH: {
				return ((IntInsnNode) insn).operand;
			}
			case ICONST_M1:
			case ICONST_0:
			case ICONST_1:
			case ICONST_2:
			case ICONST_3:
			case ICONST_4:
			case ICONST_5: {
				return op - ICONST_0;
			}
			case LCONST_0:
			case LCONST_1: {
				return op - LCONST_0;
			}
			case FCONST_0:
			case FCONST_1:
			case FCONST_2: {
				return op - FCONST_0;
			}
			case DCONST_0:
			case DCONST_1: {
				return op - DCONST_0;
			}
			case LDC: {
				Object cst = ((LdcInsnNode) insn).cst;
				if (cst instanceof Number) {
					return ((Number) cst).intValue();
				}
			}
			default: {
				return -1;
			}
		}
	}

    public void setNumber(int number) {
        AbstractInsnNode ain = insn();
        if (ain instanceof IntInsnNode) {
            ((IntInsnNode) insn()).operand = number;
            ((IntInsnNode) ain).operand = number;
        } else if (ain instanceof LdcInsnNode) {
            ((LdcInsnNode) insn()).cst = number;
            ((LdcInsnNode) ain).cst = number;
        }
    }
    
    public void setNumber(Number num) {
    	AbstractInsnNode ain = insn();
    	if(!(ain instanceof LdcInsnNode)) {
    		setInstruction(new LdcInsnNode(num));
    	} else{
    		((LdcInsnNode) ain).cst = num;
    	}
    }
    
    @Override
	public String toString() {
    	return insn().toString();
    }
}
