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