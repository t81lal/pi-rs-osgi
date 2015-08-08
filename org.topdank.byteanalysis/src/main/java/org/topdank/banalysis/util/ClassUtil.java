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

package org.topdank.banalysis.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.tree.ClassNode;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 9 Apr 2015 at 19:04:54 <br>
 */
public class ClassUtil {

	private static final List<String> JAVA_PACKAGE_STARTS = new ArrayList<String>();

	static {
		/* This might need changing since there are sun classes as well. See 'isJavaSEClass(String)' */
		JAVA_PACKAGE_STARTS.add("java");
		JAVA_PACKAGE_STARTS.add("javax");
		JAVA_PACKAGE_STARTS.add("javafx");
	}

	/**
	 * Maps a Collection of ClassNodes with the name of the ClassNode as the key and the ClassNode
	 * as the value.
	 *
	 * @param classes ClassNodes to map.
	 * @return Mapped ClassNodes.
	 */
	public static Map<String, ClassNode> createMap(Collection<ClassNode> classes) {
		Map<String, ClassNode> map = new HashMap<String, ClassNode>();
		for (ClassNode cn : classes) {
			map.put(cn.name, cn);
		}
		return map;
	}

	/**
	 * Gets the name of the class from a fully qualified bytecode class name. <br>
	 * Eg. <code>getClassName("org/topdank/banalysis/util/ClassUtil")</code> returns "ClassUtil"
	 *
	 * @param name Bytecode class name.
	 * @return The name of the class.
	 */
	public static String getClassName(String name) {
		/* If Strnig.lastIndexOf returns -1 it doesn't matter because we add 1 to it anyway. */
		return name.substring(name.lastIndexOf('/') + 1, name.length());
	}

	/**
	 * Gets the names of all of the packages of the name. <br>
	 * Eg. <code>getPackages("org/topdank/banalysis/util/ClassUtil")</code> returns [org, topdank,
	 * banalysis, util].
	 *
	 * @param name Bytecode class name.
	 * @return The names of the packages as a String[].
	 */
	public static String[] getPackages(String name) {
		int len = packageLength(name);
		if (len == 0)
			return new String[] {};
		String[] arr = new String[len];
		int k = 0;
		char[] chars = name.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == '/') {
				arr[k++] = sb.toString();
				sb.setLength(0);

				if (k >= len)
					break;// done, small optimisation
			} else {
				sb.append(c);
			}
		}
		return arr;
	}

	/**
	 * Gets the amount of packages for the name. <br>
	 * Eg. <code>packageLength("org/topdank/banalysis/util/ClassUtil")</code> returns 4.
	 *
	 * @param name Bytecode class name.
	 * @return Amount of packages.
	 */
	public static int packageLength(String name) {
		String replaced = name.replace("/", "");
		int diff = name.length() - replaced.length();
		return diff;
	}

	/**
	 * Determines whether this class is a part of the Java SE API.
	 *
	 * @param name The name of the class.
	 * @return
	 */
	public static boolean isJavaSEClass(String name) {
		/* Get the lowest package of the name, eg. the lowest package for 'java'/lang/String is
		 * 'java'. */
		int index = name.indexOf('/');
		/* Might not be in a package so indexOf will return -1. */
		if (index == -1)
			return false;
		String firstPackage = name.substring(0, index);
		/* Predefined list of java packages, make this customisable or make method static or just
		 * leave it to overriding shouldTraverse? */
		return JAVA_PACKAGE_STARTS.contains(firstPackage);
	}
}
