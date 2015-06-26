package org.nullbool.pi.core.scripting.api.klassmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bibl (don't ban me pls)
 * @created 24 Jun 2015 23:08:12
 */
public class AggregateFilter<T> implements IAggregateFilter<T> {
	
	private final List<IFilter<T>> filters = new ArrayList<IFilter<T>>();
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.script.MasterScriptLoader.IAggregateFilter#accept(java.lang.Object)
	 */
	@Override
	public boolean accept(T t) {
		for(IFilter<T> f : filters) {
			if(!f.accept(t))
				return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.script.MasterScriptLoader.IAggregateFilter#connect(org.nullbool.pi.core.game.api.script.MasterScriptLoader.IFilter)
	 */
	@Override
	public void connect(IFilter<T> f) {
		filters.add(f);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.script.MasterScriptLoader.IAggregateFilter#disconnect(org.nullbool.pi.core.game.api.script.MasterScriptLoader.IFilter)
	 */
	@Override
	public void disconnect(IFilter<T> f) {
		filters.remove(f);
	}
}