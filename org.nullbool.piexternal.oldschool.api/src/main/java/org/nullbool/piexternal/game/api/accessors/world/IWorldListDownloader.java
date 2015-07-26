package org.nullbool.piexternal.game.api.accessors.world;

import java.io.DataInputStream;

public interface IWorldListDownloader {
   int getState();

   void setState(int var1);

   long getTimeout();

   void setTimeout(long var1);

   byte[] getSizePayload();

   void setSizePayload(byte[] var1);

   DataInputStream getStream();

   void setStream(DataInputStream var1);

   byte[] getPayload();

   void setPayload(byte[] var1);
}
