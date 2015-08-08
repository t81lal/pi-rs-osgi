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

package org.nullbool.osgi.shell.api;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

/**
 * @author Bibl (don't ban me pls)
 * @created 14 Jun 2015 00:13:57
 */
public class BundleHelper {

	public static Bundle getBundle(BundleContext context, String id) {
		if (context == null) {
			throw new IllegalArgumentException("Null context.");
		}

		Bundle bundle = null;
		if (id != null) {
			try {
				bundle = context.getBundle(Long.parseLong(id.trim()));
			} catch (NumberFormatException e) {
				for (Bundle b : context.getBundles()) {
					String uid = b.getSymbolicName() + ":" + b.getHeaders().get(Constants.BUNDLE_VERSION);
					if (uid.equals(id.trim())) {
						bundle = b;
						break;
					}
				}
			}
		}
		if (bundle == null) {
			throw new IllegalArgumentException("No such bundle: " + id);
		}
		return bundle;
	}
}
