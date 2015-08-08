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

package org.nullbool.piexternal.game.api.queries;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.nullbool.piexternal.game.api.OldschoolClient;
import org.nullbool.piexternal.game.api.meta.RSItem;
import org.nullbool.piexternal.game.api.wrappers.widget.Item;

public class InventoryQuery extends Query<Item> {


	public int getRealCount(final int id) {

		final int count = (int) stream().filter(c -> c.getId() == id).count();
		return count == 1 ? getItem(id).getQuantity() : count;
	}

	

	public int getRealCount(final String n) {

		final int c = (int) stream().filter(i -> i.getName().equals(n)).count();
		return c == 1 ? getItem(n).getQuantity() : c;
	}

	

	public Item getItem(final String name) {
		final Stream<RSItem> s = stream().filter(c -> c.getName().equals(name));
		return s.findFirst().orElse(null);
	}
	
	public Item getItem(final int id) {
		return stream().filter(c -> c.getId() == id).findFirst().orElse(null);
	}

	public int getCount(final String name) {
		return (int) stream().filter(c -> c.getName().equals(name)).count();
	}

	public boolean isSelected() {
		return OldschoolClient.getSelectionState() !=0l
	}


	public int getCount(final int id) {

		return (int) stream().filter(c -> c.getId() == id).count();
	}


	public int getCount() {

		return (int) stream().filter(c -> c.getId() != 0).count();
	}


	public boolean contains(final String name) {

		return this.getCount(name) != 0;
	}


	public boolean contains(final int id) {

		return this.getCount(id) != 0;
	}

	
	public RSItem getAt(final int slot) {

		return this.getItems()[slot];
	}

	

	public boolean isEmpty() {

		return this.getCount() == 0;
	}



	public boolean isFull() {

		return this.getCount() == 28;
	}

	
	@Override
	private Stream<RSItem> stream() {

		return Arrays.stream(getItems());
	}


	public Point getInteractingPoint() {

		final Point point = new Point(-1, -1);
		final Rectangle rect = new Rectangle(519, 338, 28, 115);
		point.x = (int) (rect.x + (rect.width * Math.random()));
		point.y = (int) (rect.y + (rect.height * Math.random()));
		return point;
	}


	public RSItem[] getItems() {

		final ArrayList<RSItem> items = new ArrayList<RSItem>();
		final WidgetChild child = api.widgets.getWidget(149).getChild(0);

		final int[] itemIds = child.getSlotIds();
		final int[] itemStack = child.getSlotStackSize();

		for (int i = 0; i < itemIds.length; i++) {

			int x = (580 + ((i % 4) * 42)) - 15;
			int y = (228 + ((i / 4) * 36)) - 2;
			items.add(new RSItem(api, itemIds[i], itemStack[i], x, y ,i));
		}
		return items.toArray(new RSItem[items.size()]);
	}
	
	
	
}
