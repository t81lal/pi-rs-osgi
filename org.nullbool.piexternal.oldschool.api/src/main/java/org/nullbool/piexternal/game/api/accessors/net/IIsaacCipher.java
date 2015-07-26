package org.nullbool.piexternal.game.api.accessors.net;

public interface IIsaacCipher {
   int getCount();

   void setCount(int var1);

   int[] getResults();

   void setResults(int[] var1);

   int[] getMem();

   void setMem(int[] var1);

   void init();

   int next();

   void isaac();
}
