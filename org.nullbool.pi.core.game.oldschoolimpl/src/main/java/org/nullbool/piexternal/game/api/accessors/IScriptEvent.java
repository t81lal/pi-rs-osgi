package org.nullbool.piexternal.game.api.accessors;

public interface IScriptEvent {
   Object[] getArgs();

   String getOpbase();

   Object[] isDisposable();

   int getMouseX();

   int getMouseY();
}
