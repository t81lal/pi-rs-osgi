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

package org.nullbool.pi.core.painting.api;

import java.awt.Graphics;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BasicDelegateRenderer implements IDelegateRenderer {

	private final Set<IRenderer> attached;
	
	public BasicDelegateRenderer() {
		attached = new HashSet<IRenderer>();
	}
	
	@Override
	public void render(Graphics g) {
		synchronized (attached) {
			for(IRenderer r : attached) {
				r.render(g);
			}
		}
	}

	@Override
	public boolean attach(IRenderer r) {
		synchronized (attached) {
			return attached.add(r);
		}
	}

	@Override
	public boolean deattach(IRenderer r) {
		synchronized (attached) {
			return attached.remove(r);
		}
	}

	@Override
	public Collection<IRenderer> getAttached() {
		return attached;
	}
}
