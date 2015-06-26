package org.nullbool.pi.core.scripting.api.loader;

import java.util.Arrays;

import org.objectweb.asm.tree.ClassNode;
import org.topdank.byteengineer.commons.data.JarInfo;
import org.topdank.byteengineer.commons.data.LocateableJarContents;

public class ResourceDefinition implements Comparable<ResourceDefinition> {

	private final int priority;
	private final JarInfo jarInfo;
	private final LocateableJarContents<ClassNode> contents;
	private final String klassName;
	private final String name;
	private final String description;
	private final String version;
	private final String[] authors;

	public ResourceDefinition(int priority, JarInfo jarInfo, LocateableJarContents<ClassNode> contents, String klassName, String name, String description,
			String version, String[] authors) {
		this.priority = priority;
		this.jarInfo = jarInfo;
		this.contents = contents;
		this.klassName = klassName;
		this.name = name;
		this.description = description;
		this.version = version;
		this.authors = authors;
	}

	public int getPriority() {
		return priority;
	}

	public JarInfo getJarInfo() {
		return jarInfo;
	}

	public LocateableJarContents<ClassNode> getContents() {
		return contents;
	}

	public String getKlassName() {
		return klassName;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getVersion() {
		return version;
	}

	public String[] getAuthors() {
		return authors;
	}

	@SuppressWarnings("unchecked")
	public <T> Class<T> getClass(ClassLoader cl, Class<T> superClass, String name) throws Exception {
		name = name.replace(".", "/");
		for (ClassNode cn : contents.getClassContents()) {
			if (cn.name.equals(name)) {
				Class<?> c = cl.loadClass(name);
				if (superClass.isAssignableFrom(c)) {
					return (Class<T>) c;
				} else {
					throw new Exception(c.getCanonicalName() + " isn't a subclass of " + superClass.getCanonicalName());
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "ResourceDefinition [priority=" + priority + ", jarInfo=" + jarInfo + ", klassName=" + klassName + ", name=" + name + ", description="
				+ description + ", version=" + version + ", authors=" + Arrays.toString(authors) + "]";
	}

	@Override
	public int compareTo(ResourceDefinition o) {
		int p = o.priority;
		// return priority > p ? +1 : priority < p ? -1 : 0;
		return (~priority - ~p) >>> 31 | -((priority - p) >>> 31);
	}
}