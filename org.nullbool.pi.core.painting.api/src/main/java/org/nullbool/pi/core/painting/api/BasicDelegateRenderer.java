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