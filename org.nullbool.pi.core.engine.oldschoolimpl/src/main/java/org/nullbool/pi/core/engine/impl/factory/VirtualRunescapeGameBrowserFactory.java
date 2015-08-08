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

package org.nullbool.pi.core.engine.impl.factory;

import java.io.IOException;
import java.net.URL;

import org.nullbool.pi.core.engine.api.IVirtualGameBrowser;
import org.nullbool.pi.core.engine.api.IVirtualGameBrowserFactory;
import org.nullbool.pi.core.engine.impl.VirtualRunescapeBrowser;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 01:36:56
 */
public class VirtualRunescapeGameBrowserFactory implements IVirtualGameBrowserFactory {

	@Override
	public IVirtualGameBrowser create(URL url) throws IOException {
		return new VirtualRunescapeBrowser(url);
	}
}
