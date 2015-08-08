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

import java.util.LinkedList;
import java.util.List;

import org.objectweb.asm.commons.cfg.tree.NodeTree;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LabelNode;

public class TargetNode extends AbstractNode {
	private static final long serialVersionUID = 4993791878851883165L;

	private final List<JumpNode> nodes = new LinkedList<>();

	public TargetNode(NodeTree tree, AbstractInsnNode insn, int collapsed, int producing) {
		super(tree, insn, collapsed, producing);
	}

	public void addTargeter(JumpNode jn) {
		nodes.add(jn);
	}

	public LabelNode label() {
		return (LabelNode) insn();
	}

	public void removeTargeter(JumpNode jn) {
		nodes.remove(jn);
	}

	public AbstractNode resolve() {
		AbstractNode n = this;
		while (n != null && n.opcode() == -1) {
			n = n.next();
		}
		return n == null ? parent() : n;
	}

	public JumpNode[] targeters() {
		return nodes.toArray(new JumpNode[nodes.size()]);
	}

	@Override
	public String toString(int tab) {
		return "Target@" + Integer.toHexString(label().hashCode());
	}
}
