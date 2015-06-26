package org.nullbool.pi.core.engine.registryimpl;

import org.nullbool.pi.core.engine.api.IContextRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 03:46:39
 */
public class Activator implements BundleActivator {

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		context.registerService(IContextRegistry.class, new BasicContextRegistry(), null);
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		
	}
}