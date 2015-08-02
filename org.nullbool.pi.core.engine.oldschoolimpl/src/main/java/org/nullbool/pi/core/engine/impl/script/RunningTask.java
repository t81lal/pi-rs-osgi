package org.nullbool.pi.core.engine.impl.script;

import java.util.Arrays;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.scripting.api.Task;
import org.nullbool.pi.core.scripting.api.klassmodel.HierarchalClassLoader;
import org.nullbool.pi.core.scripting.api.loader.ExternalResourceDefinition;
import org.nullbool.pi.core.scripting.api.loader.ResolvedDefinition;

/**
 * @author Bibl (don't ban me pls)
 * @created 27 Jun 2015 23:43:04
 */
public class RunningTask {

	private final ResolvedDefinition definition;
	private final Task taskInstance;
	private final HierarchalClassLoader classLoader;
	
	public RunningTask(IClientContext<IGameClient> context, ResolvedDefinition definition) throws Exception {
		this.definition = definition;

		classLoader = new HierarchalClassLoader(context.getContextClassLoader(), definition.getContents());
		Class<Task> klass = definition.getClass(classLoader, Task.class, definition.getDefinition().getKlassName());
		Task taskInstance = klass.newInstance();
		this.taskInstance = taskInstance;
	}
	
	public String[] getFormattedData() {
		if (definition == null)
			return new String[] { "error", "error", "error", "error" };
		ExternalResourceDefinition def = definition.getDefinition();
		return new String[] { Arrays.toString(def.getAuthors()), def.getName(), def.getVersion(), def.getDescription() };
	}

	public ResolvedDefinition getDefinition() {
		return definition;
	}

	public Task getTaskInstance() {
		return taskInstance;
	}

	public HierarchalClassLoader getClassLoader() {
		return classLoader;
	}
}