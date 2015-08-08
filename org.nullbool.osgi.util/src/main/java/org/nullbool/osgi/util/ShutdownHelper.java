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

package org.nullbool.osgi.util;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 00:06:48
 */
public class ShutdownHelper {

	public static void shutdown(BundleContext context) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					context.getBundle(0).stop();
				} catch (BundleException e) {
					e.printStackTrace();
					System.err.println("Error stopping system bundle.");
				}

				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Forcing VM shutdown.");
				System.exit(1);
			}
		}).start();
	}
}
