package org.nullbool.piexternal.game.api.accessors.net;

import org.nullbool.piexternal.game.api.accessors.net.IBuffer;
import org.nullbool.piexternal.game.api.accessors.net.IIsaacCipher;

public interface IPacket extends IBuffer {
   IIsaacCipher getCipher();

   int getBitCaret();

   void setBitCaret(int var1);

   void initCipher(int[] var1);

   int readBits(int var1);

   void finishBitAccess();

   void initBitAccess();

   int readableBytes(int var1);

   int readPacketHeader();

   void writePacketHeader(int var1);
}
