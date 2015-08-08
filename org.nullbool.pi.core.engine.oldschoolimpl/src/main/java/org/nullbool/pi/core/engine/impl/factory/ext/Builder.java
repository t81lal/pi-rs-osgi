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

package org.nullbool.pi.core.engine.impl.factory.ext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bibl (don't ban me pls)
 * @created some time before 8/8/15
 */
public final class Builder {
	private final int id;
	private String relativeName;
	private int priority;
	private boolean runnable;
	private boolean optional;
	private List<Integer> require;
	private IActor<?> actor;
	
	public Builder(int id) {
		this.id = id;
		priority = -1;
		require = new ArrayList<Integer>();
	}

	public int getId() {
		return id;
	}

	public String getRelativeName() {
		return relativeName;
	}
	
	public int getPriority() {
		return priority;
	}

	public boolean isRunnable() {
		return runnable;
	}

	public List<Integer> getRequire() {
		return require;
	}
	
	public IActor<?> getActor() {
		return actor;
	}
	
	public Builder relativeName(String relativeName) {
		this.relativeName = relativeName;
		return this;
	}

	public Builder priority(int priority) {
		this.priority = priority;
		return this;
	}
	
	public Builder runnable(boolean b) {
		runnable = b;
		return this;
	}
	
	public Builder require(int... ids) {
		for(int i : ids) {
			require.add(i);
		}
		return this;
	}
	
	public Builder actor(IActor<?> actor) {
		this.actor = actor;
		return this;
	}
	
	public Builder optional(boolean optional) {
		this.optional = optional;
		return this;
	}
	
	public boolean isOptional() {
		return optional;
	}
}
