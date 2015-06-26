package org.nullbool.piexternal.game.api.accessors.definitions;

import org.nullbool.piexternal.game.api.accessors.collections.IDualNode;

public interface IObjectDefinition extends IDualNode {
   String getName();

   int getWidth();

   int getHeight();

   int getAnimationId();

   int getObjMapScene();

   int getModelWidth();

   int getModelHeight();

   int getModelBreadth();

   int getTranslationX();

   int getTranslationY();

   int getTranslationZ();

   boolean isWalkable();

   String[] getActions();

   int getIcon();

   boolean isRotated();

   boolean hasCastedShadow();
}
