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
 * Primitive desc type enum.
 * @author Bibl
 * 
 */
public enum Primitive {
	
	/** Integer type (I) **/
	INT("I"),
	/** Short type (S) **/
	SHORT("S"),
	/** Byte type(B) **/
	BYTE("B"),
	/** Float type (F) **/
	FLOAT("F"),
	/** Double type (D) **/
	DOUBLE("D"),
	/** Long type (J) **/
	LONG("J"),
	/** Boolean type (Z) **/
	BOOLEAN("Z");
	
	private String desc;
	
	Primitive(String desc) {
		this.desc = desc;
	}
	
	/**
	 * @return Internal bytecode description type representation.
	 */
	public String desc() {
		return desc;
	}
	
	/**
	 * Converts a bytecode descriptor to the equivilient primitive type.
	 * @param desc Bytecode descriptor.
	 * @return {@link Primitive} or null if the desc is an array or a reference type.
	 */
	public static Primitive translate(String desc) {
		for(Primitive primitive : Primitive.values()) {
			if (primitive.desc.equals(desc))
				return primitive;
		}
		return null;
	}
}
