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

package org.nullbool.pi.core.engine.impl.factory;

import java.util.HashSet;
import java.util.Set;

import org.nullbool.pi.core.scripting.api.klassmodel.IFilter;

/**
 * @author Bibl (don't ban me pls)
 * @created 24 Jun 2015 23:42:14
 */
public class LookupFilter<T> implements IFilter<T> {

	private final Set<T> elements;
	
	public LookupFilter() {
		this(new HashSet<T>());
	}
	
	public void add(T element) {
		elements.add(element);
	}
	
	public void remove(T element) {
		elements.remove(element);
	}
	
	public Set<T> elements() {
		return elements;
	}
	public LookupFilter(Set<T> elements) {
		if(elements == null)
			throw new IllegalArgumentException();
		
		this.elements = elements;
	}
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.script.IFilter#accept(java.lang.Object)
	 */
	@Override
	public boolean accept(T t) {
		if(elements.isEmpty())
			return true;
		return elements.contains(t);
	}
}
