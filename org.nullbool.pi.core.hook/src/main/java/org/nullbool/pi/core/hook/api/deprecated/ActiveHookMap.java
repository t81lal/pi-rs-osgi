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

package org.nullbool.pi.core.hook.api.deprecated;

import java.util.ArrayList;
import java.util.List;

import org.nullbool.pi.core.hook.api.ClassHook;
import org.nullbool.pi.core.hook.api.HookMap;

@Deprecated
public class ActiveHookMap extends HookMap {

	private List<AbstractActor> actors;
	
	public ActiveHookMap() {
		super();
		actors = new ArrayList<AbstractActor>();
	}
	
	public ActiveHookMap(int ver) {
		super(ver);
		actors = new ArrayList<AbstractActor>();
	}
	
	public ActiveHookMap(List<ClassHook> classes) {
		super(classes);
		actors = new ArrayList<AbstractActor>();
	}

	public ActiveHookMap(int ver, List<ClassHook> classes) {
		super(ver, classes);
		actors = new ArrayList<AbstractActor>();
	}

	public List<AbstractActor> getActors() {
		return actors;
	}
	
	public void addActor(AbstractActor actor) {
		actors.add(actor);
	}

	public void setActors(List<AbstractActor> actor) {
		this.actors = actor;
	}
}
