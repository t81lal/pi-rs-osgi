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
import org.nullbool.pi.core.hook.api.HookMap;
import org.objectweb.asm.tree.ClassNode;

/**
 * @author Bibl (don't ban me pls)
 * @created some time before 8/8/15
 */
public class ImplementorTransformer extends AbstractTransformer {

	public ImplementorTransformer(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) {
		super(classes, hooks, helper);
	}

	@Override
	public void run(ClassNode cn) {
		/*for(InterfaceMapping im : hook.getInterfaces()) {
			String c = im.getCanonicalName();
			if(!cn.interfaces.contains(c))
				cn.interfaces.add(c);
		}*/
		
		String name = hook.refactored();
		if(name == null)
			return;
		
		String interfaceName = helper.canonicalName(name);
		if (interfaceName != null) {
			//System.out.println("name: " + name);
			//System.out.println("intf: " + interfaceName);
			String fullName = (interfaceName.startsWith(helper.accessorBase()) ? "" : helper.accessorBase()) + interfaceName;
			//System.out.println("full: " + fullName);
			cn.interfaces.add(fullName);
		}
	}
}
