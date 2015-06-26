package org.nullbool.pi.core.ui.api;

import java.awt.Component;
import java.awt.Dimension;

import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.piexternal.game.api.IGameClient;
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