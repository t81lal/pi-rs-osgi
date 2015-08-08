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

package org.nullbool.pi.core.scripting.api.util;

public class PrefixedStringBuilder {

	protected StringBuilder sb;
	protected String prefix;

	public PrefixedStringBuilder() {
		sb = new StringBuilder();
	}
	
	public PrefixedStringBuilder(int size) {
		sb = new StringBuilder(size);
	}

	public PrefixedStringBuilder append(String s) {
		sb.append(s);
		if (s.contains("\n") && (prefix != null) && (prefix.length() > 0))// insert
			sb.append(prefix);
		return this;
	}

	public PrefixedStringBuilder append(Object o) {
		return append(o.toString());
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void trimPrefix(int amount) {
		if (prefix == null)
			return;
		if (prefix.length() < amount)
			return;
		prefix = prefix.substring(0, prefix.length() - amount);
	}

	public void appendPrefix(String s) {
		if (prefix == null)
			prefix = "";
		prefix += s;
	}

	public String getPrefix() {
		return prefix;
	}

	@Override
	public String toString() {
		return sb.toString();
	}
}
