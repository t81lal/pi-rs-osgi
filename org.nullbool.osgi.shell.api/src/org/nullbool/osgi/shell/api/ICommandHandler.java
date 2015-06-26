package org.nullbool.osgi.shell.api;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author Bibl (don't ban me pls)
 * @created 14 Jun 2015 00:01:26
 */
public abstract interface ICommandHandler {

	public abstract void execute(Shell shell, String args, InputStream in, PrintStream out, PrintStream err) throws Throwable;
}