package org.nullbool.piexternal.game.api;

import java.util.Date;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 29 Jun 2015 13:30:53
 */
public class Activator implements BundleActivator {

	public static Activator activatorInstance = null;
	public static BundleContext bundleInstance = null;

	public Activator() {
		activatorInstance = this;
		System.out.println("Created: " + this.getClass().hashCode());
	}
	
	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext arg0) throws Exception {
		System.out.println("BActivator.start() " + new Date());
		System.out.println("Inited: " + this.getClass().hashCode());
		
		bundleInstance = arg0;
		
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext arg0) throws Exception {
		System.out.println("BActivator.stop() " + new Date());
		bundleInstance = null;
	}
}