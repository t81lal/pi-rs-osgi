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

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 13 Jun 2015 23:54:22
 */
public abstract class Shell {

	private final BundleContext context;
	private final Map<String, ICommandHandler> handlers;

	public Shell(BundleContext context) {
		this.context = context;
		this.handlers = new HashMap<String, ICommandHandler>();
	}

	public void registerHandler(ICommandHandler handler, String... cmds) {
		for (String cmd : cmds) {
			handlers.put(cmd != null ? cmd.toUpperCase() : cmd, handler);
		}
	}

	public void unregisterHandler(String... cmds) {
		for (String cmd : cmds) {
			handlers.remove(cmd != null ? cmd.toUpperCase() : cmd);
		}
	}

	public void unregisterHandlers(ICommandHandler handler) {
		Iterator<Entry<String, ICommandHandler>> it = handlers.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, ICommandHandler> e = it.next();
			if (e.getValue().equals(handler))
				it.remove();
		}
	}

	public void unregisterAll() {
		handlers.clear();
	}

	public ICommandHandler retrieveHandler(String cmd) {
		return handlers.get(cmd != null ? cmd.toUpperCase() : cmd);
	}

	public void execute(String args, InputStream in, PrintStream out, PrintStream err) {
		int idx = args.indexOf(' ');
		boolean hasArgs = (idx > 0);
		String cmd = hasArgs ? args.substring(0, idx) : args;
		ICommandHandler handler = retrieveHandler(cmd);
		
		if (handler != null) {
			try {
				handler.execute(this, hasArgs ? args.substring(idx) : null, in, out, err);
			} catch (Throwable t) {
				t.printStackTrace(err);
				err.printf("Unable to execute: %s.%n", args);
			}
		}

		if (handler == null && !(args.trim().length() == 0)) {
			err.printf("Unknown command: %s.%n", args);
		}
	}

	public Bundle getBundle(String id) {
		return BundleHelper.getBundle(this.context, id);
	}
	
	public boolean isCritical(Bundle bundle) {
		return context.getBundle() == bundle;
	}
	
	public BundleContext getContext() {
		return context;
	}

	public abstract void start();

	public abstract void stop();
}
