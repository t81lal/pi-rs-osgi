package org.nullbool.pi.core.scripting.api.loader;

import java.util.Set;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 8 Mar 2015 at 19:03:59 <br>
 */
public abstract class RunnableResourceLocation<T> {

	public abstract Set<T> load() throws Exception;
}