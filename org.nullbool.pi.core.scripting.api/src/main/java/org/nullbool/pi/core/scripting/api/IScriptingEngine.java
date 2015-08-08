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

package org.nullbool.pi.core.scripting.api;

import java.util.List;

import org.nullbool.pi.core.scripting.api.loader.ResolvedDefinition;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 03:54:21
 */
public interface IScriptingEngine {

	public Task startTask(ResolvedDefinition taskData);
	
	public void stopTask(Task task);
	
	public void stopAllTasks();
	
	public boolean tasksRunning();
	
	public Script startScript(ResolvedDefinition scriptData);
	
	public boolean isScriptRunning();
	
	public void interuptActiveScript(boolean state);
	
	public void interuptActiveScript();

	public void stopActiveScript();
	
	public Script getActiveScript();
	
	public String[] getActiveScriptData();
	
	public List<Task> getActiveTasks();
	
	public String[] getActiveTaskData(Task task);
	
	public void refresh();
}
