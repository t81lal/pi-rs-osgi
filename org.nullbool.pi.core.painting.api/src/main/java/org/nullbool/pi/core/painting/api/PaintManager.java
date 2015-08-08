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

package org.nullbool.pi.core.painting.api;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public final class PaintManager {

	private PaintManager() {
		throw new UnsupportedOperationException();
	}
	
	public static IDelegateRenderer getInstance() {
		IDelegateRenderer dr = getInstance0();
		if(dr != null) {
			return dr;
		} else {
			throw new UnsupportedOperationException("no renderer installed.");
		}
	}
	
	private static IDelegateRenderer getInstance0() {
		Bundle bundle = FrameworkUtil.getBundle(PaintManager.class);
		if(bundle == null) {
			throw new IllegalStateException("null bundle.");
		}
		
		BundleContext context = bundle.getBundleContext();
		ServiceReference<IDelegateRenderer> drSvcRef = context.getServiceReference(IDelegateRenderer.class);
		IDelegateRenderer dr = context.getService(drSvcRef);
		context.ungetService(drSvcRef);
		return dr;
	}
}
