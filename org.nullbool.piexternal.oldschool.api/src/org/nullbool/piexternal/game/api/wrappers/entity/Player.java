package org.nullbool.piexternal.game.api.wrappers.entity;

import org.nullbool.piexternal.game.api.accessors.entity.IPlayer;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 19 Apr 2015 at 13:37:06 <br>
 */
public class Player extends Actor<IPlayer> implements IPlayer {

	public Player(IPlayer _player) {
		super(_player);
	}

	@Override
	public String getName() {
		return _node.getName();
	}

	@Override
	public int getPlayerLevel() {
		return _node.getPlayerLevel();
	}
}