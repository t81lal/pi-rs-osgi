/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

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
