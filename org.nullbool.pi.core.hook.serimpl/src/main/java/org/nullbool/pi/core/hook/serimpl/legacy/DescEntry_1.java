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

//package org.nullbool.pi.core.hook.serimpl.legacy;
//
//import java.io.DataOutputStream;
//import java.io.IOException;
//
//import org.nullbool.pi.core.hook.serimpl.Pool;
//import org.nullbool.pi.core.hook.serimpl.PoolEntry;
//import org.nullbool.pi.core.hook.api.DynamicDesc;
//
//public class DescEntry_1 extends PoolEntry {
//
//	private final DynamicDesc desc;
//	private final int index;
//	
//	public DescEntry_1(Pool pool, DynamicDesc desc) {
//		super(pool, 1);
//		this.desc = desc;
//		
//		index = pool.allocateString(desc.getObfuscated());		
//	}
//	
//	@Override
//	public void write(DataOutputStream dos) throws IOException{
//		super.write(dos);
//		/*write the type and then the obfuscated desc string entry index*/
//		dos.writeBoolean(desc.isMethod());
//		dos.writeInt  (index);
//	}
//
//	public DynamicDesc getDesc() {
//		return desc;
//	}
//	
//	@Override
//	public String toString() {
//		return desc.toString();
//	}
//}
