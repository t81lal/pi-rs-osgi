package org.nullbool.piexternal.game.api.meta;

import java.awt.Point;
import java.awt.Rectangle;

import org.nullbool.piexternal.game.api.DefinitionLoader;
import org.nullbool.piexternal.game.api.wrappers.definition.ItemDefinition;

public class RSItem implements Interactable {

	private String name;
	private Rectangle area;
	private int id;
	private int stack;
	private int slot;
	private ItemDefinition definition;

	public RSItem(int id, int stack, int x, int y, int slot) {
		this.definition = DefinitionLoader.get().getItemdefinition(id - 1);
		this.area = new Rectangle(x, y, 17, 17);
		this.stack = stack;
		this.slot = slot;
		this.id = id - 1;
	}

	public String getName() {
		return this.definition != null && this.id != 0 ? this.definition.getName() : "";
	}

	public String bankName() {
		return this.name.contains(">") ? this.name.split(">")[1].split("<")[0] : "";
	}

	public Rectangle getArea() {
		return this.area;
	}

	public int getQuantity() {
		return this.stack;
	}

	public int getSlot() {
		return this.slot;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public Point getActionPoint(int x, int y) {
		Point point = this.getRandomPoint();
		point.x += x;
		point.y += y;
		return point;
	}

	public Point getRandomPoint() {
		Point point = new Point(-1, -1);
		point.x = (int) ((double) this.area.x + (double) this.area.width * Math.random());
		point.y = (int) ((double) this.area.y + (double) this.area.height * Math.random());
		return point;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.meta.Interactable#getScreenPoint()
	 */
	@Override
	public Point getScreenPoint() {
		return null;
	}
}