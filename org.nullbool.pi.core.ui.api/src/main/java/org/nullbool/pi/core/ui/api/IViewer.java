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

package org.nullbool.pi.core.ui.api;

import java.awt.Component;
import java.awt.Dimension;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.osgi.framework.BundleContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 14 Jun 2015 22:00:19
 */
public abstract interface IViewer {

	public abstract void show(Component c);
	
	public abstract void acceptContext(IClientContext<IGameClient> context) throws UnsupportedOperationException;
	
	public abstract IClientContext<IGameClient> getActiveContext();
	
	public abstract void initComponents(BundleContext context);
	
	public abstract void display();
	
	public abstract void hide();

	public abstract void exit();
	
	public abstract Dimension getSize();
}
