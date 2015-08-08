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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.nullbool.piexternal.game.api.OldschoolClient;
import org.nullbool.piexternal.game.api.accessors.world.ISceneComponent;
import org.nullbool.piexternal.game.api.meta.RSTile;
import org.nullbool.piexternal.game.api.wrappers.entity.Player;
import org.nullbool.piexternal.game.api.wrappers.world.Tile;

public class GameObjectsQuery extends Query<ISceneComponent> {

	public static GameObjectsQuery pool() {
		return pool(true);
	}

	public GameObjectsQuery(final Stream<ISceneComponent> stream) {
		this.setArray(stream.toArray(ISceneComponent[]::new));
		this.setStream(Arrays.stream(array()));
	}

	public GameObjectsQuery visible() {
		return new GameObjectsQuery(stream().filter(c -> c.isOnScreen()));
	}

	public GameObjectsQuery within(final int distance) {
		return new GameObjectsQuery(stream().filter(c -> c.dist() <= distance));
	}

	public GameObjectsQuery at(final RSTile t) {
		return new GameObjectsQuery(stream().filter(c -> c.getPosition().equals(t)));
	}

	public GameObjectsQuery actions(final String... n) {
		return new GameObjectsQuery(stream().filter(c -> contains(c.getActions(), n)));
	}

	public GameObjectsQuery name(final String... names) {
		return new GameObjectsQuery(stream().filter(c -> contains(names, c.getName())));
	}

	private boolean contains(final String[] elements, final String... keys) {
		Stream<String> stream = Arrays.stream(elements).filter(e -> e != null);
		stream = stream.filter(e -> Arrays.binarySearch(keys, e) >= 0);
		return stream.count() != 0;
	}

	public GameObjectsQuery sort() {
		final List<ISceneComponent> gameObjectList = list();
		Collections.sort(gameObjectList, (ISceneComponent n, ISceneComponent e) -> compare(n, e));
		return new GameObjectsQuery(gameObjectList.stream());
	}

	private int compare(final ISceneComponent first, final ISceneComponent second) {
		return Integer.compare(first.dist(), second.dist());
	}

	public static GameObjectsQuery pool(final boolean all) {
		final int p = OldschoolClient.getPlane();
		final Player m = OldschoolClient.getLocalPlayer();
		final int regionX = all ? 94 : m.getLocalX() >> 7;
		final int regionY = all ? 94 : m.getLocalY() >> 7;
		final Tile[][][] tiles = OldschoolClient.getRegion().getTiles();
		final List<ISceneComponent> sceneComponents = new ArrayList<ISceneComponent>();
		for (int x = all ? 0 : regionX - 10; x < regionX + 10; x++)
			for (int y = all ? 0 : regionY - 10; y < regionY + 10; y++)
				addObjectsAt(tiles[p][x][y], sceneComponents);
		sceneComponents.removeAll(Collections.singleton(null));
		return new GameObjectsQuery(sceneComponents.stream());
	}

	private static void addObjectsAt(Tile tile, List<ISceneComponent> sceneComponents) {
		sceneComponents.add(tile.getWallObjects());
		sceneComponents.add(tile.getGroundObjects());
		sceneComponents.add(tile.getWallDecorations());
		sceneComponents.add(tile.getGroundDecorations());
		ISceneComponent[] gameObjects = tile.getObjects();
		for (ISceneComponent gameObject : gameObjects != null ? gameObjects : new ISceneComponent[0])
			sceneComponents.add(gameObject);
	}
}
