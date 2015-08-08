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

package org.nullbool.pi.core.hook.serimpl;

import java.io.DataOutputStream;
import java.io.IOException;

import org.objectweb.asm.Handle;

public class HandleEntry_3 extends PoolEntry {

	private final Handle handle;
	private final int index1, index2, index3;
	
	public HandleEntry_3(Pool pool, Handle handle) {
		super(pool, 3);
		this.handle = handle;
		
		index1 = pool.allocateString(handle.getOwner());
		index2 = pool.allocateString(handle.getName());
		index3 = pool.allocateString(handle.getDesc());
	}
	
	public Handle getHandle(){
		return handle;
	}
	
	@Override
	public void write(DataOutputStream dos) throws IOException{
		dos.writeInt(handle.getTag());
		dos.writeInt(index1);
		dos.writeInt(index2);
		dos.writeInt(index3);
	}
}
