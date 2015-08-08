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
		if(filters.size() == 0) {
			return true;
		} else {
			for(IFilter<T> f : filters) {
				if(f.accept(t))
					return true;
				//if(!f.accept(t))
				//	return false;
			}
		}
		return false;
		//return true;
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
