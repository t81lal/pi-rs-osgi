package org.nullbool.pi.core.scripting.api.klassmodel;

import java.util.ArrayList;
import java.util.List;

public class DefaultClassFilter implements IFilter<String> {

	private final List<String> allowed = new ArrayList<String>();
	
	public DefaultClassFilter() {
		allowed.add("java.");
		allowed.add("sun.");
		allowed.add("com.sun");
		allowed.add("com.oracle");
		allowed.add("javax.");
	}
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.script.MasterScriptLoader.IFilter#accept(java.lang.Object)
	 */
	@Override
	public boolean accept(String t) {
		if(t == null || t.isEmpty())
			return false;
		
		for(String s : allowed) {
			if(t.startsWith(s))
				return true;
		}
		return false;
	}
}