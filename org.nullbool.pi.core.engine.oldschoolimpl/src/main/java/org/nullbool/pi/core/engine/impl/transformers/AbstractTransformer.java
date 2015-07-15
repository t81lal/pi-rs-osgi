package org.nullbool.pi.core.engine.impl.transformers;

import java.util.Map;

import org.nullbool.pi.core.engine.api.transform.IAPIHelper;
import org.nullbool.pi.core.engine.api.transform.ITransformer;
import org.nullbool.pi.core.hook.api.ClassHook;
import org.nullbool.pi.core.hook.api.HookMap;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public abstract class AbstractTransformer implements ITransformer, Opcodes {

	protected final Map<String, ClassNode> classes;
	protected final HookMap hooks;
	protected final IAPIHelper helper;
	protected ClassHook hook;
	
	public AbstractTransformer(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) {
		this.hooks = hooks;
		this.classes = classes;
		this.helper = helper;
	}
	
	@Override
	public boolean accept(ClassNode cn) {
		return (hook = hooks.forName(cn.name, true)) != null;
	}
	
	@Override
	public abstract void run(ClassNode cn);
}