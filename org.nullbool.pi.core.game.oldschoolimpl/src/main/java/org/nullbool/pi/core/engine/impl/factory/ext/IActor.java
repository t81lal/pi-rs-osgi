package org.nullbool.pi.core.engine.impl.factory.ext;

import java.io.File;

import org.objectweb.asm.tree.ClassNode;
import org.topdank.byteengineer.commons.data.JarContents;

public abstract interface IActor {

	public abstract void act(JarContents<ClassNode> contents, File baseDir) throws Exception;
}