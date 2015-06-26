package org.nullbool.pi.core.ui.menu.script;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 20:15:24
 */
public class TokenRegistry {

	private final Map<String, String> mapping = new HashMap<String, String>();
	
	public String find(String key) {
		return mapping.get(key.toUpperCase());
	}
	
	public void register(String key, String val) {
		mapping.put(key.toUpperCase(), val);
	}
}