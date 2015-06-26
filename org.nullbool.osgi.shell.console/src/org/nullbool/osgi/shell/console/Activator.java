package org.nullbool.osgi.shell.console;

import java.util.Hashtable;

import org.nullbool.osgi.shell.api.Shell;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 14 Jun 2015 22:35:23
 */
public class Activator implements BundleActivator {

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		// Maybe factory?
		context.registerService(Shell.class.getName(), new ConsoleShell(context), new Hashtable<String, String>());
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		// OSGi unregisters the server by default if the bundle is unloaded.
	}
}