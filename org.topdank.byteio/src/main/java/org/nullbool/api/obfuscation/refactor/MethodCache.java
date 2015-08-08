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

package org.nullbool.api.obfuscation.refactor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 May 2015 (actually before this)
 */
public class MethodCache {
	private final Filter<MethodNode> filter;
	private final Map<String, MethodNode> methodMap;
	
	public MethodCache(Filter<MethodNode> filter) {
		this.filter = filter;
		methodMap = new HashMap<String, MethodNode>();
	}
	
	public MethodCache(Filter<MethodNode> filter, Collection<ClassNode> classes) {
		this(filter);
		put(classes);
	}
	
	public MethodCache(Collection<ClassNode> classes) {
		this(Filter.acceptAll(), classes);
	}

	//TODO:
	//	public void propagate(ClassTree tree) {
	//		for(Entry<String, MethodNode> e: methodMap.entrySet()) {
	//			MethodNode m = e.getValue();
	//			String key = e.getKey();
	//			ClassNode owner = m.owner;
	//			Set<ClassNode> sups = tree.getSupers(owner);
	//			Set<ClassNode> dels = tree.getDelegates(owner);
	//			
	//			for(ClassNode sup : sups) {
	//				
	//			}
	//		}
	//	}
	
	//you have no idea how much I wished the compiler inlined these...
	
	public void put(Collection<ClassNode> classes) {
		for(ClassNode cn : classes) {
			put(cn);
		}
	}
	
	public void put(ClassNode cn) {
		for(MethodNode m : cn.methods) {
			put(m);
		}
	}
	
	public void put(MethodNode m) {
		if(filter.accept(m)){
			methodMap.put(makeKey(m.owner.name, m.name, m.desc), m);
			m.cacheKey();
		}
	}
	
	public void reset() {
		Collection<MethodNode> methods = methodMap.values();
		methodMap.clear();
		for(MethodNode m : methods) {
			put(m);
		}
	}
	
	public static String makeKey(String owner, String name, String desc) {
		return new StringBuilder(owner).append(".").append(name).append(desc).toString();
	}
	
	public MethodNode get(String owner, String name, String desc) {
		return get(makeKey(owner, name, desc));
	}
	
	public MethodNode get(String key) {
		if(methodMap.containsKey(key))
			return methodMap.get(key);
		return null;
	}
	
	public void clear() {
		methodMap.clear();
	}
	
	public int size() {
		return methodMap.size();
	}
}
