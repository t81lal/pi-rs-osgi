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

package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.topdank.eventbus.api.Event;

/**
 * @author Bibl (don't ban me pls)
 * @created 7 Aug 2015 00:55:16
 */
public class EngineObjectEvent<T> implements Event {

	private final IScriptingEngine engine;
	private final T obj;
	
	public EngineObjectEvent(IScriptingEngine engine, T t) {
		this.engine = engine;
		this.obj = t;
	}

	public IScriptingEngine getEngine() {
		return engine;
	}

	public T get() {
		return obj;
	}
}
