package org.nullbool.pi.core.engine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.engine.impl.script.RunningTask;
import org.nullbool.pi.core.engine.impl.script.ScriptThread;
import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.Script;
import org.nullbool.pi.core.scripting.api.Task;
import org.nullbool.pi.core.scripting.api.event.ScriptEngineRefresh;
import org.nullbool.pi.core.scripting.api.event.ScriptInteruptEvent;
import org.nullbool.pi.core.scripting.api.event.ScriptStartEvent;
import org.nullbool.pi.core.scripting.api.event.ScriptStopEvent;
import org.nullbool.pi.core.scripting.api.event.TaskStartEvent;
import org.nullbool.pi.core.scripting.api.event.TaskStopEvent;
import org.nullbool.pi.core.scripting.api.loader.IScriptingPoolModel;
import org.nullbool.pi.core.scripting.api.loader.ResolvedDefinition;
import org.nullbool.pi.core.scripting.api.loader.ResourceType;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 04:00:50
 */
public class DefaultScriptingEngine implements IScriptingEngine {

	private IClientContext<IGameClient> context;
	private ScriptThread script;
	private List<RunningTask> activeTasks;

	public DefaultScriptingEngine() {
		activeTasks = new ArrayList<RunningTask>();
	}
	
	public void initContext(IClientContext<IGameClient> context) {
		this.context = context;
	}

	@Override
	public Task startTask(ResolvedDefinition taskData) {
		if(taskData.getType() != ResourceType.TASK)
			return null;
		
		try {
			RunningTask task = new RunningTask(context, taskData);
			context.getContextClassLoader().children().add(task.getClassLoader());
			
			synchronized (activeTasks) {
				activeTasks.add(task);
			}
			// TODO: marker for event call
			Task instance = task.getTaskInstance();
			context.getEventBus().dispatch(new TaskStartEvent(this, instance));
			return instance;
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
		context.getEventBus().dispatch(new TaskStopEvent(this, task));
	}

	@Override
	public void stopAllTasks() {
		synchronized (activeTasks) {
			ListIterator<RunningTask> it = activeTasks.listIterator();
			while (it.hasNext()) {
				RunningTask task = it.next();
				it.remove();
				// TODO: marker for event call
				context.getEventBus().dispatch(new TaskStopEvent(this, task.getTaskInstance()));
			}
		}
	}

	@Override
	public boolean tasksRunning() {
		return activeTasks.size() > 0;
	}

	@Override
	public Script startScript(ResolvedDefinition scriptData) {
		if(scriptData.getType() != ResourceType.SCRIPT)
			return null;
		// TODO: dependencies
		/* 1. If there are no api dependencies, pass.
		 * 2. If there are, verify them. */
//		LoadedLibrary[] apis = null;
//		
//		String[] keys = scriptData.getDefinition().getApiKeys();
//		if(keys != null && keys.length > 0) {
//			apis = new LoadedLibrary[keys.length];
//			for(int i=0; i < keys.length; i++) {
//				String key = keys[i];
//				try {
//					LoadedLibrary lib = apiCache.find(key);
//					if(lib == null) {
//						throw new UnsupportedOperationException();
//					}
//					
//					apis[i] = lib;
//				} catch(UnsupportedOperationException e) {
//					System.out.flush();
//					System.err.flush();
//					System.err.printf("Error resolving library (%s).%n", key);
//					e.printStackTrace();
//					return null;
//				}
//			}
//		}
//		
//		if(apis == null)
//			apis = new LoadedLibrary[0];
		
		if(script == null) {
			Script internalScript = null;
			try {
				this.script = new ScriptThread(context, this, scriptData);
//				HierarchalClassLoader classLoader = this.script.getClassLoader();
//				for(LoadedLibrary lib : apis) {
//					classLoader.children().add(lib.getClassLoader());
//				}
				this.script.start();

				internalScript = this.script.getActiveScript();
				context.getEventBus().dispatch(new ScriptStartEvent(this, internalScript));
				return internalScript;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
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
		return this.script != null ? this.script.getFormattedData() : null;
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
					context.getEventBus().dispatch(new ScriptInteruptEvent(this, internalScript, state));
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
					context.getEventBus().dispatch(new ScriptInteruptEvent(this, internalScript, state));
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
				context.getEventBus().dispatch(new ScriptStopEvent(this, internalScript));
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
		List<Task> taskList = new ArrayList<Task>();
		for(RunningTask t : activeTasks) {
			taskList.add(t.getTaskInstance());
		}
		return taskList;
	}

	@Override
	public void refresh() {
		// TODO: stop exceptions propogating upwards.
		try {
			Bundle bundle = FrameworkUtil.getBundle(getClass());
			BundleContext cxt = bundle.getBundleContext();
			ServiceReference<IScriptingPoolModel> modelSvcRef = cxt.getServiceReference(IScriptingPoolModel.class);
			IScriptingPoolModel model = cxt.getService(modelSvcRef);
			cxt.ungetService(modelSvcRef);
			
			model.getPersistentScriptPool().refresh();
			model.getPersistentTaskPool().refresh();
		} finally {
			// TODO: marker for event call
			context.getEventBus().dispatch(new ScriptEngineRefresh(this));
		}
	}
	
	@Override
	public String[] getActiveTaskData(Task task) {
		if(task == null)
			return null;
		for(RunningTask t : activeTasks) {
			if(t.getTaskInstance() == task) {
				return t.getFormattedData();
			}
		}
		return null;
	}
}