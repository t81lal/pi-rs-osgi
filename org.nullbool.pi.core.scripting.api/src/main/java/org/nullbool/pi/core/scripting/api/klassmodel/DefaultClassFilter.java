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

package org.nullbool.pi.core.scripting.api.klassmodel;

import java.util.ArrayList;
import java.util.List;

public class DefaultClassFilter implements IFilter<String> {

	private final List<String> allowed = new ArrayList<String>();
	
	public DefaultClassFilter() {
		allowed.add("java.");
		allowed.add("sun.");
		allowed.add("com.sun");
		allowed.add("com.oracle");
		allowed.add("javax.");
	}
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.script.MasterScriptLoader.IFilter#accept(java.lang.Object)
	 */
	@Override
	public boolean accept(String t) {
		if(t == null || t.isEmpty())
			return false;
		
		for(String s : allowed) {
			if(t.startsWith(s))
				return true;
		}
		return false;
	}
}
