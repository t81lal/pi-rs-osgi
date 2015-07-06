package org.nullbool.pi.core.scripting.api.loader;

/**
 * @author Bibl (don't ban me pls)
 * @created 27 Jun 2015 12:53:06
 */
public enum ResourceType {

	SCRIPT(), TASK();
	
	public static ResourceType byName(String name) {
		if(name == null || name.isEmpty())
			throw new IllegalArgumentException();
		
		name = name.toUpperCase();
		return valueOf(name);
	}
}