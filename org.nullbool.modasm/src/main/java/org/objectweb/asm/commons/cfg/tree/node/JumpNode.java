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
import org.objectweb.asm.commons.util.Assembly;
import org.objectweb.asm.tree.JumpInsnNode;

public class JumpNode extends AbstractNode {
	private static final long serialVersionUID = 4993791878851883165L;

	private TargetNode target;

	public JumpNode(NodeTree tree, JumpInsnNode insn, int collapsed, int producing) {
		super(tree, insn, collapsed, producing);
	}

	@Override
	public JumpInsnNode insn() {
		return (JumpInsnNode) super.insn();
	}

	public AbstractNode resolve() {
		return target.resolve();
	}

	public void setTarget(TargetNode target) {
		if (this.target != null) {
			this.target.removeTargeter(this);
		}
		if (target != null) {
			target.addTargeter(this);
			insn().label = target.label();
		} else {
			insn().label = null;
		}
		this.target = target;
	}

	public TargetNode target() {
		return target;
	}

	@Override
	protected String toString(int tab) {
		StringBuilder sb = new StringBuilder();
		sb.append(Assembly.toString(insn()));
		sb.append(' ').append('>').append(' ');
		sb.append(target);
		for (AbstractNode n : this) {
			sb.append('\n');
			for (int i = 0; i < tab; i++) {
				sb.append('\t');
			}
			sb.append(n.toString(tab + 1));
		}
		return sb.toString();
	}
}
