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

package org.nullbool.osgi.shell.console;

import org.nullbool.osgi.shell.api.Shell;
import org.nullbool.osgi.shell.api.handlers.OSGiStopCommandHandler;
import org.osgi.framework.BundleContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 14 Jun 2015 21:49:31
 */
public class ConsoleShell extends Shell {
	
	private final StreamMonitor monitor;
	
	public ConsoleShell(BundleContext context) {
		super(context);
		
		registerHandler(new OSGiStopCommandHandler(), "exit", "quit");
		monitor = new StreamMonitor(this);
	}

	public ConsoleShell(BundleContext context, StreamMonitor monitor) {
		super(context);
		
		registerHandler(new OSGiStopCommandHandler(), "exit", "quit");
		this.monitor = monitor;
	}

	@Override
	public void start() {
		monitor.output("Starting console shell.");
		monitor.start();
	}

	@Override
	public void stop() {
		monitor.output("Stopping console shell.");
		monitor.stop();
	}
}
