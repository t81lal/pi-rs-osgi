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

package org.nullbool.pi.core.engine.api;

import java.util.Set;

import org.nullbool.core.piexternal.game.api.IGameClient;

/**
 * A pair-entry based registry that stores IClientContexts with their
 * associated ThreadGroups. 
 * 
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 03:40:53
 */
public interface IContextRegistry {

	/**
	 * @param context
	 */
	public void register(IClientContext<IGameClient> context);
	
	/**
	 * @param tg
	 */
	public void unregister(ThreadGroup tg);
	
	/**
	 * @param listener
	 */
	public void registerListener(ContextListener listener);
	
	/**
	 * @param listener
	 */
	public void unregisterListener(ContextListener listener);
	
	public void unregisterAllListeners();
	
	/**
	 * @param tg
	 * @return An IClientContext instance or null;
	 */
	public IClientContext<IGameClient> retrieve(ThreadGroup tg);
	
	/**
	 * @return A (possibly empty) set of IClientContexts.
	 */
	public Set<IClientContext<IGameClient>> retrieveAll();
}
