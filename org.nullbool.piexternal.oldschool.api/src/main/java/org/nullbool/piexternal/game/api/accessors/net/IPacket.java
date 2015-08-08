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
