package org.nullbool.pi.core.engine.api.transform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.tree.ClassNode;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 00:36:07
 */
public abstract class TransformationEngine {

	private final Map<String, ClassNode> classes;
	private final List<ITransformer> transformers;
	private transient Iterator<ClassNode> iterator;
	
	public TransformationEngine(Map<String, ClassNode> classes) {
		this.classes = classes;
		transformers = new ArrayList<ITransformer>();
		iterator = classIterator();
	}
	
	public boolean registerTransformer(ITransformer transformer) {
		return transformers.add(transformer);
	}

	public boolean unregisterTransformer(ITransformer transformer) {
		return transformers.remove(transformer);
	}
	
	public boolean registerTransformers(ITransformer... transformers) {
		boolean b = true;
		for(ITransformer t : transformers) {
			b &= registerTransformer(t);
		}
		return b;
	}
	
	public boolean unregisterTransformers(ITransformer... transformers) {
		boolean b = true;
		for(ITransformer t : transformers) {
			b &= unregisterTransformer(t);
		}
		return b;
	}
	
	public boolean step() throws Throwable {
		if(iterator.hasNext()) {
			ClassNode cn = iterator.next();
			transform(cn);
			return true;
		} else {
			return false;
		}
	}
	

	public void transformAll() throws Throwable {
		while(step());
	}
	
	public void transform(ClassNode cn) throws Throwable {
		for(ITransformer t : transformers) {
			if(t.accept(cn))
				t.run(cn);
		}
	}
	
	public ClassNode findClass(String name) {
		return classes.get(name);
	}
	
	public Iterator<ClassNode> classIterator() {
		return classes.values().iterator();
	}
}