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

package org.nullbool.pi.core.scripting.api.klassmodel;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nullbool.api.obfuscation.refactor.ClassTree;
import org.nullbool.pi.core.scripting.api.util.PrefixedStringWriter;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.topdank.byteengineer.commons.data.JarContents;

/**
 * @author Bibl (don't ban me pls)
 * @created 28 Jun 2015 05:32:50
 */
public class HierarchalClassLoader extends ClassLoader {

	static int count = 0;

	private final ClassLoader parentClassLoader;
	private final Map<String, Class<?>> classCache;
	private final Map<String, ClassNode> nodeCache;
	private final ClassTree classTree;
	private final IAggregateFilter<String> filter;
	private final List<HierarchalClassLoader> children;
	private final int id;

	public HierarchalClassLoader(ClassLoader parentClassLoader, JarContents<ClassNode> contents) {
		this.parentClassLoader = parentClassLoader;
		classCache = new HashMap<String, Class<?>>();
		nodeCache = contents.getClassContents().namedMap();
		classTree = new ClassTree(nodeCache);
		filter = new AggregateFilter<String>();
		children = new ArrayList<HierarchalClassLoader>();

		id = count++;
		StackTraceElement stel = new Exception().getStackTrace()[1];
		System.out.printf("%s: Spawned (%d) from %s.%s:%d at %d.%n", toString(), id, stel.getClassName(), stel.getMethodName(), stel.getLineNumber(),
				id);
	}

	public IAggregateFilter<String> filter() {
		return filter;
	}

	public List<HierarchalClassLoader> children() {
		return children;
	}

	public Class<?> lookup(String byte_name) {
		if (classCache.containsKey(byte_name)) {
			return classCache.get(byte_name);
		} else {
			System.out.printf("\t%s: %s is not defined yet.%n", toString(), byte_name);
		}

		if (nodeCache.containsKey(byte_name)) {
			System.out.printf("\t%s: Defining %s.%n", toString(), byte_name);
			Class<?> klass = defineNode(nodeCache.get(byte_name));
			System.out.printf("\t%s: Defined %s.%n", toString(), klass);
			if (klass != null) {
				return cache(klass);
			}
		} else {
			System.out.printf("\t%s: Cache doesn't contain %s.%n", toString(), byte_name);
		}

		return null;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		String byte_name = name.replace(".", "/");
		System.out.printf("%s: Loading %s.%n", toString(), byte_name);

		Class<?> klass = null;

		try {
			Throwable e1 = null;
			Throwable e2 = null;

			try {
				klass = parentClassLoader.loadClass(name);
				if (klass != null)
					return cache(klass);
			} catch (Throwable e) {
				e1 = e;
			}

			try {
				klass = lookup(byte_name);
				if (klass != null)
					return klass;
			} catch (Throwable e) {
				e2 = e;
			}

			try {
				klass = super.loadClass(name);
				if (klass != null)
					return cache(klass);
			} catch (Throwable e) {
				PrefixedStringWriter sw = new PrefixedStringWriter();
				sw.buf().setPrefix("\t" + toString() + ": ");
				sw.buf().appendPrefix("");
				PrintWriter pw = new PrintWriter(sw);
//				System.out.println("UBER FAILURE: " + byte_name);
				
				e.printStackTrace(pw);
				
				if(e1 != null) {
					e1.printStackTrace(pw);
				} else {
					sw.write("Null e1.\n");
				}
				
				if(e2 != null) {
					e2.printStackTrace(pw);
				} else {
					sw.write("Null e2.\n");
				}
				
				System.err.println(sw.toString());
			}

			return null;
		} finally {
			System.out.printf("%s: %s loading %s (%s).%n", toString(), klass != null ? "Done" : "Error", klass, byte_name);
		}
	}

	protected Class<?> cache(Class<?> klass) {
		String name = klass.getCanonicalName();
		// System.out.printf("name=%s, klass=%s.%n", name, klass);
		if (name == null)
			name = klass.getName();
		name = name.replace(".", "/");
		classCache.put(name, klass);
		return klass;
	}

	Class<?> defineNode(ClassNode node) {
		Class<?> klass = null;
		try {
			ClassWriter cw = new CacheLookupClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS, classTree);
			node.accept(cw);
			byte[] bytes = cw.toByteArray();
			klass = defineClass(null, bytes, 0, bytes.length);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return klass;
	}

	@Override
	public String toString() {
		return "[" + id + "]";
	}
}
