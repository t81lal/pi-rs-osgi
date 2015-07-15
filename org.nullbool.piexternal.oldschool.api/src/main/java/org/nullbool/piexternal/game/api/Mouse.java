package org.nullbool.piexternal.game.api;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.piexternal.game.api.accessors.IOldschoolClient;

/**
 * @author Bibl (don't ban me pls)
 * @created 7 Jul 2015 15:28:41
 */

public final class Mouse extends MouseAdapter {

	private static final Map<ThreadGroup, Mouse> INSTANCES = new HashMap<ThreadGroup, Mouse>();
	
	public static Mouse get() {
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		if(INSTANCES.containsKey(tg)) {
			return INSTANCES.get(tg);
		}
		
		IClientContext<IGameClient> cxt = OldschoolClient.current();
		if(tg != cxt.threadGroup()) {
			throw new IllegalStateException("ThreadGroups don't match?");
		}
		
		IOldschoolClient client = (IOldschoolClient) cxt.client();
		Canvas canvas = client.getCanvas();
		Mouse mouse = new Mouse(canvas);
		INSTANCES.put(tg, mouse);
		
		return mouse;
	}
	
	private Canvas canvas;
	private MouseListener rsMouseListener;
	private MouseMotionListener rsMouseMotionListener;
	private boolean pressed;
	private int x;
	private int y;

	Mouse(Canvas canvas) {
		this.canvas = canvas;
		this.canvas.addMouseMotionListener(this);
		this.rsMouseListener = canvas.getMouseListeners()[0];
		this.rsMouseMotionListener = canvas.getMouseMotionListeners()[0];
	}

	public void move(Point p) {
		if (p.x != -1 && !p.equals(this.getPoint())) {
			Point[] path;
			int len = (path = this.generate(this.getPoint(), p)).length;

			for (int i = 0; i < len; ++i) {
				Point point = path[i];
				MouseEvent event = this.create(503, 0, p);
				this.rsMouseMotionListener.mouseMoved(event);
				Utilities.sleep((long) Utilities.next(5, 17));
				this.x = point.x;
				this.y = point.y;
			}
		}
	}

	public void click(Point p, boolean t) {
		if (p != null && p.x != -1) {
			MouseEvent e = this.create(501, t ? 4 : 16, p);
			MouseEvent c = this.create(502, 0, p);
			this.rsMouseListener.mousePressed(e);
			this.rsMouseListener.mouseReleased(c);
		}
	}

	public MouseEvent create(int type, int clickType, Point p) {
		this.pressed = 501 == type;
		boolean c = 503 == type;
		long time = System.currentTimeMillis();
		Utilities.sleep((long) (c ? 0 : Utilities.next(80, 170)));
		return new MouseEvent(this.canvas, type, time, clickType, p.x, p.y, 1, false);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.x = e.getPoint().x;
		this.y = e.getPoint().y;
	}

	public boolean hasBeenPressed() {
		return this.pressed;
	}

	public Point getPoint() {
		return new Point(this.x, this.y);
	}

	public Point[] getLinearPoints(Point dest) {
		double distance = (new Point(this.x, this.y)).distance(dest);
		double stepX = (dest.getX() - (double) this.x) / distance;
		double stepY = (dest.getY() - (double) this.y) / distance;
		LinkedList<Point> points = new LinkedList<Point>();
		int i = 1;
		int v = -1;

		for (int h = -1; (new Point(v, h)).distance(dest) > 1.0D; ++i) {
			v = (int) ((double) this.x + (double) i * stepX);
			h = (int) ((double) this.y + (double) i * stepY);
			points.add(new Point(v, h));
		}

		points.addLast(dest);
		return (Point[]) points.toArray(new Point[points.size()]);
	}

	public Point[] generate(Point a, Point b) {
		byte multiplier = 1;
		int steps = (int) (Math.sqrt(a.distance(b)) * 3.0D);
		double x = Math.toRadians(180.0D / (double) steps);
		double y = Math.toRadians(180.0D / (double) steps);
		double waviness = a.distance(b) < 120.0D ? 2.8D : 0.9D;
		double xOffset = (double) (b.x - a.x) / (Math.sqrt(a.distance(b)) * 3.0D);
		double yOffset = (double) (b.y - a.y) / (Math.sqrt(a.distance(b)) * 3.0D);
		Point[] path = new Point[steps + 2];
		boolean cond = Math.random() >= 0.5D;
		x *= (double) (cond ? (int) Math.floor(Math.random() * waviness + 1.0D) : 1);
		cond = Math.random() >= 0.5D;
		y *= (double) (cond ? (int) Math.floor(Math.random() * waviness + 1.0D) : 1);
		cond = Math.random() >= 0.5D;
		int rand = multiplier * (cond ? -1 : 1);
		double offset = Math.random() * (1.6D + Math.sqrt((double) steps)) + 6.0D + 2.0D;

		for (int i = 0; i < steps + 1; ++i) {
			int v = rand * (int) (offset * Math.sin(x * (double) i));
			int v1 = rand * (int) (offset * Math.sin(y * (double) i));
			int x1 = a.x + (int) (xOffset * (double) i) + v;
			int y1 = a.y + (int) (yOffset * (double) i) + v1;
			path[i] = new Point(x1, y1);
		}

		path[path.length - 1] = b;
		path[0] = a;
		return path;
	}
}