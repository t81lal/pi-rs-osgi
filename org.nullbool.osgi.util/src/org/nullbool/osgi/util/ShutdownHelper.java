package org.nullbool.osgi.util;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 00:06:48
 */
public class ShutdownHelper {

	public static void shutdown(BundleContext context) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					context.getBundle(0).stop();
				} catch (BundleException e) {
					e.printStackTrace();
					System.err.println("Error stopping system bundle.");
				}

				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Forcing VM shutdown.");
				System.exit(1);
			}
		}).start();
	}
}