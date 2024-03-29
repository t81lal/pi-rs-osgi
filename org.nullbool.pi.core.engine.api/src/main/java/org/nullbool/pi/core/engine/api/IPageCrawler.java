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

import java.util.Map;

/**
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 22:18:55
 */
public interface IPageCrawler {

	/**
	 * @return A value-pair map of Applet usable parameters.
	 */
	public Map<String, String> getGameParameters();
	
	/**
	 * @return A value-pair map of Applet environment parameters.
	 */
	public Map<String, String> getAppletParameters();
}
