package org.nullbool.pi.core.engine.api.transform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultAPIHelper implements IAPIHelper {

	private final String base;
	private final Map<String, String> canons;
	private final Map<String, List<String>> supers;

	public DefaultAPIHelper() {
		this(null);
	}
	
	public DefaultAPIHelper(String base) {
		this.base = base;
		canons = new HashMap<String, String>();
		supers = new HashMap<String, List<String>>();
	}

	@Override
	public String accessorBase() {
		return base;
	}

	@Override
	public void mapSuperInterfaces(String klass, String[] superInterfaces, boolean overwrite) {
		List<String> col = null;
		if(supers.containsKey(klass)) {
			col = supers.get(klass);
			if(overwrite) {
				col.clear();
			}
		} else {
			col = new ArrayList<String>();
			supers.put(klass, col);
		}
		
		for(String si : superInterfaces) {
			col.add(si);
		}
	}

	@Override
	public String[] superInterfaces(String klass) {
		if(!supers.containsKey(klass)) {
			return new String[0];
		} else {
			List<String> col = supers.get(klass);
			return col.toArray(new String[0]);
		}
	}

	@Override
	public String canonicalName(String klass) {
		return canons.get(klass);
	}

	@Override
	public void remapCanonicalname(String klass, String newName) {
		canons.put(klass, newName);
	}

	@Override
	public Collection<String> simpleNames() {
		return canons.keySet();
	}
}