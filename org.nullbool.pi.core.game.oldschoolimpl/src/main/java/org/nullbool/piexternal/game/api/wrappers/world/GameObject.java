package org.nullbool.piexternal.game.api.wrappers.world;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import org.nullbool.piexternal.game.api.Calculations;
import org.nullbool.piexternal.game.api.DefinitionLoader;
import org.nullbool.piexternal.game.api.OldschoolClient;
import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;
import org.nullbool.piexternal.game.api.accessors.world.IGameObject;
import org.nullbool.piexternal.game.api.meta.Interactable;
import org.nullbool.piexternal.game.api.meta.RSTile;
import org.nullbool.piexternal.game.api.wrappers.definition.ObjectDefinition;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:24:49 <br>
 */
public class GameObject implements IGameObject, Interactable, Comparable<GameObject> {

	private final IGameObject _obj;
	private final ObjectDefinition definition;
	private final double initDistance;

	public GameObject(IGameObject _obj) {
		this._obj = _obj;
		definition = DefinitionLoader.get().getObjectdefinition(getId());
		initDistance = Calculations.distance(getTile());
	}

	public int getId() {
		return getHash() >> 14 & 32767;
	}

	@Override
	public int getFlags() {
		return _obj.getFlags();
	}

	@Override
	public int getHash() {
		return _obj.getHash();
	}

	@Override
	public int getHeight() {
		return _obj.getHeight();
	}

	@Override
	public int getLocalX() {
		return _obj.getLocalX();
	}

	@Override
	public int getLocalY() {
		return _obj.getLocalY();
	}

	@Override
	public IRenderable getMarkedRenderable() {
		return _obj.getMarkedRenderable();
	}

	@Override
	public int getOrientation() {
		return _obj.getOrientation();
	}

	@Override
	public int getPlane() {
		return _obj.getPlane();
	}

	@Override
	public int getStrictX() {
		return _obj.getStrictX();
	}

	@Override
	public int getStrictY() {
		return _obj.getStrictY();
	}

	@Override
	public int getWidth() {
		return _obj.getWidth();
	}

	public boolean isOnScreen() {
		return Calculations.isOnScreen(getScreenPoint());
	}

	public boolean isWalkable() {
		return definition != null && definition.isWalkable();
	}

	public int getAnimation() {
		return definition != null ? definition.getObjMapScene() : 0;
	}

	public int getMapScene() {
		return definition != null ? definition.getObjMapScene() : 0;
	}

	public String getName() {
		return definition != null ? definition.getName() : "";
	}

	public String[] getActions() {
		return definition != null ? definition.getActions() : new String[0];
	}

	public int getX() {
		return getLocalX();
	}

	public int getY() {
		return getLocalY();
	}

	public int getTileX() {
		return getX() + OldschoolClient.getBaseX();
	}

	public int getTileY() {
		return getY() + OldschoolClient.getBaseY();
	}

	public RSTile getTile() {
		return new RSTile(getTileX(), getTileY(), getPlane());
	}

	public int getWidth1() {
		return definition != null ? definition.getWidth() - 1 : 1;
	}

	public int getHeight1() {
		return definition != null ? definition.getHeight() - 1 : 1;
	}

	public Rectangle2D getRectangle() {
		return Calculations.getRectangle2d(getTile(), getWidth1(), getHeight1());
	}

	public int dist() {
		return (int) Calculations.distance(getTile());
	}

	@Override
	public Point getScreenPoint() {
		return Calculations.getCenterPoint(getTile(), 1.0D);
	}

	@Override
	public int compareTo(GameObject obj) {
		return Double.compare(initDistance, initDistance);
	}

	@Override
	public Point getActionPoint(int x, int y) {
//		Model m = getModel();
//		Point point = m != null ? m.getPoint() : getScreenPoint();
		Point point = getScreenPoint();
		point.x += x;
		point.y += y;
		return point;
	}
}