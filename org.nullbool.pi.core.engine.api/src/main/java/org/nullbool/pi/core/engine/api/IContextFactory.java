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

import org.nullbool.core.piexternal.game.api.IGameClient;

/**
 * @see org.nullbool.pi.core.engine.api.IClientContext
 * 
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 19:25:34
 */
public interface IContextFactory<T extends IClientContext<IGameClient>> {

	/**
	 * Implementation specific creation method.
	 * 
	 * @param browser
	 * @return A client context instance or null.
	 * @throws Exception
	 */
	public T create(IVirtualGameBrowser browser) throws Exception;
}
