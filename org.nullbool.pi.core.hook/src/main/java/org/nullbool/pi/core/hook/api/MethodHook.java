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

package org.nullbool.pi.core.hook.api;

import org.objectweb.asm.tree.InsnList;

public class MethodHook extends ObfuscatedData {

	private static final long serialVersionUID = 5848090575172209265L;
	
	private ClassHook owner;
	private InsnList instructions;

	public MethodHook() {
	}

	public MethodHook(ClassHook owner) {
		this.owner = owner;
	}
	
	public MethodHook(InsnList instructions) {
		this.instructions = instructions;
	}

	@Override
	public MethodHook var(String name, String value) {
		super.var(name, value);
		return this;
	}
	
	@Override
	public MethodHook obfuscated(String obfuscated) {
		super.obfuscated(obfuscated);
		return this;
	}
	
	@Override
	public MethodHook refactored(String refactored) {
		super.refactored(refactored);
		return this;
	}
	
	public ClassHook owner() {
		return owner;
	}

	public MethodHook owner(ClassHook owner) {
		if (this.owner != null)
			this.owner.methods().remove(this);

		this.owner = owner;
		owner.methods().add(this);
		return this;
	}

	public InsnList insns() {
		return instructions;
	}

	public MethodHook insns(InsnList instructions) {
		this.instructions = instructions;
		return this;
	}
	
	public String baseToString() {
		StringBuilder sb = new StringBuilder("MethodHook");
		sb.append(" [\n");
		sb.append(Util.mapToString(variables(), "\t", "\n"));
		sb.append("\tinsns.size=").append(instructions == null ? "null" : instructions.size()).append("\n");
		sb.append("]");
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("MethodHook");
		sb.append(" [\n");
		sb.append("\towner=").append(owner.baseToString()).append("\n");
		sb.append(Util.mapToString(variables(), "\t", "\n"));
		sb.append("\tinsns.size=").append(instructions == null ? "null" : instructions.size()).append("\n");
		sb.append("]");
		return sb.toString();
	}
}
