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

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.nullbool.osgi.shell.api.Shell;

/**
 * @author Bibl (don't ban me pls)
 * @created 14 Jun 2015 21:50:04
 */
public class StreamMonitor implements Runnable {

	private final Shell shell;
	private final InputStream is;
	private final PrintStream out;
	private final PrintStream err;
	private final Thread thisThread;

	public StreamMonitor(Shell shell) {
		this(shell, System.in, System.out, System.err);
	}

	public StreamMonitor(Shell shell, InputStream is, PrintStream out, PrintStream err) {
		this.shell = shell;
		this.is = is;
		this.out = out;
		this.err = err;
		
		thisThread = new Thread(this);
	}

	@Override
	public void run() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		do {
			String line = scanner.nextLine();
			try {
				shell.execute(line, is, out, err);
			} catch(Throwable t) {
				t.printStackTrace(err);
			}
		} while(!Thread.interrupted() && scanner.hasNext());
	}
	
	public void start () {
		thisThread.start();
	}
	
	public void stop() {
		try {
			thisThread.interrupt();
			thisThread.join();
		} catch(Throwable t) {
			t.printStackTrace(err);
		}
	}
	
	public void output(String msg) {
		out.println(msg);
	}
	
	public void error(String msg) {
		err.println(msg);
	}
}
