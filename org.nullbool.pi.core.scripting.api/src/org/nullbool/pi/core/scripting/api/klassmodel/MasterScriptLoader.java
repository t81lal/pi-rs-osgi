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

	private final List<ScriptClassLoader> children;
	
	public MasterScriptLoader(JarContents<ClassNode> contents, ClassLoader osgiParent) {
		super(osgiParent, contents);
		
		children = new ArrayList<ScriptClassLoader>();
		
		filter().connect(new DefaultClassFilter());
		filter().connect(new IFilter<String>() {

			@Override
			public boolean accept(String t) {
				return t.startsWith("org.nullbool");
			}
		});
		filter().connect(new IFilter<String>() {
			
			@Override
			public boolean accept(String t) {
				return getClassNodes().containsKey(t.replace("/", "."));
			}
		});
	}
	
	public List<ScriptClassLoader> children() {
		return children;
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		name = name.replace("/", ".");
		
		Class<?> klass;
		try {
			IAggregateFilter<String> filter = filter();
			if(filter.accept(name)) {
				klass = super.loadClass(name);
				if(klass != null) {
					return klass;
				}
			}
		} catch(ClassNotFoundException e) {
			System.err.println(e.getMessage() + " in master.");
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
		
		return findClass(name);
	}
	
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		name = name.replace("/", ".");
		
		Class<?> klass = parent.loadClass(name);
		if(klass != null) {
			cacheClass(name, klass);
			return klass;
		} else {
			throw new ClassNotFoundException();
		}
	}
}