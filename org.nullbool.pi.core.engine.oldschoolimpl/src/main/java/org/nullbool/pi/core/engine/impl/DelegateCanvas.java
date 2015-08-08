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

package org.nullbool.pi.core.engine.impl;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.nullbool.pi.core.painting.api.IDelegateRenderer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author Bibl (don't ban me pls)
 * @created some time before 8/8/15
 */
public class DelegateCanvas extends Canvas {

	private final IDelegateRenderer renderer;
	private Object swapLock = new Object();
	private BufferedImage botBuffer;
	private BufferedImage gamBuffer;
	private static final long serialVersionUID = -7979768392848832471L;

	public DelegateCanvas() {
		this(new Dimension(765, 503));
	}

	public DelegateCanvas(Dimension size) {
		update(size);
		
		// FIXME: dangling service
		BundleContext context = FrameworkUtil.getBundle(DelegateCanvas.class).getBundleContext();
		ServiceReference<IDelegateRenderer> ref = context.getServiceReference(IDelegateRenderer.class);
		IDelegateRenderer dr = context.getService(ref);
		context.ungetService(ref);
		renderer = dr;
	}
	
	public void update(Dimension size) {
		synchronized (swapLock) {
			botBuffer = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
			gamBuffer = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		}
	}

	@Override
	public Graphics getGraphics() {
		synchronized (swapLock) {
			Graphics botPaint = botBuffer.getGraphics();
			botPaint.drawImage(gamBuffer, 0, 0, null);
			botPaint.setFont(new Font("Arial", Font.PLAIN, 13));
			delegate(botPaint);
			super.getGraphics().drawImage(botBuffer, 0, 0, null);
			return gamBuffer.getGraphics();
		}
	}

	protected void delegate(Graphics g) {
		renderer.render(g);
	}
}
