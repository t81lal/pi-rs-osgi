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

package org.nullbool.api.util.map;

import java.util.LinkedHashMap;

/**
 * @author Bibl (don't ban me pls)
 * @created 2 Jun 2015 18:47:27
 */
public class NullPermeableLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 1L;

	private final ValueCreator<V> creator;

	public NullPermeableLinkedHashMap(ValueCreator<V> creator) {
		this.creator = creator;
	}

	public NullPermeableLinkedHashMap() {
		this(new NullCreator<V>());
	}

	public V getNotNull(K k) {
		V val = get(k);
		if (val == null) {
			val = creator.create();
			put(k, val);
		} 
		return val;
	}
}
