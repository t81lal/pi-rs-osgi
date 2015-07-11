package org.nullbool.piexternal.game.api.queries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.nullbool.piexternal.game.api.meta.RSTile;
import org.nullbool.piexternal.game.api.OldschoolClient;
import org.nullbool.piexternal.game.api.wrappers.entity.Player;
import org.nullbool.piexternal.game.api.wrappers.collection.Deque;
import org.nullbool.piexternal.game.api.accessors.collections.INode;
import org.nullbool.piexternal.game.api.accessors.world.IGroundItem;
import org.nullbool.piexternal.game.api.accessors.collections.IDeque;
import org.nullbool.piexternal.game.api.wrappers.world.GroundItem;

public class GroundItemsQuery extends Query<GroundItem> {

	public GroundItemsQuery pool() {
		return pool(-1);
	}

	public GroundItemsQuery(final Stream<GroundItem> stream) {
		this.setArray(stream.toArray(GroundItem[]::new));
		this.setStream(Arrays.stream(array()));
	}

	private int compare(final GroundItem first, final GroundItem second) {
		return Integer.compare(first.dist(), second.dist());
	}

	public GroundItemsQuery name(final String... n) {
		return new GroundItemsQuery(stream().filter(i -> look(i.getName(), n)));
	}

	public GroundItemsQuery id(final int... id) {
		return new GroundItemsQuery(stream().filter(i -> lookI(i.getId(), id)));
	}

	public GroundItemsQuery at(final RSTile t) {
		return new GroundItemsQuery(stream().filter(i -> i.getTile().equals(t)));
	}

	public GroundItemsQuery within(final int distance) {
		return new GroundItemsQuery(stream().filter(i -> i.dist() <= distance));
	}

	@SuppressWarnings("unchecked")
	private <T> boolean look(final T key, final T... elements) {
		Arrays.sort(elements);
		return Arrays.binarySearch(elements, key) >= 0;
	}

	private boolean lookI(final int key, final int... elements) {
		Arrays.sort(elements);
		return Arrays.binarySearch(elements, key) >= 0;
	}

	public GroundItemsQuery sort() {
		final List<GroundItem> list = list();
		Collections.sort(list, (GroundItem n, GroundItem e) -> compare(n, e));
		return new GroundItemsQuery(list.stream());
	}

	public GroundItemsQuery pool(int zoneSize) {

		INode c;
		Deque l;
		boolean all = zoneSize == -1;
		final int z = OldschoolClient.getPlane();
		final Player m = OldschoolClient.getLocalPlayer();
		final int regionX = all ? 94 : m.getLocalX() >> 7;
		final int regionY = all ? 94 : m.getLocalY() >> 7;
		final ArrayList<GroundItem> i = new ArrayList<GroundItem>();
		final IDeque[][][] objects = OldschoolClient.getGroundItems();
		for (int x = all ? 0 : regionX - zoneSize; x < regionX + zoneSize; x++)

			for (int y = all ? 0 : regionY - zoneSize; y < regionY + zoneSize; y++)

				if ((l = new Deque(objects[z][x][y])) != null)

					if ((c = l.getHead().getNext()) != null)

						while (c != null && c != l.getHead()) {

							final int a = OldschoolClient.getBaseX() + x;
							final int b = OldschoolClient.getBaseY() + y;
							final RSTile tile = new RSTile(a, b, z);
							i.add(new GroundItem((IGroundItem) c, tile));
							c = c.getNext();
						}
		return new GroundItemsQuery(i.stream());
	}
}
