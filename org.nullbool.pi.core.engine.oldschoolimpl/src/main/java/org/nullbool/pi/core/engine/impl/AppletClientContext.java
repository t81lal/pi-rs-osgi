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

package org.nullbool.pi.core.engine.impl;

import java.applet.Applet;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.klassmodel.HierarchalClassLoader;
import org.nullbool.topdank.eventbus.api.EventBus;

/**
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 19:59:49
 */
public class AppletClientContext<T extends IGameClient> implements IClientContext<T> {

	private final ThreadGroup tgroup;
	private final HierarchalClassLoader classLoader;
	private final EventBus bus;
	private final IScriptingEngine scriptingEngine;
	private final T client;

	public AppletClientContext(ThreadGroup tgroup, HierarchalClassLoader classLoader, EventBus bus, IScriptingEngine scriptingEngine, T client) {
		this.tgroup = tgroup;
		this.classLoader = classLoader;
		this.bus = bus;
		this.scriptingEngine = scriptingEngine;
		this.client = client;
	}
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.IClientContext#threadGroup()
	 */
	@Override
	public ThreadGroup getThreadGroup() {
		return tgroup;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.IClientContext#client()
	 */
	@Override
	public T getClient() {
		return client;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.IClientContext#applet()
	 */
	@Override
	public Applet getApplet() {
		return (Applet) client;
	}
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.IClientContext#classloader()
	 */
	@Override
	public HierarchalClassLoader getContextClassLoader() {
		return classLoader;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.IClientContext#eventBus()
	 */
	@Override
	public EventBus getEventBus() {
		return bus;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.engine.api.IClientContext#scriptingEngine()
	 */
	@Override
	public IScriptingEngine getScriptingEngine() {
		return scriptingEngine;
	}
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.IClientContext#init()
	 */
	@Override
	public void init() throws Throwable {
		getApplet().init();
		getApplet().start();
	}
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.IClientContext#shutdown()
	 */
	@Override
	public void shutdown() {
		getApplet().stop();
		getApplet().destroy();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.IClientContext#active()
	 */
	@Override
	public boolean active() {
		return getApplet().isActive();
	}
}
