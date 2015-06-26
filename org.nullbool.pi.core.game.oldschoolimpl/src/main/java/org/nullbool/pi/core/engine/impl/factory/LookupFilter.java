package org.nullbool.pi.core.engine.impl.factory;

import java.util.HashSet;
import java.util.Set;

import org.nullbool.pi.core.scripting.api.klassmodel.IFilter;

/**
 * @author Bibl (don't ban me pls)
 * @created 24 Jun 2015 23:42:14
 */
public class LookupFilter<T> implements IFilter<T> {

	private final Set<T> elements;
	
	public LookupFilter() {
		this(new HashSet<T>());
	}
	
	public void add(T element) {
		elements.add(element);
	}
	
	public void remove(T element) {
		elements.remove(element);
	}
	
	public Set<T> elements() {
		return elements;
	}
	public LookupFilter(Set<T> elements) {
		if(elements == null)
			throw new IllegalArgumentException();
		
		this.elements = elements;
	}
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.script.IFilter#accept(java.lang.Object)
	 */
	@Override
	public boolean accept(T t) {
		if(elements.isEmpty())
			return true;
		return elements.contains(t);
	}
}