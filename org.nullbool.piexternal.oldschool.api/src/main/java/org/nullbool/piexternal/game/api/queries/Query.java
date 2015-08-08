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

package org.nullbool.piexternal.game.api.queries;

import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

import org.nullbool.piexternal.game.api.Utilities;

public abstract class Query<T> implements Iterable<T> {

	private T[] array;
	private Stream<T> stream;

	public T[] array() {
		return array;
	}

	public Stream<T> stream() {
		return stream;
	}

	public void setStream(final Stream<T> stream) {
		this.stream = stream;
	}

	public List<T> list() {
		return Arrays.asList(array);
	}

	public void setArray(final T[] array) {
		this.array = array;
	}

	public Iterator<T> iterator() {
		return Arrays.asList(array).iterator();
	}

	public T first() {
		return array.length > 0 ? array[0] : null;
	}
	
	public T last() {
		return array.length > 0 ? array[array.length - 1] : null;
	}

	public T random() {
		return array.length > 0 ? array[Utilities.next(0, array.length)] : null;
	}
}
