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

import java.io.IOException;
import java.io.StringWriter;

public class PrefixedStringWriter extends StringWriter {

	private PrefixedStringBuilder buf;

	public PrefixedStringWriter() {
		buf = new PrefixedStringBuilder();
		lock = buf;
	}

	public PrefixedStringWriter(int initialSize) {
		if (initialSize < 0) {
			throw new IllegalArgumentException("Negative buffer size");
		}
		buf = new PrefixedStringBuilder(initialSize);
		lock = buf;
	}

	@Override
	public void write(int c) {
		buf.append((char) c);
	}

	@Override
	public void write(char cbuf[], int off, int len) {
		if ((off < 0) || (off > cbuf.length) || (len < 0) || ((off + len) > cbuf.length) || ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return;
		}
		System.out.println("PrefixedStringWriter.write()");
//		buf.append(cbuf, off, len);
	}

	@Override
	public void write(String str) {
		buf.append(str);
	}

	@Override
	public void write(String str, int off, int len) {
		buf.append(str.substring(off, off + len));
	}

	@Override
	public PrefixedStringWriter append(CharSequence csq) {
		if (csq == null)
			write("null");
		else
			write(csq.toString());
		return this;
	}

	@Override
	public PrefixedStringWriter append(CharSequence csq, int start, int end) {
		CharSequence cs = (csq == null ? "null" : csq);
		write(cs.subSequence(start, end).toString());
		return this;
	}

	@Override
	public PrefixedStringWriter append(char c) {
		write(c);
		return this;
	}

	@Override
	public String toString() {
		return buf.toString();
	}
	
	public PrefixedStringBuilder buf() {
		return buf;
	}
	
	@Override
	public void flush() {
	}

	@Override
	public void close() throws IOException {
	}
}
