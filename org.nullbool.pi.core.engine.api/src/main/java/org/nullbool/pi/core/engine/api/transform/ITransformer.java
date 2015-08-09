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

import org.objectweb.asm.tree.ClassNode;

/**
 * A single component that can be chained to modify classes.
 * 
 * @see ClassNode
 * @see TransformationEngine
 * 
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 00:35:55
 */
public interface ITransformer {

	/**
	 * @param cn A prospective node to transform.
	 * @return Whether or not to transform the node.
	 */
	public boolean accept(ClassNode cn);
	
	/**
	 * @param cn The node to transform.
	 */
	public void run(ClassNode cn);
}
