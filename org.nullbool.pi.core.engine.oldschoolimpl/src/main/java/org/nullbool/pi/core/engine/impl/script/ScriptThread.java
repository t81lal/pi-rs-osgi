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

package org.nullbool.pi.core.engine.impl.script;

import java.util.Arrays;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.Script;
import org.nullbool.pi.core.scripting.api.klassmodel.HierarchalClassLoader;
import org.nullbool.pi.core.scripting.api.loader.ExternalResourceDefinition;
import org.nullbool.pi.core.scripting.api.loader.ResolvedDefinition;

/**
 * @author Bibl (don't ban me pls)
 * @created some time before 8/8/15
 */
public class ScriptThread extends Thread {

	private final IScriptingEngine engine;
	private final ResolvedDefinition scriptData;
	private final HierarchalClassLoader classLoader;
	private volatile Script activeScript;
	private volatile boolean running;
	private volatile boolean stopRequested;
	private volatile boolean interupted;
	private long startTime;

	public ScriptThread(IClientContext<IGameClient> context, IScriptingEngine engine, ResolvedDefinition scriptData) throws Exception {
		super(context.getThreadGroup(), String.format("script-(%s, %s)", scriptData.getDefinition().getName(), scriptData.getDefinition().getVersion()));
		this.engine = engine;
		this.scriptData = scriptData;
		
		classLoader = new HierarchalClassLoader(context.getContextClassLoader(), scriptData.getContents());
//		 classLoader = new ScriptClassLoader(getClass().getClassLoader(), scriptData.getContents());
				
		Class<Script> klass = scriptData.getClass(classLoader, Script.class, scriptData.getDefinition().getKlassName());
		activeScript = (Script) klass.newInstance();
		running = true;
		interupted = false;
		
		// context.classloader().children().add(classLoader);
		// hackScript();
	}
	
	public HierarchalClassLoader getClassLoader() {
		return classLoader;
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
		ExternalResourceDefinition def = scriptData.getDefinition();
		return new String[] { Arrays.toString(def.getAuthors()), def.getName(), def.getVersion(), def.getDescription() };
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
