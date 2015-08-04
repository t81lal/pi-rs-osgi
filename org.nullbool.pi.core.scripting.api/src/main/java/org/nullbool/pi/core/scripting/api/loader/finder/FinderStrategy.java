package org.nullbool.pi.core.scripting.api.loader.finder;

import java.util.Set;

public interface FinderStrategy<T> {

	public Set<T> find();
}