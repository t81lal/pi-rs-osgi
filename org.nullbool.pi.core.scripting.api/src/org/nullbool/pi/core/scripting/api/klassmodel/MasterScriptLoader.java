/**
 * 
 */
package org.nullbool.pi.core.scripting.api.klassmodel;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.topdank.byteengineer.commons.data.JarContents;

/**
 * A custom ClassLoader implementation that bridges reloadable classes loaded
 * by scripts or tasks and the underlying OSGi bundle that provides the game
 * and api classes.
 * 
 * @author Bibl (don't ban me pls)
 * @created 20 Jun 2015 17:55:40
 */
public class MasterScriptLoader extends ScriptClassLoader {

	private final ClassLoader osgiParent;
	private final List<ScriptClassLoader> children;
	
	public MasterScriptLoader(JarContents<ClassNode> contents, ClassLoader osgiParent) {
		super(contents);
		
		this.osgiParent = osgiParent;
		children = new ArrayList<ScriptClassLoader>();
	}
	
	public List<ScriptClassLoader> children() {
		return children;
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		name = name.replace("/", ".");
		
		Class<?> klass = super.loadClass(name);
		if(klass != null) {
			return klass;
		}
		
		// check the other children
		synchronized (children) {
			for(ScriptClassLoader classLoader : children) {
				if(classLoader.filter().accept(name)) {
					try {
						klass = classLoader.loadClass(name);
						if(klass != null) {
							return klass;
						}
					} catch(ClassNotFoundException | NoClassDefFoundError e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		IAggregateFilter<String> filter = filter();
		synchronized (filter) {
			if(!filter.accept(name)) {
				throw new ClassNotFoundException("Cannot load " + name + " from master class loader.");
			}
		}
		
		return findClass(name);
	}
	
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		name = name.replace("/", ".");
		
		Class<?> klass = osgiParent.loadClass(name);
		if(klass != null) {
			cacheClass(name, klass);
			return klass;
		} else {
			throw new ClassNotFoundException();
		}
	}
}