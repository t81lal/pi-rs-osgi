package org.nullbool.piexternal.game.api.accessors;

public interface IWrappedException {
   String getReason_();

   void setReason_(String var1);

   Throwable getThrowable();

   void setThrowable(Throwable var1);
}
