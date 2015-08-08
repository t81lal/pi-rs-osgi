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

package org.objectweb.asm.commons.cfg;

import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.cfg.graph.CallGraph;
import org.objectweb.asm.tree.MethodNode;

/**
 * @author Tyler Sedlar
 */
public class CallVisitor extends MethodVisitor {

    public CallVisitor() {
		super(Opcodes.ASM5);
	}

	public final CallGraph graph = new CallGraph();

    private MethodNode mn;

    public void visit(MethodNode mn) {
        this.mn = mn;
        mn.accept(this);
    }
    
    @Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {
        graph.addMethodCall(mn.handle, new Handle(0, owner, name, desc));

    }
    
    @Override
	public void visitMethodInsn(int opcode, String owner, String name,
            String desc, boolean itf) {
        graph.addMethodCall(mn.handle, new Handle(0, owner, name, desc));

    }
}
