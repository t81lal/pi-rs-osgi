package org.nullbool.pi.core.engine.impl.transformers;

import java.util.Map;

import org.nullbool.pi.core.engine.api.transform.IAPIHelper;
import org.nullbool.pi.core.hook.api.ClassHook;
import org.nullbool.pi.core.hook.api.Constants;
import org.nullbool.pi.core.hook.api.HookMap;
import org.nullbool.pi.core.hook.api.MethodHook;
import org.objectweb.asm.tree.ClassNode;

public class CallbackTransformer extends AbstractTransformer {

	public CallbackTransformer(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) {
		super(classes, hooks, helper);
	}

	@Override
	public void run(ClassNode cn) {

		// TODO: dynamic
		ClassHook callbackOwnerHook = hook;

		for (MethodHook mh : hook.methods()) {
			System.out.println(mh.refactored() + " : " + mh.val(Constants.METHOD_TYPE) + " : " + mh.val(Constants.DESC));
			switch (mh.val(Constants.METHOD_TYPE)) {

			case "null":
				System.out.println("yolo");
				break;

			}
		}
	}
}