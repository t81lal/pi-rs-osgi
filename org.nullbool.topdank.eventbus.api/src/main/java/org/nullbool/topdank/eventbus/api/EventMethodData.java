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

package org.nullbool.topdank.eventbus.api;

import java.lang.reflect.Method;

public final class EventMethodData {

	public EventPriority priority;
	public Object src;
	public Method method;

	public EventMethodData(EventPriority priority, Object src, Method method) {
		super();
		this.priority = priority;
		this.src = src;
		this.method = method;
		if (!method.isAccessible())
			method.setAccessible(true);
	}
}
