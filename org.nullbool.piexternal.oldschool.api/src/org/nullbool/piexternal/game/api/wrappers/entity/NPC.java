package org.nullbool.piexternal.game.api.wrappers.entity;

import org.nullbool.piexternal.game.api.accessors.entity.INPC;
import org.nullbool.piexternal.game.api.wrappers.definition.NPCDefinition;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 19 Apr 2015 at 17:44:01 <br>
 */
public class NPC extends Actor<INPC> implements INPC {

	public NPC(INPC _npc) {
		super(_npc);
	}

	@Override
	public NPCDefinition getNpcDefinition() {
		return new NPCDefinition(_node.getNpcDefinition());
	}

}