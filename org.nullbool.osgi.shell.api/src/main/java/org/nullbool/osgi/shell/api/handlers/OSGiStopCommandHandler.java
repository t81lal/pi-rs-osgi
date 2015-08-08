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

package org.nullbool.osgi.shell.api.handlers;

import java.io.InputStream;
import java.io.PrintStream;

import org.nullbool.osgi.shell.api.ICommandHandler;
import org.nullbool.osgi.shell.api.Shell;
import org.nullbool.osgi.util.ShutdownHelper;

/**
 * @author Bibl (don't ban me pls)
 * @created 14 Jun 2015 22:30:40
 */
public class OSGiStopCommandHandler implements ICommandHandler {

	/* (non-Javadoc)
	 * @see org.nullbool.osgi.shell.api.ICommandHandler#execute(org.nullbool.osgi.shell.api.Shell, java.lang.String, java.io.InputStream, java.io.PrintStream, java.io.PrintStream)
	 */
	@Override
	public void execute(Shell shell, String args, InputStream in, PrintStream out, PrintStream err) throws Throwable {
		ShutdownHelper.shutdown(shell.getContext());
	}
}
