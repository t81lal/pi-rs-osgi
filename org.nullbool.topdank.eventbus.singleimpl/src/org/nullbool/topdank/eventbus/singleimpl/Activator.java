/**
 * 
 */
package org.nullbool.topdank.eventbus.singleimpl;

import java.util.Hashtable;

import org.nullbool.topdank.eventbus.api.EventBus;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 00:58:15
 */
public class Activator implements BundleActivator {

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		Hashtable<String, Object> ht = new Hashtable<String, Object>();
		ht.put(EventBus.THREAD_TYPE, "single");
		
		context.registerService(EventBus.class, new ServiceFactory<EventBus>() {
			@Override
			public EventBus getService(Bundle bundle, ServiceRegistration<EventBus> svcRef) {
				return new SingleThreadEventBus();
			}

			@Override
			public void ungetService(Bundle bundle, ServiceRegistration<EventBus> svcRef, EventBus bus) {
				if(bus instanceof SingleThreadEventBus) {
					((SingleThreadEventBus) bus).destroy();
				}
			}
		}, ht);
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		
	}
}