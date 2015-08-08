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

package org.topdank.banalysis.asm.desc;

/**
 * Utility class for bytecode array descriptors.
 * @author Bibl
 * 
 */
public final class Arrays {
	
	/**
	 * Checks whether the inputted description is of the array description type.
	 * @param desc Bytecode description (field/ref).
	 * @return True if the description is of array type.
	 */
	public static boolean isArray(String desc) {
		return desc.indexOf('[') == 0;
	}
	
	/**
	 * Finds how many dimensions the array is.
	 * @param desc Bytecode description (field/ref).
	 * @return Amount of dimensions.
	 */
	public static int getDimensions(String desc) {
		if (desc.lastIndexOf('[') == -1)
			return 0;
		char[] chars = desc.toCharArray();
		for(int i = 0; i < chars.length; i++) {
			if (chars[i] != '[')
				return i;
		}
		return 0;
	}
	
	/**
	 * Concatenates '['s with the base bytecode description.
	 * @param ref Base desc eg "Ljava/lang/String;"
	 * @param dims Amount of '['s.
	 * @return Concatenated desc.
	 */
	public static String createDesc(String ref, int dims) {
		return concat("[", dims) + ref;
	}
	
	/**
	 * Concatenates a string together a certain amount of times.
	 * @param c Input string.
	 * @param times Amount of times to concat.
	 * @return Concatenated string.
	 */
	public static String concat(String c, int times) {
		String s = "";
		for(int i = 0; i < times; i++) {
			s += c;
		}
		return s;
	}
}
