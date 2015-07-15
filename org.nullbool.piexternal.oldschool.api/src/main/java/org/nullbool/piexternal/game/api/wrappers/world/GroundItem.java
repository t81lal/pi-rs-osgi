package org.nullbool.piexternal.game.api.wrappers.world;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import org.nullbool.piexternal.game.api.meta.RSTile;
import org.nullbool.piexternal.game.api.Calculations;
import org.nullbool.piexternal.game.api.OldschoolClient;
import org.nullbool.piexternal.game.api.wrappers.entity.Renderable;
import org.nullbool.piexternal.game.api.accessors.world.IGroundItem;
import org.nullbool.piexternal.game.api.wrappers.definition.ItemDefinition;
/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:27:12 <br>
 */
public class GroundItem extends Renderable<IGroundItem> implements IGroundItem {

	private RSTile tile;
	private ItemDefinition definition;

	public GroundItem(IGroundItem _item, RSTile tile) {
		super(_item);
		this.tile = tile;
		this.definition = new ItemDefinition(OldschoolClient.loadItemDefinition(getId()));
	}

	public RSTile getTile() {
		return tile;
	}

	@Override
	public int getId() {
		return _node.getId();
	}

	@Override
	public int getStackSize() {
		return _node.getStackSize();
	}

	public int getRegionX() {
		return tile.getX() - OldschoolClient.getBaseX();
	}

	public int getRegionY() {
		return tile.getY() - OldschoolClient.getBaseY();
	}

	public boolean isOnScreen() {
		return Calculations.isOnScreen(getScreenPoint());
	}

	public int dist() {
		return (int) Calculations.distance(this.getTile());
	}

	public String getName() {
		return definition != null ? definition.getName() : null;
	}

	public String[] getGroundActions() {
		return this.definition != null ? definition.getGroundActions() : null;
	}

	public String[] getWidgetActions() {
		return this.definition != null ? definition.getWidgetActions() : null;
	}

	public Polygon getBounds() {
		final int x = tile.getX();
		final int y = tile.getY();
		final int z = tile.getZ();
		final Point pn, px, py, pe;
		final double a = -0.35, r = -a;
		final Polygon polygon = new Polygon();
		final RSTile lastTile = new RSTile(x + 1, y + 1, z);
		pn = Calculations.pointOnScreen(new RSTile(x, y, z), r, r, 0);
		px = Calculations.pointOnScreen(new RSTile(x + 1, y, z), a, r, 0);
		py = Calculations.pointOnScreen(new RSTile(x, y + 1, z), r, a, 0);
		pe = Calculations.pointOnScreen(lastTile, a, a, 0);
		polygon.addPoint(pn.x, pn.y);
		polygon.addPoint(py.x, py.y);
		polygon.addPoint(pe.x, pe.y);
		polygon.addPoint(px.x, px.y);
		return polygon;
	}

	public Point getScreenPoint() {
		final Rectangle2D r = getBounds().getBounds2D();
		final double x = r.getCenterX(), y = r.getCenterY();
		return new Point((int) x, (int) y);}
	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundItem#setId(int)
	 */
	@Override
	public void setId(int var1) {
		_node.setId(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundItem#setStackSize(int)
	 */
	@Override
	public void setStackSize(int var1) {
		_node.setStackSize(var1);		
	}
}
