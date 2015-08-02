package org.nullbool.pi.core.scripting.poolmodelimpl;

import org.nullbool.pi.core.scripting.api.loader.BasicResourcePool;
import org.nullbool.pi.core.scripting.api.loader.IScriptingPoolModel;

/**
 * @author Bibl (don't ban me pls)
 * @created 2 Aug 2015 03:06:02
 */
public class ScriptingModelPoolImpl implements IScriptingPoolModel {

	private final BasicResourcePool scriptPool = new BasicResourcePool();
	private final BasicResourcePool taskPool = new BasicResourcePool();
	
	@Override
	public BasicResourcePool getPersistentScriptPool() {
		return scriptPool;
	}
	
	@Override
	public BasicResourcePool getPersistentTaskPool() {
		return taskPool;
	}
}