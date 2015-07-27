package org.nullbool.pi.core.painting.api;

import java.awt.Graphics;
import java.util.Collection;

public interface IDelegateRenderer extends IRenderer {

	@Override
	public void render(Graphics g);
	
	public boolean attach(IRenderer r);
	
	public boolean deattach(IRenderer r);
	
	Collection<IRenderer> getAttached();
}