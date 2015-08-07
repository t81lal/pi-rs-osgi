package org.nullbool.pi.core.ui.menu.script;

import java.util.Iterator;
import java.util.Set;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.engine.api.IContextRegistry;
import org.nullbool.pi.core.scripting.api.loader.IScriptingPoolModel;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author Bibl (don't ban me pls)
 * @created 2 Aug 2015 00:36:50
 */
public class Util {

	public static IClientContext<?>[] contexts() {
		BundleContext bundleContext = context();
		ServiceReference<IContextRegistry> cxtSvcRef = bundleContext.getServiceReference(IContextRegistry.class);
		IContextRegistry contextRegistry = bundleContext.getService(cxtSvcRef);
		bundleContext.ungetService(cxtSvcRef);
		
		Set<IClientContext<IGameClient>> contexts = contextRegistry.retrieveAll();
		IClientContext<?>[] engines = new IClientContext[contexts.size()];
		Iterator<IClientContext<IGameClient>> it = contexts.iterator();

		int i = 0;
		while (it.hasNext()) {
			engines[i++] = it.next();
		}


		return engines;
	}
	
	public static IScriptingPoolModel model() {
		BundleContext bundleContext = FrameworkUtil.getBundle(IScriptingPoolModel.class).getBundleContext();
		ServiceReference<IScriptingPoolModel> modelSvcRef = bundleContext.getServiceReference(IScriptingPoolModel.class);
		IScriptingPoolModel model = bundleContext.getService(modelSvcRef);
		bundleContext.ungetService(modelSvcRef);
		return model;
	}
	
	public static BundleContext context() {
		BundleContext bundleContext = FrameworkUtil.getBundle(ScriptMenuDecorator.class).getBundleContext();
		return bundleContext;
	}
}