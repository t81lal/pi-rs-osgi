package org.nullbool.pi.core.engine.impl.transformers;

import java.util.Map;

import org.nullbool.pi.core.engine.api.transform.IAPIHelper;
import org.nullbool.pi.core.engine.api.transform.ITransformer;
import org.nullbool.pi.core.engine.api.transform.TransformationEngine;
import org.nullbool.pi.core.hook.api.HookMap;
import org.objectweb.asm.tree.ClassNode;

public class TransformationEngineImpl extends TransformationEngine {

	public TransformationEngineImpl(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) {
		super(classes);
		
		registerTransformers(new ITransformer[]{				
				new GetterSetterTransformer(classes, hooks, helper), 
//				 new CallbackTransformer(classes, hooks, helper), 
				new ImplementorTransformer(classes, hooks, helper)
		});
	}
}