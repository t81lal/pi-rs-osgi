package org.nullbool.pi.core.scripting.api.klassmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nullbool.api.obfuscation.refactor.ClassTree;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.topdank.byteengineer.commons.data.JarContents;

/**
 * @author Bibl (don't ban me pls)
 * @created 28 Jun 2015 05:32:50
 */
public class HierarchalClassLoader extends ClassLoader {

	static int count = 0;
	
	private final ClassLoader parentClassLoader;
	private final Map<String, Class<?>> classCache;
	private final Map<String, ClassNode> nodeCache;
	private final ClassTree classTree;
	private final IAggregateFilter<String> filter;
	private final List<HierarchalClassLoader> children;
	private final int id;
	
	public HierarchalClassLoader(ClassLoader parentClassLoader, JarContents<ClassNode> contents) {
		this.parentClassLoader = parentClassLoader;
		classCache = new HashMap<String, Class<?>>();
		nodeCache  = contents.getClassContents().namedMap();
		classTree  = new ClassTree(nodeCache);
		filter     = new AggregateFilter<String>();
		children   = new ArrayList<HierarchalClassLoader>();
		
		id = count++;
		System.out.println("HierarchalClassLoader.enclosing_method(): " + id + " " + nodeCache.keySet().iterator().next() + " = " + new Exception().getStackTrace()[1].getClassName() + "." + new Exception().getStackTrace()[1].getMethodName());
	}
	
	public IAggregateFilter<String> filter() {
		return filter;
	}
	
	public List<HierarchalClassLoader> children() {
		return children;
	}
	
	public Class<?> lookup(String byte_name) {
		if(classCache.containsKey(byte_name))
			return classCache.get(byte_name);
		
		if(nodeCache.containsKey(byte_name) && byte_name.indexOf('/') == -1) {
			Class<?> klass = defineNode(nodeCache.get(byte_name));
			if(klass != null) {
				return cache(klass);
			}
		}
		
		return null;
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// System.out.println("Loading with " + id);
		
		String byte_name = name.replace(".", "/");
		
		if(byte_name.equals("org/nullbool/piexternal/game/api/wrappers/OldschoolClient")) {
			System.out.println("HierarchalClassLoader.loadClass() " + id);
		}
		
		//Exception e1 = null;
		//Exception e2 = null;
		
		Class<?> klass = null;
		try {
			klass = lookup(byte_name);
			if(klass != null)
				return klass;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			klass = parentClassLoader.loadClass(name);
			if(klass != null)
				return cache(klass);
		} catch(Exception e) {
//			e2 = e;
			e.printStackTrace();
		}

		try {
			klass = super.loadClass(name);
			if(klass != null)
				return cache(klass);
		} catch(Exception e) {
			e.printStackTrace();
//			if(e1 != null)
//				e1.printStackTrace();
//			if(e2 != null)
//				e2.printStackTrace();
//			e.printStackTrace();
		}
		
		return null;

		
		/*synchronized (children) {
			for(HierarchalClassLoader child : children) {
				if(child.filter.accept(byte_name)) {
					try {
						klass = child.loadClass(byte_name);
						if(klass != null) {
							return cache(klass);
						}
					} catch(ClassNotFoundException | NoClassDefFoundError e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		try {
			if(parentClassLoader instanceof HierarchalClassLoader) {
				klass = ((HierarchalClassLoader) parentClassLoader).lookup(byte_name);
			} else {
				klass = parentClassLoader.loadClass(name);
			}
		} catch(ClassNotFoundException | NoClassDefFoundError e) {
			try {
				klass = super.loadClass(name);
				if(klass != null) {
					System.out.println("Recovered " + klass);
					return cache(klass);
				}
			} catch(ClassNotFoundException | NoClassDefFoundError e2) {
				e.printStackTrace();
				e2.printStackTrace();
			}
		}
		
		if(klass != null) {
			return cache(klass);
		}
		
		return null;*/
	}
	
	protected Class<?> cache(Class<?> klass) {
		String name = klass.getCanonicalName();
		name = name.replace(".", "/");
		classCache.put(name, klass);
		return klass;
	}
	
	Class<?> defineNode(ClassNode node) {
		Class<?> klass = null;
		try {
			ClassWriter cw = new CacheLookupClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS, classTree);
			node.accept(cw);
			byte[] bytes = cw.toByteArray();
			klass = defineClass(null, bytes, 0, bytes.length);
		} catch(RuntimeException e) {
			e.printStackTrace();
		}
		return klass;
	}
}