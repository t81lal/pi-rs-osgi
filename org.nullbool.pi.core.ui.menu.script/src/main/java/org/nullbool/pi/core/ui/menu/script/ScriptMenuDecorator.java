package org.nullbool.pi.core.ui.menu.script;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.nullbool.pi.core.ui.api.IMenu;
import org.nullbool.pi.core.ui.api.IMenuDecorator;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 18:46:19
 */
public class ScriptMenuDecorator implements IMenuDecorator {

	@Override
	public void decorate(IMenu menu) {
		JMenu script = menu.findMenu("Script");
		if (script == null) {
			menu.registerMenu("Script", script = new JMenu("Script"));
		}

		ScriptViewer viewer = new ScriptViewer();
		JMenuItem viewMenuItem = new JMenuItem("View");
		viewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 viewer.show();
			}
		});

		script.add(viewMenuItem);
	}

	public void error(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
}