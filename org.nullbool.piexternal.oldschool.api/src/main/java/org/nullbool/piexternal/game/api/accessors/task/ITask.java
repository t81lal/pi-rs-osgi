package org.nullbool.piexternal.game.api.accessors.task;

public interface ITask {
   int getType();

   void setType(int var1);

   int getIntArg();

   void setIntArg(int var1);

   Object getObjArg();

   void setObjArg(Object var1);

   ITask getNext();

   int getStatus();

   void setStatus(int var1);

   Object getResult();

   void setResult(Object var1);
}
