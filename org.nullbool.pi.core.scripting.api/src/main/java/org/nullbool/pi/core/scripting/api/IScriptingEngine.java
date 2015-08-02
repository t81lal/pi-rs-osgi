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