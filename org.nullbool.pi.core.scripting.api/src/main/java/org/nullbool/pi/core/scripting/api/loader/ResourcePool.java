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

package org.nullbool.pi.core.scripting.api.loader;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 8 Mar 2015 at 19:10:12 <br>
 */
public abstract interface ResourcePool<T> extends Iterable<T> {

	public abstract boolean add(@SuppressWarnings("unchecked") T... ts);

	public abstract boolean add(Collection<T> ts);

	public abstract boolean remove(@SuppressWarnings("unchecked") T... ts);

	public abstract boolean remove(Collection<T> t);

	public abstract boolean containsDuplicates();

	public abstract int size();

	public abstract void clear();

	public abstract Collection<T> pool();

	@Override
	public abstract Iterator<T> iterator();
}
