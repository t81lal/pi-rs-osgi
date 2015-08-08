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

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.nullbool.pi.core.painting.api.IDelegateRenderer;
import org.nullbool.pi.core.painting.api.IRenderer;

/**
 * @author Bibl (don't ban me pls)
 * @created some time before 8/8/15
 */
public class ContextDelegateRenderer implements IDelegateRenderer {

	private final Map<ThreadGroup, Set<IRenderer>> delegates = new HashMap<ThreadGroup, Set<IRenderer>>();
	private final Set<IRenderer> global = new HashSet<IRenderer>();
	
	public ContextDelegateRenderer() {
		attachGlobal(new IRenderer() {
			@Override
			public void render(Graphics g) {
				g.setColor(Color.RED);
				g.drawString("pi system renderer", 5, 14);
			}
		});
	}
	
	void attachGlobal(IRenderer r) {
		synchronized (global) {
			global.add(r);	
		}
	}
	
	void unattachGlobal(IRenderer r) {
		synchronized (global) {
			global.add(r);	
		}
	}
	
	protected synchronized Set<IRenderer> current() {
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		if(tg == null) {
			throw new IllegalStateException();
		}
		
		Set<IRenderer> set = delegates.get(tg);
		if(set == null) {
			set = new HashSet<IRenderer>();
			delegates.put(tg, set);
			System.out.printf("Registered new working set on threadgroup %s.%n", tg);
		}
		
		return set;
	}
	
	protected synchronized void render(Graphics g, Set<IRenderer> renderers) {
		for(IRenderer r : renderers) {
			r.render(g);
		}
	}
	
	@Override
	public void render(Graphics g) {
		render(g, current());
		render(g, global);
	}

	@Override
	public boolean attach(IRenderer r) {
		return current().add(r);
	}

	@Override
	public boolean deattach(IRenderer r) {
		return current().remove(r);
	}

	@Override
	public Collection<IRenderer> getAttached() {
		return current();
	}
}
