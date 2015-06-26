package org.nullbool.pi.core.engine.api;

import java.util.Map;

/**
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 22:18:55
 */
public abstract interface IPageCrawler {

	public abstract Map<String, String> getGameParameters();
	
	public abstract Map<String, String> getAppletParameters();
}