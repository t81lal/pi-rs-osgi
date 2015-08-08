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

package org.nullbool.pi.core.hook.serimpl.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.nullbool.pi.core.hook.api.ClassHook;
import org.nullbool.pi.core.hook.api.Constants;
import org.nullbool.pi.core.hook.api.HookMap;
import org.nullbool.pi.core.hook.api.MethodHook;
import org.nullbool.pi.core.hook.serimpl.StaticMapDeserialiserImpl;
import org.nullbool.pi.core.hook.serimpl.StaticMapSerialiserImpl;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class Test {

	public static void main(String[] args) throws IOException {
//		HookMap map = new HookMap();
//		ClassHook c = new ClassHook("TestObf", "TestRefac");
//		FieldHook f = new FieldHook(c, new ObfuscatedData("a", "getField"), new DynamicDesc("I", false), false, 1);
//		c.getFields().add(f);
//		
//		map.getClasses().add(c);
//		
//		System.out.println(map.toString());
//		
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		map.write(baos);
//		
//		byte[] bytes = baos.toByteArray();
//		
//		HookMap map2 = HookMap.read(new ByteArrayInputStream(bytes));
//		System.out.println(map2.toString());
//		
//		System.out.println(bytes.length);
		
		HookMap map = new HookMap();
		ClassHook s = new ClassHook("string", "java/lang/String");
		
		ClassNode cn = new ClassNode();
		ClassReader cr = new ClassReader("java/lang/String");
		cr.accept(cn, 0);
		
		System.out.println(cn.methods.size());
		for(MethodNode m : cn.methods) {
			MethodHook mh = new MethodHook(m.instructions).var(Constants.METHOD_TYPE, "callbakc").obfuscated("obf").refactored("ref").var(Constants.DESC, m.desc).var(Constants.STATIC, "true");
			s.methods().add(mh);
		}
		
		System.out.println(s.methods().size());
		
		map.classes().add(s);
		
		//System.out.println(map.toString());
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StaticMapSerialiserImpl serialiser = new StaticMapSerialiserImpl();
		serialiser.serialise(map, baos);
		
		byte[] bytes = baos.toByteArray();
		
		System.out.println(bytes.length);
		
		StaticMapDeserialiserImpl deserialiser = new StaticMapDeserialiserImpl();
		HookMap hm = deserialiser.deserialise(new ByteArrayInputStream(bytes));
		
		System.out.println(hm.classes().size());
		System.out.println(hm.classes().get(0).methods().size());
//		
//		HookMap map2 = HookMap.read(new ByteArrayInputStream(bytes));
//		
//		System.out.println(map2.getClasses().size());
		//System.out.println(map2.toString());
		
		//System.out.println(bytes.length);
	}
}
