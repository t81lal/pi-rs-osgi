package org.nullbool.pi.core.ui.api;

import javax.swing.JMenu;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 16:19:58
 */
public abstract interface IMenu {

	public abstract void registerMenu(String key, JMenu menu);
	
	public abstract JMenu findMenu(String key);
}