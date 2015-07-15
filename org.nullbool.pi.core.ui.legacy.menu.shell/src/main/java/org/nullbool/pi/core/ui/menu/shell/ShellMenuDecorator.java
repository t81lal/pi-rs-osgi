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