package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.collections.INode;
import org.nullbool.piexternal.game.api.accessors.world.IGameObject;
import org.nullbool.piexternal.game.api.accessors.world.IGroundDecoration;
import org.nullbool.piexternal.game.api.accessors.world.IGroundObject;
import org.nullbool.piexternal.game.api.accessors.world.IWallDecoration;
import org.nullbool.piexternal.game.api.accessors.world.IWallObject;

public interface ITile extends INode {
   IGameObject[] getObjects();

   IGroundObject getGroundObjects();

   IGroundDecoration getGroundDecorations();

   IWallObject getWallObjects();

   IWallDecoration getWallDecorations();
}
