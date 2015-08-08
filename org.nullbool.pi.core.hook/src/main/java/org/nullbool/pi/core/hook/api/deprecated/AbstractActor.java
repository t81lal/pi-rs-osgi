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

package org.nullbool.pi.core.hook.api.deprecated;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.tree.ClassNode;

@Deprecated
public abstract class AbstractActor {

	private final Map<String, String> variables = new HashMap<String, String>();
	
	public Map<String, String> variables() {
		return variables;
	}
	
	/**
	 * Implementation specific action.
	 * @param hooks
	 * @param classes
	 * @param client Whether the code is being ran on a live gamepack or for analysis.
	 */
	public abstract void act(ActiveHookMap hooks, Map<String, ClassNode> classes, boolean client);
}
