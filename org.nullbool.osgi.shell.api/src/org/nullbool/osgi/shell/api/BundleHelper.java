package org.nullbool.osgi.shell.api;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

/**
 * @author Bibl (don't ban me pls)
 * @created 14 Jun 2015 00:13:57
 */
public class BundleHelper {

	public static Bundle getBundle(BundleContext context, String id) {
		if (context == null) {
			throw new IllegalArgumentException("Null context.");
		}

		Bundle bundle = null;
		if (id != null) {
			try {
				bundle = context.getBundle(Long.parseLong(id.trim()));
			} catch (NumberFormatException e) {
				for (Bundle b : context.getBundles()) {
					String uid = b.getSymbolicName() + ":" + b.getHeaders().get(Constants.BUNDLE_VERSION);
					if (uid.equals(id.trim())) {
						bundle = b;
						break;
					}
				}
			}
		}
		if (bundle == null) {
			throw new IllegalArgumentException("No such bundle: " + id);
		}
		return bundle;
	}
}