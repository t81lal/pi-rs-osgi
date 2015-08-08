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

package org.objectweb.asm.commons.cfg.tree;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.cfg.BlockVisitor;
import org.objectweb.asm.commons.cfg.tree.node.*;

public abstract class NodeVisitor implements Opcodes {

	private NodeVisitor nv;
    private BlockVisitor bv;

    public boolean validate() {
        return bv == null || bv.validate();
    }

	public NodeVisitor() {
        this.nv = null;
	}

    public NodeVisitor(BlockVisitor bv) {
        this.bv = bv;
    }

	public NodeVisitor(NodeVisitor nv) {
		this.nv = nv;
	}

    public void visitAny(AbstractNode n) {
        if (nv != null)
            nv.visitAny(n);
    }

    public void visit(AbstractNode n) {
        if (nv != null)
            nv.visit(n);
    }

    public void visitCode() {
        if (nv != null)
            nv.visitCode();
    }

    public void visitEnd() {
        if (nv != null)
            nv.visitEnd();
    }

    public void visitField(FieldMemberNode fmn) {
        if (nv != null)
            nv.visitField(fmn);
    }

    public void visitFrame(AbstractNode n) {
        if (nv != null)
            nv.visitFrame(n);
    }

    public void visitIinc(IincNode in) {
        if (nv != null)
            nv.visitIinc(in);
    }

    public void visitJump(JumpNode jn) {
        if (nv != null)
            nv.visitJump(jn);
    }

    public void visitLabel(AbstractNode n) {
        if (nv != null)
            nv.visitLabel(n);
    }

    public void visitConversion(ConversionNode cn) {
        if (nv != null)
            nv.visitConversion(cn);
    }

    public void visitConstant(ConstantNode cn) {
        if (nv != null)
            nv.visitConstant(cn);
    }

    public void visitNumber(NumberNode nn) {
        if (nv != null)
            nv.visitNumber(nn);
    }

    public void visitOperation(ArithmeticNode an) {
        if (nv != null)
            nv.visitOperation(an);
    }

    public void visitVariable(VariableNode vn) {
        if (nv != null) {
            nv.visitVariable(vn);
        }
    }

    public void visitLine(AbstractNode n) {
        if (nv != null)
            nv.visitLine(n);
    }

    public void visitLookupSwitch(AbstractNode n) {
        if (nv != null)
            nv.visitLookupSwitch(n);
    }

    public void visitMethod(MethodMemberNode mmn) {
        if (nv != null)
            nv.visitMethod(mmn);
    }

    public void visitMultiANewArray(AbstractNode n) {
        if (nv != null)
            nv.visitMultiANewArray(n);
    }

    public void visitTableSwitch(AbstractNode n) {
        if (nv != null)
            nv.visitTableSwitch(n);
    }

    public void visitType(TypeNode tn) {
        if (nv != null)
            nv.visitType(tn);
    }
}
