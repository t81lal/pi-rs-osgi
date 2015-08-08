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

package org.nullbool.osgi.shell.telnet;

import java.net.ServerSocket;

import org.nullbool.osgi.shell.api.Shell;
import org.osgi.framework.BundleContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 14 Jun 2015 00:54:22
 */
public class TelnetShell extends Shell {

	private final TelnetReceiver receiver;
	
	public TelnetShell(BundleContext context, int port, int maxConnections) throws Throwable {
		super(context);
		receiver = new TelnetReceiver(this, new ServerSocket(port), maxConnections);
	}

	@Override
	public void start() {
		try {
			receiver.start();
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}

	@Override
	public void stop() {
		try {
			receiver.stop();
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
}
