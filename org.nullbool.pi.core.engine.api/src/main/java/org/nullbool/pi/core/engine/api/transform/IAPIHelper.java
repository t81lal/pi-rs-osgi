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

import java.util.Collection;

/**
 * @author Bibl (don't ban me pls)
 * @since 1
 */
@Deprecated
public interface IAPIHelper {

	public String accessorBase();
	
	public void mapSuperInterfaces(String klass, String[] superInterfaces, boolean overwrite);
	
	public String[] superInterfaces(String klass);
	
	public String canonicalName(String klass);
	
	public void remapCanonicalname(String klass, String newName);
	
	public Collection<String> simpleNames();
}
