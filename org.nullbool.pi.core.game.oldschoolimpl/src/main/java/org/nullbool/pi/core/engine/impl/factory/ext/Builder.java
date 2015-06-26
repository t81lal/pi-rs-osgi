package org.nullbool.pi.core.engine.impl.factory.ext;

import java.util.ArrayList;
import java.util.List;

public final class Builder {
	private final int id;
	private String relativeName;
	private int priority;
	private boolean runnable;
	private List<Integer> require;
	private IActor actor;
	
	public Builder(int id) {
		this.id = id;
		priority = -1;
		require = new ArrayList<Integer>();
	}

	public int getId() {
		return id;
	}

	public String getRelativeName() {
		return relativeName;
	}
	
	public int getPriority() {
		return priority;
	}

	public boolean isRunnable() {
		return runnable;
	}

	public List<Integer> getRequire() {
		return require;
	}
	
	public IActor getActor() {
		return actor;
	}
	
	public Builder relativeName(String relativeName) {
		this.relativeName = relativeName;
		return this;
	}

	public Builder priority(int priority) {
		this.priority = priority;
		return this;
	}
	
	public Builder runnable(boolean b) {
		runnable = b;
		return this;
	}
	
	public Builder require(int... ids) {
		for(int i : ids) {
			require.add(i);
		}
		return this;
	}
	
	public Builder actor(IActor actor) {
		this.actor = actor;
		return this;
	}
}