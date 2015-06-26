package org.nullbool.piexternal.game.api.accessors.network;

public interface IIsaacCipher {
   int getCount();

   int[] getResults();

   int[] getMem();

   void init();

   int next();

   void isaac();
}
