package org.nullbool.osgi.shell.api.handlers;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.nullbool.osgi.shell.api.ICommandHandler;
import org.nullbool.osgi.shell.api.Shell;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

/**
 * @author Bibl (don't ban me pls)
 * @created 14 Jun 2015 00:21:16
 */
public class BundleStopCommandHandler implements ICommandHandler {

	/* (non-Javadoc)
	 * @see org.nullbool.osgi.shell.api.ICommandHandler#execute(org.nullbool.osgi.shell.api.Shell, java.lang.String, java.io.InputStream, java.io.PrintStream, java.io.PrintStream)
	 */
	@Override
	public void execute(Shell shell, String args, InputStream in, PrintStream out, PrintStream err) throws Throwable {
		Bundle bundle = shell.getBundle(args);

		if (shell.isCritical(bundle)) {
			new SafeBundleStopThread(bundle, out).start();
		} else {
			bundle.stop();
		}
	}
	
	private static final class SafeBundleStopThread extends Thread {
		private final Bundle bundle;
		private final OutputStream out;

		public SafeBundleStopThread(Bundle bundle, OutputStream out) {
			super("SelfStopThread Bundle " + bundle.getBundleId());
			this.bundle = bundle;
			this.out = out;
		}

		@Override
		public void run() {
			try {
				bundle.stop();
			} catch (BundleException e) {
				e.printStackTrace(new PrintStream(out));
			}
		}
	}
}