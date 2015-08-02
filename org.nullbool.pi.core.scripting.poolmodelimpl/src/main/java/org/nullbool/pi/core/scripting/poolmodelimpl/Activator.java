package org.nullbool.pi.core.scripting.poolmodelimpl;

import org.nullbool.pi.core.scripting.api.loader.IScriptingPoolModel;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		bundleContext.registerService(IScriptingPoolModel.class, new ScriptingModelPoolImpl(), null);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
	}
}