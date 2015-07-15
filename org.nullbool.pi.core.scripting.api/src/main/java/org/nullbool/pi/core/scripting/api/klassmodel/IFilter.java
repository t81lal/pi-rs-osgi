package org.nullbool.pi.core.scripting.api.klassmodel;

/**
 * @author Bibl (don't ban me pls)
 * @created 24 Jun 2015 23:08:12
 */
public abstract interface IFilter<T> {
	public abstract boolean accept(T t);
}