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

package org.nullbool.pi.core.scripting.api.klassmodel;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Set;

import org.nullbool.api.obfuscation.refactor.ClassTree;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public class CacheLookupClassWriter extends ClassWriter {

	private final ClassTree classTree;
	
	/**
	 * @param flags
	 */
	public CacheLookupClassWriter(int flags, ClassTree classTree) {
		super(flags);
		this.classTree = classTree;
	}
	
	private ClassNode create(String type) {
		//String jtype = type.replace("/", ".");
		
		ClassNode cn = classTree.getClass(type);
		if(cn != null)
			return cn;
		
		System.err.println("Falling back to classloader search for " + type);
		
		ClassReader cr;
		try {
			cr = new ClassReader(type);
		} catch (IOException e) {
			return null;
		}
		cn = new ClassNode();
		cr.accept(cn, 0);
				
		return cn;
	}
	
    @Override
	protected String getCommonSuperClass(final String type1, final String type2) {
    	ClassNode ccn = classTree.getClass(type1);
    	ClassNode dcn = classTree.getClass(type2);
    	
    	if(ccn == null) {
    		classTree.build(create(type1));
    		return getCommonSuperClass(type1, type2);
    	}
    	
    	if(dcn == null) {
    		classTree.build(create(type2));
    		return getCommonSuperClass(type1, type2);
    	}
    	
        Set<ClassNode> c = classTree.getSupers(ccn);
        Set<ClassNode> d = classTree.getSupers(dcn);
        
        if(c.contains(dcn))
        	return type1;
        
        if(d.contains(ccn))
        	return type2;
        
        if(Modifier.isInterface(ccn.access) || Modifier.isInterface(dcn.access)) {
        	return "java/lang/Object";
        } else {
        	do {
        		ClassNode nccn = classTree.getClass(ccn.superName);
        		if(nccn == null)
        			break;
        		ccn = nccn;
        		c = classTree.getSupers(ccn);
        	} while(!c.contains(dcn));
        	return ccn.name;
        }
    }
}
