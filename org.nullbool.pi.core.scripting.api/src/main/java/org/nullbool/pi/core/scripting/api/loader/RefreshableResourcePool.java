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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 8 Mar 2015 at 19:11:20 <br>
 */
public class RefreshableResourcePool<T, K extends RunnableResourceLocation<T>> implements Iterable<Entry<K, Set<T>>> {

	/*
	 * Pool
	 * add  -> resource location
	 *            -> plugin2
	 * add  -> resource loaction2
	 *            -> plugin1
	 *            
	 * refresh()
	 *    -> locations
	 *        -> load
	 */
	private final Map<K, Set<T>> pool;

	public RefreshableResourcePool() {
		this(new HashMap<K, Set<T>>());
	}

	public RefreshableResourcePool(Map<K, Set<T>> load) {
		this.pool = load;
	}

	public void refresh(boolean overwrite) {
		for (K loc : pool.keySet()) {
			addImpl(loc, overwrite);
		}
	}

	public void refresh() {
		refresh(true);
	}

	protected boolean addImpl(K loc, boolean clean) {
		Set<T> set = pool.get(loc);
		if (set == null) {
			set = new HashSet<T>();
			pool.put(loc, set);
		} else if (clean) {
			set.clear();
		}

		try {
			for (T t : loc.load()) {
				set.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean add(K k) {
		return addImpl(k, true);
	}

	public boolean add(@SuppressWarnings("unchecked") K... ks) {
		return add(true, ks);
	}

	public boolean add(boolean overwrite, @SuppressWarnings("unchecked") K... ks) {
		boolean ret = true;
		for (K k : ks)
			ret &= addImpl(k, overwrite);
		return ret;
	}

	public boolean add(Collection<K> ks) {
		return add(true, ks);
	}

	public boolean add(boolean overwrite, Collection<K> ks) {
		boolean ret = true;
		for (K k : ks)
			ret &= addImpl(k, overwrite);
		return ret;
	}

	protected boolean removeImpl(K k) {
		Set<T> set = pool.remove(k);
		return set != null;
	}

	public boolean remove(K k) {
		return removeImpl(k);
	}

	public boolean remove(@SuppressWarnings("unchecked") K... ks) {
		boolean ret = true;
		for (K k : ks)
			ret &= removeImpl(k);
		return ret;
	}

	public boolean remove(Collection<K> ks) {
		boolean ret = true;
		for (K k : ks)
			ret &= removeImpl(k);
		return ret;
	}

	public Collection<K> locations() {
		return Collections.unmodifiableSet(pool.keySet());
	}

	public Collection<T> pool() {
		Set<T> set = new HashSet<T>();
		for (Entry<K, Set<T>> e : pool.entrySet()) {
			set.addAll(e.getValue());
		}
		return Collections.unmodifiableSet(set);
	}

	public void clear() {
		pool.clear();
	}

	@Override
	public Iterator<Entry<K, Set<T>>> iterator() {
		return pool.entrySet().iterator();
	}
}
