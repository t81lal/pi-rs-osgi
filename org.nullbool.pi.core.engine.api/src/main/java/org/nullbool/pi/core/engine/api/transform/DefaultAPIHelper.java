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

package org.nullbool.pi.core.engine.api.transform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultAPIHelper implements IAPIHelper {

	private final String base;
	private final Map<String, String> canons;
	private final Map<String, List<String>> supers;

	public DefaultAPIHelper() {
		this(null);
	}
	
	public DefaultAPIHelper(String base) {
		this.base = base;
		canons = new HashMap<String, String>();
		supers = new HashMap<String, List<String>>();
	}

	@Override
	public String accessorBase() {
		return base;
	}

	@Override
	public void mapSuperInterfaces(String klass, String[] superInterfaces, boolean overwrite) {
		List<String> col = null;
		if(supers.containsKey(klass)) {
			col = supers.get(klass);
			if(overwrite) {
				col.clear();
			}
		} else {
			col = new ArrayList<String>();
			supers.put(klass, col);
		}
		
		for(String si : superInterfaces) {
			col.add(si);
		}
	}

	@Override
	public String[] superInterfaces(String klass) {
		if(!supers.containsKey(klass)) {
			return new String[0];
		} else {
			List<String> col = supers.get(klass);
			return col.toArray(new String[0]);
		}
	}

	@Override
	public String canonicalName(String klass) {
		return canons.get(klass);
	}

	@Override
	public void remapCanonicalname(String klass, String newName) {
		canons.put(klass, newName);
	}

	@Override
	public Collection<String> simpleNames() {
		return canons.keySet();
	}
}
