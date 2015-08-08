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

package org.nullbool.pi.core.engine.api.transform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.tree.ClassNode;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 00:36:07
 */
public abstract class TransformationEngine {

	private final Map<String, ClassNode> classes;
	private final List<ITransformer> transformers;
	private transient Iterator<ClassNode> iterator;
	
	public TransformationEngine(Map<String, ClassNode> classes) {
		this.classes = classes;
		transformers = new ArrayList<ITransformer>();
		iterator = classIterator();
	}
	
	public boolean registerTransformer(ITransformer transformer) {
		return transformers.add(transformer);
	}

	public boolean unregisterTransformer(ITransformer transformer) {
		return transformers.remove(transformer);
	}
	
	public boolean registerTransformers(ITransformer... transformers) {
		boolean b = true;
		for(ITransformer t : transformers) {
			b &= registerTransformer(t);
		}
		return b;
	}
	
	public boolean unregisterTransformers(ITransformer... transformers) {
		boolean b = true;
		for(ITransformer t : transformers) {
			b &= unregisterTransformer(t);
		}
		return b;
	}
	
	public boolean step() throws Throwable {
		if(iterator.hasNext()) {
			ClassNode cn = iterator.next();
			transform(cn);
			return true;
		} else {
			return false;
		}
	}
	

	public void transformAll() throws Throwable {
		while(step());
	}
	
	public void transform(ClassNode cn) throws Throwable {
		for(ITransformer t : transformers) {
			if(t.accept(cn))
				t.run(cn);
		}
	}
	
	public ClassNode findClass(String name) {
		return classes.get(name);
	}
	
	public Iterator<ClassNode> classIterator() {
		return classes.values().iterator();
	}
}
