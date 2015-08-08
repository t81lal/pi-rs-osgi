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

package org.nullbool.pi.core.ui.menu.shell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.nullbool.pi.core.ui.api.IMenu;
import org.nullbool.pi.core.ui.api.IMenuDecorator;
import org.osgi.framework.BundleContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 18:48:21
 */
public class ShellMenuDecorator implements IMenuDecorator {

	private final BundleContext thisBundleContext;
	
	public ShellMenuDecorator(BundleContext thisBundleContext) {
		this.thisBundleContext = thisBundleContext;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IMenuDecorator#decorate(org.nullbool.pi.core.ui.api.IMenu)
	 */
	@Override
	public void decorate(IMenu menu) {
		JMenu dev = menu.findMenu("Developer");
		if(dev == null) {
			menu.registerMenu("Developer", dev = new JMenu("Developer"));
		}
		
		JMenuItem shellMenuItem = new JMenuItem("Console");
		
		ConsoleUI ui = new ConsoleUI(thisBundleContext);
		shellMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ui.setVisible(true);
			}
		});
				
		dev.add(shellMenuItem);
	}
}
