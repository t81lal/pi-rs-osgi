package org.nullbool.piexternal.game.api.accessors.network;

import java.math.BigInteger;
import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface IBuffer extends INode {
   int getCaret();

   byte[] getPayload();

   void write8Weird(int var1);

   void write8(int var1);

   void write16(int var1);

   void write24(int var1);

   void write64(long var1);

   void writeJagexString(String var1);

   void writeBytes(byte[] var1, int var2, int var3);

   void write32Weird(int var1);

   void enableEncryption(BigInteger var1, BigInteger var2);

   void write8Neg128(int var1);

   void writeLE16(int var1);

   void write16A(int var1);

   void writeLE32(int var1);

   void writeInvertedLE32(int var1);

   void writeVarByte(int var1);

   void writeString(String var1);

   void writeInverted32(int var1);

   void write8Offset128(int var1);

   void write8Neg0(int var1);

   void write32(int var1);

   void writeLE16A(int var1);

   void writeCharSequence(CharSequence var1);

   void write16B(int var1);

   void write40(long var1);
}
