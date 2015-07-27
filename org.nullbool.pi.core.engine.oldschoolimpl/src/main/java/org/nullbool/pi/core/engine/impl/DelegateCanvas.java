package org.nullbool.pi.core.engine.impl;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.nullbool.pi.core.painting.api.IDelegateRenderer;
import org.nullbool.pi.core.painting.api.IRenderer;
import org.nullbool.pi.core.painting.api.PaintManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class DelegateCanvas extends Canvas {
	private static final long serialVersionUID = -7979768392848832471L;

	private final IDelegateRenderer renderer;
	private BufferedImage botBuffer;
	private BufferedImage gamBuffer;

	public DelegateCanvas() {
		this(new Dimension(765, 503));
	}
	
	public DelegateCanvas(Dimension size) {
		botBuffer = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		gamBuffer = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		
		BundleContext context = FrameworkUtil.getBundle(DelegateCanvas.class).getBundleContext();
		ServiceReference<IDelegateRenderer> ref = context.getServiceReference(IDelegateRenderer.class);
		IDelegateRenderer dr = context.getService(ref);
		context.ungetService(ref);
		
		renderer = dr;
		
		PaintManager.getInstance().attach(new IRenderer() {
			@Override
			public void render(Graphics g) {
				g.setColor(Color.RED);
				g.drawString("thank our lord hitler", 250, 250);
			}
		});
	}
	
	@Override
	public Graphics getGraphics() {
		Graphics botPaint = botBuffer.getGraphics();
		botPaint.drawImage(gamBuffer, 0, 0, null);
		botPaint.setFont(new Font("Arial", Font.PLAIN, 13));

		delegate(botPaint);
		super.getGraphics().drawImage(botBuffer, 0, 0, null);
		return gamBuffer.getGraphics();
	}

	protected void delegate(Graphics g) {
		g.setColor(Color.ORANGE);
		g.drawString("happy gay day", 150, 150);
		
		renderer.render(g);
	}
}