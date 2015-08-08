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

package org.nullbool.pi.core.hook.serimpl;

import java.util.Hashtable;

import org.nullbool.pi.core.hook.api.serialisation.IMapSerialisationFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	
	public static final String CONTENT_TYPE_IMPL = "bser";
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void start(BundleContext context) throws Exception {
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put(IMapSerialisationFactory.CONTENT_TYPE, CONTENT_TYPE_IMPL);
		ht.put(IMapSerialisationFactory.ACTIVITY, "static");
		context.registerService(IMapSerialisationFactory.class, new StaticMapSeralisationFactoryImpl(), ht);
		
		//ht = new Hashtable<String, String>();
		//ht.put(IMapSerialisationFactory.CONTENT_TYPE, CONTENT_TYPE_IMPL);
		//ht.put(IMapSerialisationFactory.ACTIVITY, "active");
		//context.registerService(IMapSerialisationFactory.class, new ActiveMapSerialisationFactoryImpl(), ht);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		
	}
}
