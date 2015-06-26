package org.nullbool.pi.core.hook.serimpl;

import java.util.Hashtable;

import org.nullbool.pi.core.hook.api.serialisation.IMapSerialisationFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	
	public static final String CONTENT_TYPE_IMPL = "bser";
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void start(BundleContext context) throws Exception {
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put(IMapSerialisationFactory.CONTENT_TYPE, CONTENT_TYPE_IMPL);
		ht.put(IMapSerialisationFactory.ACTIVITY, "static");
		context.registerService(IMapSerialisationFactory.class, new StaticMapSeralisationFactoryImpl(), ht);
		
		//ht = new Hashtable<String, String>();
		//ht.put(IMapSerialisationFactory.CONTENT_TYPE, CONTENT_TYPE_IMPL);
		//ht.put(IMapSerialisationFactory.ACTIVITY, "active");
		//context.registerService(IMapSerialisationFactory.class, new ActiveMapSerialisationFactoryImpl(), ht);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		
	}
}