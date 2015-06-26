package org.nullbool.pi.core.engine.api.transform;

import java.util.Collection;

public abstract interface IAPIHelper {

	public abstract String accessorBase();
	
	public abstract void mapSuperInterfaces(String klass, String[] superInterfaces, boolean overwrite);
	
	public abstract String[] superInterfaces(String klass);
	
	public abstract String canonicalName(String klass);
	
	public abstract void remapCanonicalname(String klass, String newName);
	
	public abstract Collection<String> simpleNames();
}