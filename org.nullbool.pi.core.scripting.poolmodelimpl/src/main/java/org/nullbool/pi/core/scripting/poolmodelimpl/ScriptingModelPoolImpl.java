/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

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
