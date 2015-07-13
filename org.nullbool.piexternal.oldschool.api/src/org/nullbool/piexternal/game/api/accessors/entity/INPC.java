package org.nullbool.piexternal.game.api.accessors.entity;

import org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition;
import org.nullbool.piexternal.game.api.accessors.entity.IActor;

public interface INPC extends IActor {
   INPCDefinition getNpcDefinition();
}
