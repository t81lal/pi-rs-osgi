package org.nullbool.pi.core.scripting.api.klassmodel;

/**
 * @author Bibl (don't ban me pls)
 * @created 24 Jun 2015 23:08:12
 */
public abstract interface IAggregateFilter<T> extends IFilter<T> {
	
	@Override
	public abstract boolean accept(T t);
	
	public abstract void connect(IFilter<T> f);
	
	public abstract void disconnect(IFilter<T> f);
}