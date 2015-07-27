package org.nullbool.pi.core.engine.impl.transformers;

import java.util.Map;

import org.nullbool.pi.core.engine.api.transform.IAPIHelper;
import org.nullbool.pi.core.engine.impl.DelegateCanvas;
import org.nullbool.pi.core.hook.api.HookMap;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class CanvasReplacerTransformer extends AbstractTransformer {
	
	private static final String NEW_CANVAS_CLASS = DelegateCanvas.class.getCanonicalName().replace(".", "/");;
	
	public CanvasReplacerTransformer(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) {
		super(classes, hooks, helper);
	}

	@Override
	public void run(ClassNode cn) {
		if(cn.superName.equals("java/awt/Canvas")) {
			cn.superName = NEW_CANVAS_CLASS;
			for(MethodNode m : cn.methods) {
				if(m.name.equals("<init>")) {
					for(AbstractInsnNode ain : m.instructions.toArray()) {
						if(ain.opcode() == INVOKESPECIAL) {
							MethodInsnNode min = (MethodInsnNode) ain;
							if(min.owner.equals("java/awt/Canvas") && min.name.equals("<init>")) {
								min.owner = NEW_CANVAS_CLASS;
							}
						}
					}
				}
			}
		}
	}
}
