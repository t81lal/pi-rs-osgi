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

package org.nullbool.pi.core.engine.impl.transformers;

import java.util.Map;

import org.nullbool.pi.core.engine.api.transform.IAPIHelper;
import org.nullbool.pi.core.engine.api.transform.ITransformer;
import org.nullbool.pi.core.hook.api.ClassHook;
import org.nullbool.pi.core.hook.api.HookMap;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

/**
 * @author Bibl (don't ban me pls)
 * @created some time before 8/8/15
 */
public abstract class AbstractTransformer implements ITransformer, Opcodes {

	protected final Map<String, ClassNode> classes;
	protected final HookMap hooks;
	protected final IAPIHelper helper;
	protected ClassHook hook;
	
	public AbstractTransformer(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) {
		this.hooks = hooks;
		this.classes = classes;
		this.helper = helper;
	}
	
	@Override
	public boolean accept(ClassNode cn) {
		return (hook = hooks.forName(cn.name, true)) != null;
	}
	
	@Override
	public abstract void run(ClassNode cn);
}
