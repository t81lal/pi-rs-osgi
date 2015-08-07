package org.nullbool.pi.core.engine.api;


/**
 * @author Bibl (don't ban me pls)
 * @created 7 Aug 2015 01:02:11
 */
public interface ContextListener {

	void registered(IClientContext<?> cxt);
	
	void unregistered(IClientContext<?> cxt);
}