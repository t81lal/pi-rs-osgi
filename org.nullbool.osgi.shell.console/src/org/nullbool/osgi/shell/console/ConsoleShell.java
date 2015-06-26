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