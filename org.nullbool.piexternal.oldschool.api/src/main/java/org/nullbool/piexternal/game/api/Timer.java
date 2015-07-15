package org.nullbool.piexternal.game.api;

public class Timer {
	private long startTime = System.currentTimeMillis();
	private long sleepingTime;

	public Timer(long sleepingTime) {
		this.sleepingTime = sleepingTime;
	}

	public long getElapsedTime() {
		return System.currentTimeMillis() - this.startTime;
	}

	public void reset() {
		this.startTime = System.currentTimeMillis();
	}

	public boolean isRunning() {
		return this.getElapsedTime() < this.sleepingTime;
	}
}