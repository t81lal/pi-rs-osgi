package org.nullbool.pi.core.engine.impl;

import org.nullbool.pi.core.engine.api.IContextFactory;
import org.nullbool.pi.core.engine.api.IVirtualGameBrowserFactory;
import org.nullbool.pi.core.engine.impl.factory.OldschoolContextFactory;
import org.nullbool.pi.core.engine.impl.factory.VirtualRunescapeGameBrowserFactory;
import org.nullbool.pi.core.painting.api.IDelegateRenderer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 6 Jul 2015 07:02:47
 */
public class Activator implements BundleActivator {

	
	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		context.registerService(IVirtualGameBrowserFactory.class, new VirtualRunescapeGameBrowserFactory(), null);
		context.registerService(IContextFactory.class, new OldschoolContextFactory(), null);
		context.registerService(IDelegateRenderer.class, new ContextDelegateRenderer(), null);
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		
	}
}