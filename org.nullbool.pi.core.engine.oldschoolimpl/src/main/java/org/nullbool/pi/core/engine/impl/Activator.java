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

package org.nullbool.pi.core.engine.impl;

import org.nullbool.pi.core.engine.api.IContextFactory;
import org.nullbool.pi.core.engine.api.IVirtualGameBrowserFactory;
import org.nullbool.pi.core.engine.impl.factory.OldschoolContextFactory;
import org.nullbool.pi.core.engine.impl.factory.VirtualRunescapeGameBrowserFactory;
import org.nullbool.pi.core.painting.api.IDelegateRenderer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 6 Jul 2015 07:02:47
 */
public class Activator implements BundleActivator {
	
	@Override
	public void start(BundleContext context) throws Exception {
		context.registerService(IVirtualGameBrowserFactory.class, new VirtualRunescapeGameBrowserFactory(), null);
		context.registerService(IContextFactory.class, new OldschoolContextFactory(), null);
		context.registerService(IDelegateRenderer.class, new ContextDelegateRenderer(), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
	}
}
