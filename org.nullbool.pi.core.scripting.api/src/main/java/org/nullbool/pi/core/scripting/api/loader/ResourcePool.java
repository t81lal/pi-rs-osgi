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