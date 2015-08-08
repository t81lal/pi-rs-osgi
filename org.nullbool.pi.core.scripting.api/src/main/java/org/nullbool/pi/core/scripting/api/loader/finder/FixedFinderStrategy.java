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

package org.nullbool.pi.core.scripting.api.loader.finder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 24 Apr 2015 at 21:32:03 <br>
 */
public class FixedFinderStrategy<T> implements FinderStrategy<T> {

	private final Set<T> jars;

	@SafeVarargs
	public FixedFinderStrategy(T... jars) {
		this.jars = new HashSet<T>();
		for (T t : jars) {
			this.jars.add(t);
		}
	}

	public FixedFinderStrategy(Collection<T> jars) {
		this.jars = new HashSet<T>();
		for (T t : jars) {
			this.jars.add(t);
		}
	}

	@Override
	public Set<T> find() {
		return jars;
	}
}
