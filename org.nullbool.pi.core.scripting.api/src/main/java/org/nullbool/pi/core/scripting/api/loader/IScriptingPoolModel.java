package org.nullbool.pi.core.scripting.api.loader;

/**
 * @author Bibl (don't ban me pls)
 * @created 2 Aug 2015 02:45:13
 */
public interface IScriptingPoolModel {

	public BasicResourcePool getPersistentScriptPool();
	
	public BasicResourcePool getPersistentTaskPool();
}