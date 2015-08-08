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
