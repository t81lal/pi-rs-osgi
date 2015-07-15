package org.nullbool.piexternal.game.api.accessors.network;

import org.nullbool.piexternal.game.api.accessors.network.IBuffer;
import org.nullbool.piexternal.game.api.accessors.network.IIsaacCipher;

public interface IPacket extends IBuffer {
   IIsaacCipher getCipher();

   int getBitCaret();

   void setBitCaret(int var1);

   void initCipher(int[] var1);

   void initBitAccess();

   int readBits(int var1);

   void finishBitAccess();

   int readableBytes(int var1);
}
