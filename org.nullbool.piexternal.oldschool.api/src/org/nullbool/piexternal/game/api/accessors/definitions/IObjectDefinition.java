package org.nullbool.piexternal.game.api.accessors.definitions;

import org.nullbool.piexternal.game.api.accessors.collections.IDualNode;

public interface IObjectDefinition extends IDualNode {
   String getName();

   void setName(String var1);

   int getWidth();

   void setWidth(int var1);

   int getHeight();

   void setHeight(int var1);

   int getAnimationId();

   void setAnimationId(int var1);

   int getObjMapScene();

   void setObjMapScene(int var1);

   int getModelWidth();

   void setModelWidth(int var1);

   int getModelHeight();

   void setModelHeight(int var1);

   int getModelBreadth();

   void setModelBreadth(int var1);

   int getTranslationX();

   void setTranslationX(int var1);

   int getTranslationY();

   void setTranslationY(int var1);

   int getTranslationZ();

   void setTranslationZ(int var1);

   boolean isWalkable();

   void setWalkable(boolean var1);

   String[] getActions();

   void setActions(String[] var1);

   int getIcon();

   void setIcon(int var1);

   boolean isRotated();

   void setRotated(boolean var1);

   boolean hasCastedShadow();

   void setHasCastedShadow(boolean var1);
}
