package org.nullbool.piexternal.game.api.accessors.script;

import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface IScriptEvent extends INode {
   Object[] getArgs();

   void setArgs(Object[] var1);

   String getOpbase();

   void setOpbase(String var1);

   int getMouseX();

   void setMouseX(int var1);

   int getMouseY();

   void setMouseY(int var1);
}
