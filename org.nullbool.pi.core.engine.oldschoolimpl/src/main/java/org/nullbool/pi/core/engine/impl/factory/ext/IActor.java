package org.nullbool.pi.core.engine.impl.factory.ext;

import java.io.File;

import org.objectweb.asm.tree.ClassNode;
import org.topdank.byteengineer.commons.data.JarContents;

public interface IActor<T>{

	public T act(FileSet runningJar, JarContents<ClassNode> contents, File baseDir) throws Exception;

	public Class<T> type();
}