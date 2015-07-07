package org.nullbool.piexternal.game.api.accessors.network;

import org.nullbool.piexternal.game.api.accessors.network.IBuffer;
import org.nullbool.piexternal.game.api.accessors.network.IIsaacCipher;

public interface IPacket extends IBuffer {
   int getBitCaret();

   IIsaacCipher getCipher();

   void finishBitAccess();

   void initCipher(int[] var1);

   void initBitAccess();

   int readBits(int var1);

   int readableBytes(int var1);
}