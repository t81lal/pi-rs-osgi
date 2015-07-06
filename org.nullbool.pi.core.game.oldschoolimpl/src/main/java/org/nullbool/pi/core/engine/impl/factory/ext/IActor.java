package org.nullbool.pi.core.engine.impl.factory.ext;

import java.io.File;

import org.objectweb.asm.tree.ClassNode;
import org.topdank.byteengineer.commons.data.JarContents;

public abstract interface IActor<T>{

	public abstract T act(FileSet runningJar, JarContents<ClassNode> contents, File baseDir) throws Exception;

	public abstract Class<T> type();
}