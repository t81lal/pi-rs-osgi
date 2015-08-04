package org.nullbool.pi.core.scripting.poolmodelimpl;

import org.nullbool.pi.constants.ResourceConstants;
import org.nullbool.pi.core.scripting.api.loader.BasicResourcePool;
import org.nullbool.pi.core.scripting.api.loader.DescribedManifestResourceLocation;
import org.nullbool.pi.core.scripting.api.loader.IScriptingPoolModel;
import org.nullbool.pi.core.scripting.api.loader.finder.JarInfoFolderSearchFinderStrategy;

/**
 * @author Bibl (don't ban me pls)
 * @created 2 Aug 2015 03:06:02
 */
public class ScriptingModelPoolImpl implements IScriptingPoolModel {

	private final BasicResourcePool scriptPool = new BasicResourcePool();
	private final BasicResourcePool taskPool = new BasicResourcePool();
	
	public ScriptingModelPoolImpl() {
		scriptPool.add(new DescribedManifestResourceLocation(new JarInfoFolderSearchFinderStrategy(false, ResourceConstants.SCRIPTS_DIR)));
		taskPool.add(new DescribedManifestResourceLocation(new JarInfoFolderSearchFinderStrategy(false, ResourceConstants.TASKS_DIR)));
	}
	
	@Override
	public BasicResourcePool getPersistentScriptPool() {
		return scriptPool;
	}
	
	@Override
	public BasicResourcePool getPersistentTaskPool() {
		return taskPool;
	}
}