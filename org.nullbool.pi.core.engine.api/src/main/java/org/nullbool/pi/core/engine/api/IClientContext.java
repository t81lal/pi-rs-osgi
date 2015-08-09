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

package org.nullbool.pi.core.engine.api;

import java.applet.Applet;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.klassmodel.HierarchalClassLoader;
import org.nullbool.topdank.eventbus.api.Event;
import org.nullbool.topdank.eventbus.api.EventBus;

/**
 * A container/environment instance containing information about
 * the current game (and it's environment).
 * 
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 19:31:22
 */
public interface IClientContext<T extends IGameClient> {

	/**
	 * On creation, a ThreadGroup is created for the context and is
	 * used for every Thread created in this instance. This makes the
	 * threadgroup a good way of context-based operations and switching.
	 *  
	 * @see Thread
	 * @see ThreadGroup
	 * 
	 * @return The ThreadGroup.
	 */
	public ThreadGroup getThreadGroup();
	
	/**
	 * The EventBus binded to this context. Note that this can be either shared
	 * or created just for this instance depending on the intended funtionality.
	 * 
	 * @see EventBus
	 * @see Event
	 * 
	 * @return The EventBus.
	 */
	public EventBus getEventBus();
	
	/**
	 * @see IGameClient
	 * 
	 * @return The client as an IGameClient instance.
	 */
	public T getClient();
	
	/**
	 * @see Applet
	 * 
	 * @return The client as an Applet.
	 */
	public Applet getApplet();
	
	/**
	 * @see ClassLoader
	 * @see Applet
	 * 
	 * @return The associated ClassLoader for this context's Applet.
	 */
	public HierarchalClassLoader getContextClassLoader();
	
	/**
	 * @see IScriptingEngine
	 * 
	 * @return Either a shared of allocated IScriptingEngine instance.
	 */
	public IScriptingEngine getScriptingEngine();
	
	/**
	 * Called on context creation.
	 * 
	 * @throws Throwable
	 */
	public void init() throws Throwable;
	
	/**
	 * Called to request a shutdown.
	 */
	public void shutdown();
	
	/**
	 * @return Whether this context is still running/active.
	 */
	public boolean active();
}
