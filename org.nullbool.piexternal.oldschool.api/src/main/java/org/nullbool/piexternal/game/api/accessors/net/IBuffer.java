/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

package org.nullbool.piexternal.game.api.accessors.net;

import java.math.BigInteger;
import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface IBuffer extends INode {
   byte[] getPayload();

   void setPayload(byte[] var1);

   int getCaret();

   void setCaret(int var1);

   void write8(int var1);

   void write24(int var1);

   void write32(int var1);

   void write40(long var1);

   void write64(long var1);

   void writeCharSequence(CharSequence var1);

   void writeBytes(byte[] var1, int var2, int var3);

   void write32Weird(int var1);

   void writeLE16A(int var1);

   void write8Weird(int var1);

   void writeVarByte(int var1);

   void write8Offset128(int var1);

   void write8Neg0(int var1);

   void write16A(int var1);

   void writeInvertedLE32(int var1);

   void writeInverted32(int var1);

   void write16B(int var1);

   void enableEncryption(BigInteger var1, BigInteger var2);

   void write8Neg128(int var1);

   void writeJagexString(String var1);

   void writeString(String var1);

   void write16(int var1);

   void writeLE16(int var1);

   void writeLE32(int var1);
}
