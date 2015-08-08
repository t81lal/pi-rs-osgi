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

package org.topdank.banalysis.asm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.topdank.banalysis.filter.Filter;

public abstract class InfoVector<T> {
	
	protected Map<Integer, T> numericalMap;
	protected Map<T, Integer> retrievalMap;
	
	public InfoVector(List<T> ts) {
		this(ts, true, new Filter<T>() {
			@Override
			public boolean accept(T t) {
				return true;
			}
		});
	}
	
	public InfoVector(List<T> ts, boolean definiteCount, Filter<T> filter) {
		numericalMap = new HashMap<Integer, T>();
		retrievalMap = new HashMap<T, Integer>();
		if (definiteCount) {
			buildAbsoluteMap(ts, filter);
		} else {
			buildRelativeMap(ts, filter);
		}
	}
	
	public void buildAbsoluteMap(List<T> ts, Filter<T> filter) {
		for(int i = 0; i < ts.size(); i++) {
			T t = ts.get(i);
			if (filter.accept(t)) {
				numericalMap.put(i, ts.get(i));
				retrievalMap.put(ts.get(i), i);
			}
		}
	}
	
	public void buildRelativeMap(List<T> ts, Filter<T> filter) {
		int count = 0;
		for(T t : ts) {
			if (filter.accept(t)) {
				numericalMap.put(count, t);
				retrievalMap.put(t, count);
				count++;
			}
		}
	}
	
	public T getAt(int index) {
		return numericalMap.get(index);
	}
}
