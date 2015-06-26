package org.nullbool.pi.core.scripting.api.klassmodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.nullbool.api.obfuscation.refactor.ClassTree;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.topdank.byteengineer.commons.data.JarContents;

/**
 * @author Bibl (don't ban me pls)
 * @created 24 Jun 2015 20:30:13
 */
public class ScriptClassLoader extends ClassLoader {

	// private final MasterScriptLoader parentClassLoader;
	// private final JarContents<ClassNode> contents;
	private final IAggregateFilter<String> classFilter;
	private final Map<String, ClassNode> classNodes;
	private final Map<String, Class<?>> loadedClasses;
	private final ClassTree classTree;

	public ScriptClassLoader(JarContents<ClassNode> contents) {
		// this.contents = contents;
		classFilter = new AggregateFilter<String>();
		classNodes = new HashMap<String, ClassNode>();
		loadedClasses = new HashMap<String, Class<?>>();
		
		classTree = new ClassTree(contents.getClassContents());
		
		// populate nodes.
		for(Entry<String, ClassNode> e : contents.getClassContents().namedMap().entrySet()) {
			classNodes.put(e.getKey().replace("/", "."), e.getValue());
		}
	}
	
	public void cacheClass(String name, Class<?> klass) {
		loadedClasses.put(name, klass);
	}
	
	public IAggregateFilter<String> filter() {
		return classFilter;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// standardise name
		name = name.replace("/", ".");
		if(loadedClasses.containsKey(name))
			return loadedClasses.get(name);
		
		if(classNodes.containsKey(name)) {
			ClassNode cn = classNodes.get(name);
			Class<?> klass = define(cn);
			if(klass != null) {
				loadedClasses.put(name, klass);
				return klass;
			}
		}
		
		Class<?> klass = super.loadClass(name);
		if(klass != null) {
			loadedClasses.put(name, klass);
			return klass;
		}
		
		return null;
		// throw new ClassNotFoundException("Couldn't find " + name + " from " + this);
	}
	
	protected Class<?> define(ClassNode cn) {
		// has to be created every define
		ClassWriter classWriter = new CacheLookupClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS, classTree);
		cn.accept(classWriter);
		byte[] bytes = classWriter.toByteArray();
		return defineClass(cn.name.replace("/", "."), bytes, 0, bytes.length);
	}
}