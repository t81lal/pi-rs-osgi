package org.nullbool.osgi.shell.api.handlers;

import java.io.InputStream;
import java.io.PrintStream;

import org.nullbool.osgi.shell.api.ICommandHandler;
import org.nullbool.osgi.shell.api.Shell;
import org.osgi.framework.Bundle;

/**
 * @author Bibl (don't ban me pls)
 * @created 14 Jun 2015 00:18:45
 */
public class BundleStartCommandHandler implements ICommandHandler {

	/* (non-Javadoc)
	 * @see org.nullbool.osgi.shell.api.ICommandHandler#execute(org.nullbool.osgi.shell.api.Shell, java.lang.String, java.io.InputStream, java.io.PrintStream, java.io.PrintStream)
	 */
	@Override
	public void execute(Shell shell, String args, InputStream in, PrintStream out, PrintStream err) throws Throwable {
		Bundle bundle = shell.getBundle(args);
		bundle.start();
	}
}