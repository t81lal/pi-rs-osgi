package org.nullbool.pi.core.engine.api.transform;

import org.objectweb.asm.tree.ClassNode;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 00:35:55
 */
public abstract interface ITransformer {

	public abstract boolean accept(ClassNode cn);
	
	public abstract void run(ClassNode cn);
}