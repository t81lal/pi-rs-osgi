package org.nullbool.piexternal.game.api.queries;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.nullbool.piexternal.game.api.OldschoolClient;
import org.nullbool.piexternal.game.api.meta.RSTile;
import org.nullbool.piexternal.game.api.wrappers.entity.NPC;

public class NpcsQuery extends Query<NPC> {

	public NpcsQuery(final Stream<NPC> stream) {
		this.setArray(stream.toArray(NPC[]::new));
		this.setStream(Arrays.stream(array()));
	}

	private int compare(final NPC first, final NPC second) {
		return Integer.compare(first.dist(), second.dist());
	}

	public NpcsQuery visible() {
		return new NpcsQuery(stream().filter(c -> c.isOnScreen()));
	}

	public NpcsQuery within(final int distance) {
		return new NpcsQuery(stream().filter(c -> c.dist() <= distance));
	}

	public NpcsQuery at(final RSTile t) {
		return new NpcsQuery(stream().filter(c -> c.getTile().equals(t)));
	}

	public NpcsQuery actions(final String... n) {
		return new NpcsQuery(stream().filter(c -> contains(c.getNpcDefinition().getActions(), n)));
	}

	public NpcsQuery name(final String... names) {
		return new NpcsQuery(stream().filter(c -> contains(names, c.getNpcDefinition().getName())));
	}

	public NpcsQuery notFighting() {
		return new NpcsQuery(stream().filter(c -> c.getInteractingId() == -1));
	}

	public NpcsQuery sort() {
		final List<NPC> npcsList = list();
		Collections.sort(npcsList, (NPC n, NPC e) -> compare(n, e));
		return new NpcsQuery(npcsList.stream());
	}

	private boolean contains(final String[] elements, final String... keys) {
		Stream<String> stream = Arrays.stream(elements).filter(e -> e != null);
		stream = stream.filter(e -> Arrays.binarySearch(keys, e) >= 0);
		return stream.count() != 0;
	}

	public static NpcsQuery pool() {
		return new NpcsQuery(Arrays.stream(OldschoolClient.getNPCs()));
	}
}
