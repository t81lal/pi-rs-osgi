package org.nullbool.taskqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Bibl (don't ban me pls)
 * @created 5 Jun 2015 22:37:36
 */
public class TaskQueueImpl extends Thread implements TaskQueue {

	private final Queue<Runnable> tasks = new LinkedList<Runnable>();
	private volatile boolean running = false;
	
	@Override
	public void shutdown() {
		synchronized (this) {
			running = false;
			this.notify();
		}
	}
	
	@Override
	public void submit(Runnable r) {
		synchronized (this) {
			tasks.add(r);
			this.notify();
		}
	}
	
	@Override
	public void run() {
		while(running) {
			synchronized (this) {
				if(tasks.isEmpty()) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}	
			}
			
			try {
				Runnable next = tasks.poll();
				runImpl(next);
			} catch(Throwable t) {
				t.printStackTrace();
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void runImpl(Runnable run) throws Throwable{
		run.run();
	}
	
	@Override
	public void start() {
		running = true;
		super.start();
	}
}