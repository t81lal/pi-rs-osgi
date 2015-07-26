package org.nullbool.pi.core.ui.legacy;

import org.nullbool.pi.core.ui.api.IViewer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 11:32:47
 */
public class Activator implements BundleActivator {
	
	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		try {
			context.registerService(IViewer.class.getName(), new LegacyViewer(context), null);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {

	}
}