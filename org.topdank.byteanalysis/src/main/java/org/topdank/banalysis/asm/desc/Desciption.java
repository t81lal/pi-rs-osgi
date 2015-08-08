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
 * Generic description utility class.
 * @author Bibl
 * 
 */
public final class Desciption {
	
	/**
	 * Finds the internal class name from a bytecode description. "Ljava/lang/String" -> "java/lang/String"
	 * @param referenceDesc A reference type. eg "Ljava/lang/String;"
	 * @return The internal class name of the reference type.
	 */
	public static String getBytecodeClassName(String referenceDesc) {
		referenceDesc = referenceDesc.replace("[", "");
		if (isPrimitive(referenceDesc))
			return referenceDesc;
		return referenceDesc.substring(1, referenceDesc.length() - 1);
	}
	
	/**
	 * Check if the inputted description is a method description.
	 * @param desc Desc to check.
	 * @return Whether the desc is a method description.
	 */
	public static boolean isMethod(String desc) {
		return desc.startsWith("(");
	}
	
	/**
	 * Check if the inputted description is a field description.
	 * @param desc Desc to check.
	 * @return Whether the desc is a field description.
	 */
	public static boolean isField(String desc) {
		return !desc.startsWith("(");
	}
	
	/**
	 * Check if the inputted description is an array description.
	 * @param desc Desc to check.
	 * @return Whether the desc is an array description.
	 */
	public static boolean isArray(String desc) {
		return Arrays.isArray(desc);
	}
	
	/**
	 * Check if the inputted description is a primitive field description.
	 * @param desc Desc to check.
	 * @return Whether the desc is a primitive field description.
	 */
	public static boolean isPrimitive(String desc) {
		return Primitive.translate(desc) != null;
	}
}
