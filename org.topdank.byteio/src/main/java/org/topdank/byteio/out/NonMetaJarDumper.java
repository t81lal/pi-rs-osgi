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

package org.topdank.byteio.out;

import java.io.IOException;
import java.util.jar.JarOutputStream;

import org.objectweb.asm.tree.ClassNode;
import org.topdank.byteengineer.commons.data.JarContents;

public class NonMetaJarDumper extends CompleteJarDumper {

	public NonMetaJarDumper(JarContents<ClassNode> contents) {
		super(contents);
	}

	@Override
	public int dumpResource(JarOutputStream out, String name, byte[] file) throws IOException {
		if (name.contains("META-INF"))
			return 0;
		return super.dumpResource(out, name, file);
	}
}
