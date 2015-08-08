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
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.nullbool.pi.core.hook.serimpl.legacy._static.StaticMapDeserialiserImpl;
//import org.nullbool.pi.core.hook.api.HookMap;
//import org.nullbool.pi.core.hook.api.deprecated.AbstractActor;
//import org.nullbool.pi.core.hook.api.deprecated.ActiveHookMap;
//import org.nullbool.pi.core.hook.api.serialisation.IMapDeserialiser;
//
//@Deprecated
//public class ActiveMapDeserialiserImpl implements IMapDeserialiser<ActiveHookMap> {
//
//	@Override
//	public ActiveHookMap deserialise(InputStream is) throws IOException {
//		return deserialise(is, new StaticMapDeserialiserImpl());
//	}
//	
//	public ActiveHookMap deserialise(InputStream is, IMapDeserialiser<HookMap> impl) throws IOException {
//		DataInputStream dis = new DataInputStream(is);
//		
//		List<AbstractActor> actors = new ArrayList<AbstractActor>();
//		int actor_count = dis.readInt();
//		
//		ClassLoaderExt cl = new ClassLoaderExt();
//		
//		for(int i=0; i < actor_count; i++) {
//			long class_len = dis.readLong();
//			byte[] bytes = new byte[(int) class_len];
//			dis.read(bytes);
//
//			Class<?> klass = cl.define(bytes, 0, bytes.length);
//			AbstractActor actor = null;
//			
//			try {
//				actor = (AbstractActor) klass.newInstance();
//			} catch (InstantiationException | IllegalAccessException e) {
//				throw new IOException("Invalid actor: " + klass.getName() + " no public default constructor.", e);
//			}
//			
//			actors.add(actor);
//			
//			int var_len = dis.readInt();
//			Map<String, String> vars = actor.variables();
//			
//			for(int j=0; j < var_len; j++) {
//				String key = dis.readUTF();
//				String val = dis.readUTF();
//				
//				vars.put(key, val);
//			}
//		}
//		
//		HookMap o_map = impl.deserialise(is);
//		ActiveHookMap map2 = new ActiveHookMap(o_map.version(), o_map.classes());
//		map2.setActors(actors);
//		
//		return map2;
//	}
//	
//	public static final class ClassLoaderExt extends ClassLoader {
//		public Class<?> define(byte[] b, int off, int len) {
//	    	return super.defineClass(b, off, len);
//	    }
//	}
//}
