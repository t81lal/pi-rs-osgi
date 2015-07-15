package org.nullbool.taskqueue;

/**
 * @author Bibl (don't ban me pls)
 * @created 6 Jun 2015 08:07:51
 */
public abstract interface TaskQueue {

	public abstract void start();
	
	public abstract void shutdown();
	
	public abstract void submit(Runnable r);
}