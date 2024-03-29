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

package org.nullbool.piexternal.game.api;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.piexternal.game.api.accessors.IOldschoolClient;

public final class Keyboard {
	private static final Map<ThreadGroup, Keyboard> INSTANCES = new HashMap<ThreadGroup, Keyboard>();

	public static Keyboard get() {
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		if(INSTANCES.containsKey(tg)) {
			return INSTANCES.get(tg);
		}
		
		IClientContext<IGameClient> cxt = OldschoolClient.current();
		if(tg != cxt.getThreadGroup()) {
			throw new IllegalStateException("ThreadGroups don't match?");
		}
		
		IOldschoolClient client = (IOldschoolClient) cxt.getClient();
		Canvas canvas = client.getCanvas();
		Keyboard keyboard = new Keyboard(canvas);
		INSTANCES.put(tg, keyboard);
		
		return keyboard;
	}
	
	private Canvas canvas;
	private KeyListener rsKeyListener;
	
	public Keyboard(Canvas canvas) {
		this.canvas = canvas;
	}

	public void sendMousePressed(char c) {
		this.sendEvent(c, true);
	}

	public void sendMouseRelease(char c) {
		this.sendEvent(c, false);
	}

	private char getKeyChar(char c) {
		return c >= 36 && c <= 40?'\u0000':c;
	}

	public void writeText(String text) {
		char[] var5;
		int var4 = (var5 = text.toCharArray()).length;

		for(int var3 = 0; var3 < var4; ++var3) {
			char c = var5[var3];
			KeyEvent event = new KeyEvent(this.canvas, 400, System.currentTimeMillis(), 0, 0, c);
			this.rsKeyListener.keyTyped(event);
			Utilities.sleep(80, 130);
		}

	}

	public void sendEvent(char key, boolean pressed) {
		KeyEvent event = new KeyEvent(this.canvas, pressed?401:402, System.currentTimeMillis(), 0, key, this.getKeyChar(key));
		this.canvas.dispatchEvent(event);
	}
}
