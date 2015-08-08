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

package org.nullbool.pi.core.scripting.api.loader;

import org.objectweb.asm.tree.ClassNode;
import org.topdank.byteengineer.commons.data.JarInfo;
import org.topdank.byteengineer.commons.data.LocateableJarContents;

/**
 * @author Bibl (don't ban me pls)
 * @created 27 Jun 2015 12:52:49
 */
public class ResolvedDefinition {
	
	private final ExternalResourceDefinition definition;
	private final ResourceType type;
	private final JarInfo jarInfo;
	private final LocateableJarContents<ClassNode> contents;
	
	public ResolvedDefinition(ExternalResourceDefinition definition, ResourceType type, JarInfo jarInfo, LocateableJarContents<ClassNode> contents) {
		this.definition = definition;
		this.type = type;
		this.jarInfo = jarInfo;
		this.contents = contents;
	}

	public ExternalResourceDefinition getDefinition() {
		return definition;
	}

	public ResourceType getType() {
		return type;
	}

	public JarInfo getJarInfo() {
		return jarInfo;
	}

	public LocateableJarContents<ClassNode> getContents() {
		return contents;
	}

	@SuppressWarnings("unchecked")
	public <T> Class<T> getClass(ClassLoader cl, Class<T> superClass, String name) throws Exception {
		name = name.replace(".", "/");
		ClassNode cn = contents.getClassContents().namedMap().get(name);
		if (cn.name.equals(name)) {
			Class<?> c = cl.loadClass(name);
			if (superClass.isAssignableFrom(c)) {
				return (Class<T>) c;
			} else {
				throw new Exception(c.getCanonicalName() + " isn't a subclass of " + superClass.getCanonicalName());
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((definition == null) ? 0 : definition.hashCode());
		result = prime * result + ((jarInfo == null) ? 0 : jarInfo.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResolvedDefinition other = (ResolvedDefinition) obj;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (definition == null) {
			if (other.definition != null)
				return false;
		} else if (!definition.equals(other.definition))
			return false;
		if (jarInfo == null) {
			if (other.jarInfo != null)
				return false;
		} else if (!jarInfo.equals(other.jarInfo))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "ScriptDefinition [definition=" + definition + ", type=" + type + ", jarInfo=" + jarInfo + ", contents=" + contents + "]";
//	}
	
	@Override
	public String toString() {
		return definition.getName();
	}
}
