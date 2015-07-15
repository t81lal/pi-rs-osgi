package org.nullbool.piexternal.game.api.accessors;

public interface IScriptEvent {
   Object[] getArgs();

   void setArgs(Object[] var1);

   String getOpbase();

   void setOpbase(String var1);

   int getMouseX();

   void setMouseX(int var1);

   int getMouseY();

   void setMouseY(int var1);
}
