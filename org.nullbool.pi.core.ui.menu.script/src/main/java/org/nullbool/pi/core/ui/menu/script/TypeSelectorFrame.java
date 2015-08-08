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

package org.nullbool.pi.core.ui.menu.script;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.nullbool.pi.core.scripting.api.loader.ResourceType;

/**
 * @author Bibl (don't ban me pls)
 * @created 2 Aug 2015 03:10:13
 */
public class TypeSelectorFrame extends JFrame {
	private static final long serialVersionUID = 1044485411663997852L;

	private final JComboBox<ResourceType> comboBox;
	private final JButton okButton;
	
	private TypeCallback callback;
	
	public TypeSelectorFrame() {
		super("T");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel cp = new JPanel();
		cp.setPreferredSize(new Dimension(136, 58));
		cp.setLayout(null);
		setContentPane(cp);
		
		comboBox = new JComboBox<ResourceType>();
		comboBox.setToolTipText("Resouce type.");
		comboBox.setFocusable(false);
		comboBox.setBounds(3, 3, 130, 25);
		cp.add(comboBox);
		
		okButton = new JButton("Done");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TypeCallback callback = TypeSelectorFrame.this.callback;
				// set the callback to null so that we can 
				// hide the frame then invoke it.
				TypeSelectorFrame.this.callback = null;
				setVisible(false);
				select(callback, (ResourceType) comboBox.getSelectedItem());
			}
		});
		okButton.setFocusable(false);
		okButton.setBounds(3, 30, 130, 25);
		cp.add(okButton);
		
		for(ResourceType t : ResourceType.values()) {
			comboBox.addItem(t);
		}
		
		setResizable(false);
		pack();
	}
	
	private void select(TypeCallback callback, ResourceType t) {
		new Thread() {
			@Override
			public void run() {
				callback.chose(t);
				TypeSelectorFrame.this.callback = null;
			}
		}.start();
	}
	
	@Override
	public void setVisible(boolean b) {
		/* If the frame is being closed and the,
		 * callback is not null, nothing was 
		 * chosen. */
		if(!b && callback != null) {
			select(callback, null);
		}
		super.setVisible(b);
	}
	
	public void setCallback(TypeCallback callback) {
		if(this.callback != null) {
			throw new IllegalStateException();
		}
		this.callback = callback;
	}
	
	public static interface TypeCallback {
		public void chose(ResourceType t);
	}
}
