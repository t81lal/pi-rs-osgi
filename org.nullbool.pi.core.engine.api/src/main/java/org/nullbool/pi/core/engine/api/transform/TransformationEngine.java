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
 * A chain-calling transformer visitor for easy, modular modification
 * of classes.
 *
 * @see ITransformer
 * @see ClassNode
 * 
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 00:36:07
 */
public abstract class TransformationEngine {

	private final Map<String, ClassNode> classes;
	private final List<ITransformer> transformers;
	private transient Iterator<ClassNode> iterator;
	
	/**
	 * @param classes A Map of classes, mapped name-node.
	 */
	public TransformationEngine(Map<String, ClassNode> classes) {
		this.classes = classes;
		transformers = new ArrayList<ITransformer>();
		iterator = classIterator();
	}
	
	/**
	 * Adds a transformer to the chain.
	 * 
	 * @param transformer
	 * @return true
	 */
	public boolean registerTransformer(ITransformer transformer) {
		return transformers.add(transformer);
	}

	
	/**
	 * Removes a transformer from the chain.
	 * 
	 * @param transformer
	 * @return true
	 */
	public boolean unregisterTransformer(ITransformer transformer) {
		return transformers.remove(transformer);
	}
	
	/**
	 * Adds an array of transformers to the chain.
	 * 
	 * @param transformers
	 * @return true
	 */
	public boolean registerTransformers(ITransformer... transformers) {
		boolean b = true;
		for(ITransformer t : transformers) {
			b &= registerTransformer(t);
		}
		return b;
	}

	/**
	 * Removes an array of transformers from the chain.
	 * 
	 * @param transformers
	 * @return true
	 */
	public boolean unregisterTransformers(ITransformer... transformers) {
		boolean b = true;
		for(ITransformer t : transformers) {
			b &= unregisterTransformer(t);
		}
		return b;
	}
	
	/**
	 * Iterates to the next node in the sequence and attempts to transform it.
	 * 
	 * @return Whether a there is a next node in the sequence.
	 * @throws Throwable
	 */
	public boolean step() throws Throwable {
		if(iterator.hasNext()) {
			ClassNode cn = iterator.next();
			transform(cn);
			return true;
		} else {
			return false;
		}
	}
	

	/**
	 * Iterates through every node in the sequence and attempts to transform it.
	 * @throws Throwable
	 */
	public void transformAll() throws Throwable {
		while(step());
	}
	
	/**
	 * Attempts to transform a single node.
	 * 
	 * @param cn Te node to transform.
	 * @throws Throwable
	 */
	public void transform(ClassNode cn) throws Throwable {
		for(ITransformer t : transformers) {
			if(t.accept(cn))
				t.run(cn);
		}
	}
	
	/**
	 * A utility method to fetch a node by name.
	 * 
	 * @param name
	 * @return A ClassNode or null.
	 */
	public ClassNode findClass(String name) {
		return classes.get(name);
	}
	
	/**
	 * @return An iterator that contains all of the nodes in the sequence.
	 */
	public Iterator<ClassNode> classIterator() {
		return classes.values().iterator();
	}
}
