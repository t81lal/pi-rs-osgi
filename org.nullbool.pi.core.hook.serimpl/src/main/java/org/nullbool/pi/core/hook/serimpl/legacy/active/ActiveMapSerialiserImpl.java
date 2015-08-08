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

//package org.nullbool.pi.core.hook.serimpl.legacy.active;
//
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.nullbool.pi.core.hook.serimpl.legacy._static.StaticMapSerialiserImpl;
//import org.nullbool.pi.core.hook.api.HookMap;
//import org.nullbool.pi.core.hook.api.deprecated.AbstractActor;
//import org.nullbool.pi.core.hook.api.deprecated.ActiveHookMap;
//import org.nullbool.pi.core.hook.api.serialisation.IMapSerialiser;
//
//@Deprecated
//public class ActiveMapSerialiserImpl implements IMapSerialiser<ActiveHookMap> {
//
//	@Override
//	public void serialise(ActiveHookMap map, OutputStream os) throws IOException {
//		serialise(map, new StaticMapSerialiserImpl(), os);
//	}
//	
//	public void serialise(ActiveHookMap map, IMapSerialiser<HookMap> impl, OutputStream os) throws IOException {
//		DataOutputStream dos = new DataOutputStream(os);
//		
//		List<AbstractActor> actors = map.getActors();
//		dos.writeInt(actors.size());
//		for(AbstractActor actor : actors) {
//			/* Save the class and then the variables map. */
//			Class<?> klass = actor.getClass();
//			InputStream is = klass.getClassLoader().getResourceAsStream(klass.getName().replace('.', '/') + ".class");
//			int len = is.available();
//			byte[] bytes = new byte[len];
//			is.read(bytes);
//			
//			dos.writeLong(len);
//			dos.write(bytes, 0, len);
//			
//			Map<String, String> vars = actor.variables();
//			int var_len = vars.size();
//			dos.writeInt(var_len);
//			
//			for(Entry<String, String> e : vars.entrySet()) {
//				dos.writeUTF(e.getKey());
//				dos.writeUTF(e.getValue());
//			}
//		}
//		
//		impl.serialise(map, os);
//	}
//}
