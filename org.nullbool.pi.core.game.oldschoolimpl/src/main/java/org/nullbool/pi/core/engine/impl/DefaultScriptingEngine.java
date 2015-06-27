package org.nullbool.pi.core.engine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.engine.impl.script.ScriptThread;
import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.Script;
import org.nullbool.pi.core.scripting.api.Task;
import org.nullbool.pi.core.scripting.api.event.ScriptEngineRefresh;
import org.nullbool.pi.core.scripting.api.event.ScriptInteruptEvent;
import org.nullbool.pi.core.scripting.api.event.ScriptStartEvent;
import org.nullbool.pi.core.scripting.api.event.ScriptStopRequestEvent;
import org.nullbool.pi.core.scripting.api.event.TaskStartEvent;
import org.nullbool.pi.core.scripting.api.event.TaskStopEvent;
import org.nullbool.pi.core.scripting.api.klassmodel.ScriptClassLoader;
import org.nullbool.pi.core.scripting.api.loader.RefreshableResourcePool;
import org.nullbool.pi.core.scripting.api.loader.ResourceDefinition;
import org.nullbool.pi.core.scripting.api.loader.RunnableResourceLocation;
import org.nullbool.piexternal.game.api.IGameClient;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 04:00:50
 */
public class DefaultScriptingEngine implements IScriptingEngine {

	private IClientContext<IGameClient> context;
	private final RefreshableResourcePool<ResourceDefinition, RunnableResourceLocation<ResourceDefinition>> scriptpool;
	private final RefreshableResourcePool<ResourceDefinition, RunnableResourceLocation<ResourceDefinition>> taskpool;

	private ScriptThread script;
	private List<Task> activeTasks;

	public DefaultScriptingEngine(
			RefreshableResourcePool<ResourceDefinition, RunnableResourceLocation<ResourceDefinition>> scriptpool,
			RefreshableResourcePool<ResourceDefinition, RunnableResourceLocation<ResourceDefinition>> taskpool) {
		this.scriptpool = scriptpool;
		this.taskpool = taskpool;

		activeTasks = new ArrayList<Task>();
	}
	
	public void initContext(IClientContext<IGameClient> context) {
		this.context = context;
	}

	@Override
	public Task startTask(ResourceDefinition taskData) {
		try {
			ScriptClassLoader classLoader = new ScriptClassLoader(context.classloader(), taskData.getContents());
			context.classloader().children().add(classLoader);

			Class<Task> klass = taskData.getClass(classLoader, Task.class, taskData.getKlassName());
			Task task = klass.newInstance();
			synchronized (activeTasks) {
				activeTasks.add(task);
			}
			// TODO: marker for event call
			context.eventBus().dispatch(new TaskStartEvent(task));
			return task;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void stopTask(Task task) {
		synchronized (activeTasks) {
			activeTasks.remove(task);
		}
		// TODO: marker for event call
		context.eventBus().dispatch(new TaskStopEvent(task));
	}

	@Override
	public void stopAllTasks() {
		synchronized (activeTasks) {
			ListIterator<Task> it = activeTasks.listIterator();
			while (it.hasNext()) {
				Task task = it.next();
				it.remove();
				// TODO: marker for event call
				context.eventBus().dispatch(new TaskStopEvent(task));
			}
		}
	}

	@Override
	public boolean tasksRunning() {
		return activeTasks.size() > 0;
	}

	@Override
	public Script startScript(ResourceDefinition scriptData) {
		if(script == null) {
			Script internalScript = null;
			try {
				this.script = new ScriptThread(context, this, scriptData);
				this.script.start();

				internalScript = this.script.getActiveScript();
				return internalScript;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				// TODO: marker for event call
				context.eventBus().dispatch(new ScriptStartEvent(internalScript));
			}
		} else {
			return null;
		}
	}

	@Override
	public boolean isScriptRunning() {
		return this.script != null && this.script.running();
	}

	@Override
	public String[] getActiveScriptData() {
		return this.script != null ? this.script.getFormattedData() : new String[4];
	}

	@Override
	public void interuptActiveScript(boolean state) {
		if(script != null) {
			Script internalScript = this.script.getActiveScript();
			if(this.script.running()) {
				try {
					this.script.interupt(state);
				} catch (RuntimeException e) {
					e.printStackTrace();
				} finally {
					// TODO: marker for event call
					context.eventBus().dispatch(new ScriptInteruptEvent(internalScript, state));
				}
			} else {
				this.script = null;
			}
		}
	}

	@Override
	public void interuptActiveScript() {
		if(this.script != null) {
			Script internalScript = this.script.getActiveScript();
			if(this.script.running()) {
				boolean state = !this.script.interupted();
				try {
					this.script.interupt(state);
				} catch (RuntimeException e) {
					e.printStackTrace();
				} finally {
					// TODO: marker for event call
					context.eventBus().dispatch(new ScriptInteruptEvent(internalScript, state));
				}
			} else {
				this.script = null;
			}
		}
	}

	@Override
	public void stopActiveScript() {
		if(this.script != null) {
			Script internalScript = this.script.getActiveScript();
			try {
				//if(internalScript != null) {
				//	this.script.requestStop();
				//}
				this.script.requestStop();
			} catch (RuntimeException e) {
				e.printStackTrace();
			} finally {
				context.eventBus().dispatch(new ScriptStopRequestEvent(internalScript));
				this.script = null;
			}
		}
	}

	@Override
	public Script getActiveScript() {
		return this.script != null ? this.script.getActiveScript() : null;
	}

	@Override
	public List<Task> getActiveTasks() {
		return activeTasks;
	}

	@Override
	public RefreshableResourcePool<ResourceDefinition, RunnableResourceLocation<ResourceDefinition>> getScriptPool() {
		return scriptpool;
	}

	@Override
	public RefreshableResourcePool<ResourceDefinition, RunnableResourceLocation<ResourceDefinition>> getTaskPool() {
		return taskpool;
	}

	@Override
	public void refresh() {
		try {
			scriptpool.refresh();
			taskpool.refresh();
		} finally {
			// TODO: marker for event call
			context.eventBus().dispatch(new ScriptEngineRefresh(this));
		}
	}
}