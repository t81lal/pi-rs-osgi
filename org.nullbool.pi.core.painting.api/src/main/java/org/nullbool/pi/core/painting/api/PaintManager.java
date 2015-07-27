package org.nullbool.pi.core.painting.api;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public final class PaintManager {

	private PaintManager() {
		throw new UnsupportedOperationException();
	}
	
	public static IDelegateRenderer getInstance() {
		IDelegateRenderer dr = getInstance0();
		if(dr != null) {
			return dr;
		} else {
			throw new UnsupportedOperationException("no renderer installed.");
		}
	}
	
	private static IDelegateRenderer getInstance0() {
		Bundle bundle = FrameworkUtil.getBundle(PaintManager.class);
		if(bundle == null) {
			throw new IllegalStateException("null bundle.");
		}
		
		BundleContext context = bundle.getBundleContext();
		ServiceReference<IDelegateRenderer> drSvcRef = context.getServiceReference(IDelegateRenderer.class);
		IDelegateRenderer dr = context.getService(drSvcRef);
		context.ungetService(drSvcRef);
		return dr;
	}
}