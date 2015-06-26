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