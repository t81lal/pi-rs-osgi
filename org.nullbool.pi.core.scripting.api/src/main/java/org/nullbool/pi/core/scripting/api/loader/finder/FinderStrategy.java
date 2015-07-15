package org.nullbool.pi.core.scripting.api.loader.finder;

import java.util.Set;

public abstract interface FinderStrategy<T> {

	public abstract Set<T> find();
}