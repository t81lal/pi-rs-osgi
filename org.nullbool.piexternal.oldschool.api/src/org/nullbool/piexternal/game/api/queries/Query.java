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
