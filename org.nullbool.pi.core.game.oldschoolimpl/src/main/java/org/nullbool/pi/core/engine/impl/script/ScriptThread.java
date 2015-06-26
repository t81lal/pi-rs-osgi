package org.nullbool.pi.core.engine.impl.script;

import java.util.Arrays;

import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.Script;
import org.nullbool.pi.core.scripting.api.klassmodel.ScriptClassLoader;
import org.nullbool.pi.core.scripting.api.loader.ResourceDefinition;
import org.nullbool.piexternal.game.api.IGameClient;

public class ScriptThread extends Thread {

	private final IScriptingEngine engine;
	private final ResourceDefinition scriptData;
	private final ScriptClassLoader classLoader;
	private volatile Script activeScript;
	private volatile boolean running;
	private volatile boolean stopRequested;
	private volatile boolean interupted;
	private long startTime;

	public ScriptThread(IClientContext<IGameClient> context, IScriptingEngine engine, ResourceDefinition scriptData) throws Exception {
		super(context.threadGroup(), String.format("script-(%s, %s)", scriptData.getName(), scriptData.getVersion()));
		this.engine = engine;
		this.scriptData = scriptData;
		
		classLoader = new ScriptClassLoader(scriptData.getContents());
		
		Class<Script> klass = scriptData.getClass(classLoader, Script.class, scriptData.getKlassName());
		activeScript = (Script) klass.newInstance();
		running = true;
		interupted = false;
		
		// context.classloader().children().add(classLoader);
		// hackScript();
	}

	/*private void hackScript() {
		try {
			Field defField = Script.class.getDeclaredField("definition");

			// reduce access from final to normal
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(defField, defField.getModifiers() & ~Modifier.FINAL);

			defField.setAccessible(true);
			defField.set(activeScript, scriptData);
			defField.setAccessible(false);
			// set it back to final
			modifiersField.setInt(defField, defField.getModifiers() | Modifier.FINAL);
		} catch (Throwable t) {
			System.err.println("Wtf this isn't good: ");
			t.printStackTrace();
		}
	}*/
	
	public IScriptingEngine parentEngine() {
		return engine;
	}

	public String[] getFormattedData() {
		if (scriptData == null)
			return new String[] { "error", "error", "error", "error" };
		return new String[] { Arrays.toString(scriptData.getAuthors()), scriptData.getName(), scriptData.getVersion(), scriptData.getDescription() };
	}

	@Override
	public void run() {
		try {
			// TODO: 22/3/15, dude I think this shit is broken
			// TODO: fixup
			// final boolean hasPainter = activeScript instanceof Painter;
			// final Painter paint = hasPainter ? (Painter) activeScript : null;
			// context.getBotCanvas().setPaintListener(paint);

			activeScript.onStart();
			// activeScript.setApiProvider(context.getApiProvider());

			startTime = System.currentTimeMillis();
			long lastScriptLoop = startTime;

			while ((lastScriptLoop >= 0) && running && !stopRequested) {
				if (stopRequested)
					break;
				if (interupted && running) {
					lastScriptLoop = 100;
				} else if ((activeScript != null) && running) {
					running = activeScript.canRun();// only if not interrupted
					lastScriptLoop = activeScript.run();
				} else {
					break;
				}
				sleep(lastScriptLoop >= 0 ? lastScriptLoop : 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			running = false;
			engine.stopActiveScript();
			if (activeScript != null)
				activeScript.onStop();
			activeScript = null;
		}
	}

	public void interupt(boolean state) {
		interupted = state;
	}

	public void requestStop() {
		if (!stopRequested)
			stopRequested = true;
		// throw new IllegalStateException("Script has already been stopped.");
	}

	public boolean running() {
		return running && (activeScript != null);
	}

	public boolean interupted() {
		return interupted;
	}

	public Script getActiveScript() {
		return activeScript;
	}
}