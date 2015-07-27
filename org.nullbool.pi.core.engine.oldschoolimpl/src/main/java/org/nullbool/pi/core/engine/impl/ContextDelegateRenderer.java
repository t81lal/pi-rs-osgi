package org.nullbool.pi.core.engine.impl;

import java.awt.Graphics;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.nullbool.pi.core.painting.api.IDelegateRenderer;
import org.nullbool.pi.core.painting.api.IRenderer;

public class ContextDelegateRenderer implements IDelegateRenderer {

	private final Map<ThreadGroup, Set<IRenderer>> delegates = new HashMap<ThreadGroup, Set<IRenderer>>();
	private final Set<IRenderer> global = new HashSet<IRenderer>();
	
	void attachGlobal(IRenderer r) {
		synchronized (global) {
			global.add(r);	
		}
	}
	
	void unattachGlobal(IRenderer r) {
		synchronized (global) {
			global.add(r);	
		}
	}
	
	protected synchronized Set<IRenderer> current() {
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		if(tg == null) {
			throw new IllegalStateException();
		}
		
		Set<IRenderer> set = delegates.get(tg);
		if(set == null) {
			set = new HashSet<IRenderer>();
			delegates.put(tg, set);
			System.out.printf("Registered new working set on threadgroup %s.%n", tg);
		}
		
		return set;
	}
	
	protected synchronized void render(Graphics g, Set<IRenderer> renderers) {
		for(IRenderer r : renderers) {
			r.render(g);
		}
	}
	
	@Override
	public void render(Graphics g) {
		render(g, current());
		render(g, global);
	}

	@Override
	public boolean attach(IRenderer r) {
		return current().add(r);
	}

	@Override
	public boolean deattach(IRenderer r) {
		return current().remove(r);
	}

	@Override
	public Collection<IRenderer> getAttached() {
		return current();
	}
}