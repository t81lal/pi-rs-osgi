package org.nullbool.pi.core.engine.impl.transformers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.tree.ClassNode;
import org.topdank.byteengineer.commons.asm.ASMFactory;
import org.topdank.byteengineer.commons.asm.DefaultASMFactory;
import org.topdank.byteengineer.commons.data.LocateableJarContents;

import sun.misc.URLClassPath;

// This has to be done since the other bundle would need to import
	// this game api package.
	public class JarClassLoader extends ClassLoader {

		private final ClassLoader myParent;
		private ASMFactory<ClassNode> factory;

		private Map<String, ClassNode> fastNodeCache;
		private Map<String, Class<?>> cache;
		private URLClassPath ucp;

		public JarClassLoader(LocateableJarContents<ClassNode> contents) {
			this(contents, null, new DefaultASMFactory());
		}

		/**
		 * @param contents Reference to the JarContents object.
		 */
		public JarClassLoader(LocateableJarContents<ClassNode> contents, ClassLoader parent) {
			this(contents, parent, new DefaultASMFactory());
		}

		/**
		 * @param contents Reference to the JarContents object.
		 * @param parent
		 * @param factory ASMFactory.
		 */
		public JarClassLoader(LocateableJarContents<ClassNode> contents, ClassLoader parent, ASMFactory<ClassNode> factory) {
			this.factory = factory;

			ClassLoader _parent = parent;
			if (_parent == null)
				_parent = getClass().getClassLoader();
				//_parent = IGameClient.class.getClassLoader();
			myParent = _parent;
			
			for(ClassNode cn : contents.getClassContents()) {
				if(cn.name.endsWith("IOldschoolClient")) {
					System.out.println("JarClassLoader.JarClassLoader() " + cn);
				}
			}
			
			System.out.printf("Parent=%s.%n", myParent);

			cache = new HashMap<String, Class<?>>();
			fastNodeCache = new HashMap<String, ClassNode>();
			ucp = new URLClassPath(new URL[0]);
			add(contents);
		}

		public void add(LocateableJarContents<ClassNode> contents) {
			for (URL url : contents.getJarUrls()) {
				ucp.addURL(url);
			}
			for (ClassNode cn : contents.getClassContents()) {
				fastNodeCache.put(cn.name, cn);
			}
		}

		@Override
		public URL findResource(String name) {
			return ucp.findResource(name, true);
		}

		/**
		 * Defines and caches a ClassNode.
		 *
		 * @param cn ClassNode to define
		 * @return Defined Class.
		 */
		public Class<?> defineNode(ClassNode cn) {
			byte[] bytes = factory.write(cn);
			Class<?> c = defineClass(cn.name.replace("/", "."), bytes, 0, bytes.length);
			cache(c, cn.name);
			fastNodeCache.put(cn.name, cn);
			return c;
		}

		public void forceCache(Class<?> c, String name) {
			cache.put(name.replace(".", "/"), c);
		}

		public void cache(Class<?> c, String name) {
			if ((c != null) && !name.startsWith("java")) {
				cache.put(name.replace(".", "/"), c);
			}
		}

		@Override
		public Class<?> findClass(String name) throws ClassNotFoundException {
			String binName = name.replace(".", "/");
			// try the loaded cache
			if (cache.containsKey(binName))
				return cache.get(binName);

			// try the node cache
			ClassNode node = fastNodeCache.get(binName); // '/'
			if (node != null)
				return defineNode(node);

			Class<?> c = myParent.loadClass(name);
			cache(c, name);
			return c;
		}
	}