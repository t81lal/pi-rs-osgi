package org.nullbool.pi.core.engine.impl.factory;

import java.io.IOException;
import java.net.URL;

import org.nullbool.pi.core.engine.api.IVirtualGameBrowser;
import org.nullbool.pi.core.engine.api.IVirtualGameBrowserFactory;
import org.nullbool.pi.core.engine.impl.VirtualRunescapeBrowser;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 01:36:56
 */
public class VirtualRunescapeGameBrowserFactory implements IVirtualGameBrowserFactory {

	@Override
	public IVirtualGameBrowser create(URL url) throws IOException {
		return new VirtualRunescapeBrowser(url);
	}
}