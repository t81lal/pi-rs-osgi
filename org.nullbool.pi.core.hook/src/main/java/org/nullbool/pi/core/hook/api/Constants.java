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

package org.nullbool.pi.core.hook.api;

/**
 * @author Bibl (don't ban me pls)
 * @created 6 Jul 2015 17:23:37
 */
public abstract interface Constants {

	// Shared
	public static final String DESC = "desc.obfuscated";
	public static final String REFACTORED_DESC = "desc.refactored";
	public static final String STATIC = "attr.static";
	
	// Field
	public static final String REAL_OWNER = "owner.real";
	public static final String MUTLI = "attr.multi";
	public static final String ENCODER = "attr.multi.encoder";

	// Method
	public static final String METHOD_TYPE = "attr.type";
	public static final String MAX_STACK = "attr.maxs";
	public static final String MAX_LOCALS = "attr.maxl";
	public static final String CALLBACK = "callback";
	public static final String PATCH = "patch";
	public static final String PATCH_POSITION = "attr.type.patch.pos";
	public static final String START = "attr.type.patch.pos.start";
	public static final String END = "attr.type.patch.pos.end";
	public static final String SAFE_OPAQUE = "attr.safeopaque";
	public static final String HAS_OPAQUE = "attr.hassafe";
}
