package org.nullbool.piexternal.game.api;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import org.nullbool.piexternal.game.api.meta.RSTile;
import org.nullbool.piexternal.game.api.wrappers.entity.Actor;
import org.nullbool.piexternal.game.api.wrappers.entity.Player;

public class Calculations {
	
	private static int[] SIN_CURVES = new int[2048];
	private static int[] COS_CURVES = new int[2048];
	private static final Rectangle SCREEN = new Rectangle(4, 4, 512, 334);
	private static final Rectangle FULL_GAME_SCREEN = new Rectangle(0, 0, 765, 496);

	static {
		 for(int i = 0; i < 2048; ++i) {
	         SIN_CURVES[i] = (int)(65536.0D * Math.sin((double)i * 0.0030679615D));
	         COS_CURVES[i] = (int)(65536.0D * Math.cos((double)i * 0.0030679615D));
	      }
	}
	
	public static int[] getSinCurves() {
		return SIN_CURVES;
	}

	public static int[] getCosCurves() {
		return COS_CURVES;
	}

	public static Point getCenterPoint(RSTile tile) {
		return getCenterPoint(tile, 1.0D);
	}

	public static boolean isOnScreen(Point point) {
		return SCREEN.contains(point);
	}

	public static boolean isOnGameScreen(Point point) {
		return FULL_GAME_SCREEN.contains(point);
	}

	public static boolean isOnScreen(Actor<?> actor) {
		return isOnScreen(actor.getScreenPoint());
	}

	public static double distance(Actor<?> actor) {
		return distance(actor.getTile());
	}

	public static double distance(RSTile tile) {
		return distance(OldschoolClient.getLocalPlayer().getTile(), tile);
	}

	public static double distance(RSTile first, RSTile sec) {
		return Math.hypot((double) (first.getX() - sec.getX()), (double) (first.getY() - sec.getY()));
	}

	public static boolean isRectOnScreen(Rectangle2D rect) {
		return SCREEN.contains(rect);
	}

	public static Point getCenterPoint(RSTile tile, double d) {
		int plane = OldschoolClient.getPlane();
		Point p1 = pointOnScreen(tile, 0.0D, 0.0D, plane);
		Point p2 = pointOnScreen(tile, d, 0.0D, plane);
		Point p3 = pointOnScreen(tile, d, d, plane);
		Point p4 = pointOnScreen(tile, 0.0D, d, plane);
		Polygon p = new Polygon();
		p.addPoint(p1.x, p1.y);
		p.addPoint(p2.x, p2.y);
		p.addPoint(p3.x, p3.y);
		p.addPoint(p4.x, p4.y);
		Rectangle2D r = p.getBounds2D();
		return new Point((int) r.getCenterX(), (int) r.getCenterY());
	}

	public static Point getCenterPoint(RSTile ti, double d, int x, int y) {
		if (x == 0 && y == 0) {
			return getCenterPoint(ti, d);
		} else {
			Rectangle2D r = getRectangle2d(ti, x, y);
			return new Point((int) r.getCenterX(), (int) r.getCenterY());
		}
	}

	public static Rectangle2D getRectangle2d(RSTile ti, int x, int y) {
		Polygon p = new Polygon();
		int f = OldschoolClient.getPlane();
		List<Point> ps = new ArrayList<Point>();
		RSTile t = new RSTile(ti.getX() - 1, ti.getY() - 1, f);
		ps.add(pointOnScreen(t, 0.0D, 0.0D, f));
		ps.add(pointOnScreen(new RSTile(t.getX() + x, t.getY(), f), 1.0D, 0.0D, f));
		ps.add(pointOnScreen(new RSTile(t.getX() + x, t.getY() + y, f), 1.0D, 1.0D, f));
		ps.add(pointOnScreen(new RSTile(t.getX(), t.getY() + y, f), 0.0D, 1.0D, f));
		ps.stream().forEach((po) -> {
			p.addPoint(po.x, po.y);
		});
		return p.getBounds2D();
	}

	public static Polygon getTilePolygon(RSTile tile) {
		int plane = OldschoolClient.getPlane();
		Point p1 = pointOnScreen(tile, 0.0D, 0.0D, plane);
		Point p2 = pointOnScreen(tile, 1.0D, 0.0D, plane);
		Point p3 = pointOnScreen(tile, 1.0D, 1.0D, plane);
		Point p4 = pointOnScreen(tile, 0.0D, 1.0D, plane);
		Polygon polygon = new Polygon();
		polygon.addPoint(p1.x, p1.y);
		polygon.addPoint(p2.x, p2.y);
		polygon.addPoint(p3.x, p3.y);
		polygon.addPoint(p4.x, p4.y);
		return polygon;
	}

	public static int getTileHeight(int plane, int x, int y) {
		int xx = x >> 7;
		int yy = y >> 7;
		int[][][] h = OldschoolClient.getTileHeights();
		if (xx >= 0 && yy >= 0 && xx <= 103 && yy <= 103) {
			int aa = h[plane][xx][yy] * (128 - (x & 127)) + h[plane][xx + 1][yy] * (x & 127) >> 7;
			int ab = h[plane][xx][yy + 1] * (128 - (x & 127)) + h[plane][xx + 1][yy + 1] * (x & 127) >> 7;
			return aa * (128 - (y & 127)) + ab * (y & 127) >> 7;
		} else {
			return 0;
		}
	}

	public static Point worldToMap(RSTile rsTile) {
		Player player = OldschoolClient.getLocalPlayer();
		int xMapTile = rsTile.getX() - OldschoolClient.getBaseX();
		int yMapTile = rsTile.getY() - OldschoolClient.getBaseY();
		int regionX = xMapTile * 4 + 2 - player.getLocalX() / 32;
		int regionY = yMapTile * 4 + 2 - player.getLocalY() / 32;
		int mapScale = OldschoolClient.getMapScale();
		int mapOffset = OldschoolClient.getMapOffset();
		int angle = OldschoolClient.getMapAngle() + mapScale & 2047;
		int j = regionX * regionX + regionY * regionY;
		if (j > 6400) {
			return new Point(-1, -1);
		} else {
			int sin = SIN_CURVES[angle] * 256 / (mapOffset + 256);
			int cos = COS_CURVES[angle] * 256 / (mapOffset + 256);
			int xMap = regionY * sin + regionX * cos >> 16;
			int yMap = regionY * cos - regionX * sin >> 16;
			return new Point(644 + xMap, 83 - yMap);
		}
	}

	public static Point pointByCam(int x, int y, int height) {
		if (x >= 128 && y >= 128 && x <= 13056 && y <= 13056) {
			int z = getTileHeight(OldschoolClient.getPlane(), x, y) - height;
			x -= OldschoolClient.getCameraX();
			z -= OldschoolClient.getCameraZ();
			y -= OldschoolClient.getCameraY();
			int pitch_sin = SIN_CURVES[OldschoolClient.getCameraPitch()];
			int pitch_cos = COS_CURVES[OldschoolClient.getCameraPitch()];
			int yaw_sin = SIN_CURVES[OldschoolClient.getCameraYaw()];
			int yaw_cos = COS_CURVES[OldschoolClient.getCameraYaw()];
			int _angle = y * yaw_sin + x * yaw_cos >> 16;
			y = y * yaw_cos - x * yaw_sin >> 16;
			x = _angle;
			_angle = z * pitch_cos - y * pitch_sin >> 16;
			y = z * pitch_sin + y * pitch_cos >> 16;
			if (y >= 50) {
				Point p = new Point(260 + (x << 9) / y, (_angle << 9) / y + 171);
				return isOnScreen(p) ? p : new Point(-1, -1);
			} else {
				return new Point(-1, -1);
			}
		} else {
			return new Point(-1, -1);
		}
	}

	public static Point pointOnScreen(RSTile tile, double dX, double dY, int height) {
		return pointByCam((int) (((double) (tile.getX() - OldschoolClient.getBaseX()) + dX) * 128.0D),
				(int) (((double) (tile.getY() - OldschoolClient.getBaseY()) + dY) * 128.0D), height);
	}
}