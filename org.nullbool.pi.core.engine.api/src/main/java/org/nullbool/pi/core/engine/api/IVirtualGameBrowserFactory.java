package org.nullbool.pi.core.engine.api;

import java.io.IOException;
import java.net.URL;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 01:33:51
 */
public abstract interface IVirtualGameBrowserFactory {

	public abstract IVirtualGameBrowser create(URL url) throws IOException;
}