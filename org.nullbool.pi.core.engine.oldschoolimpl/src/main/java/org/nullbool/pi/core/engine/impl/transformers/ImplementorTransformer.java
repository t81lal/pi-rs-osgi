package org.nullbool.pi.core.engine.impl.transformers;

import java.util.Map;

import org.nullbool.pi.core.engine.api.transform.IAPIHelper;
import org.nullbool.pi.core.hook.api.HookMap;
import org.objectweb.asm.tree.ClassNode;

public class ImplementorTransformer extends AbstractTransformer {

	public ImplementorTransformer(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) {
		super(classes, hooks, helper);
	}

	@Override
	public void run(ClassNode cn) {
		/*for(InterfaceMapping im : hook.getInterfaces()) {
			String c = im.getCanonicalName();
			if(!cn.interfaces.contains(c))
				cn.interfaces.add(c);
		}*/
		
		String name = hook.refactored();
		if(name == null)
			return;
		
		String interfaceName = helper.canonicalName(name);
		if (interfaceName != null) {
			//System.out.println("name: " + name);
			//System.out.println("intf: " + interfaceName);
			String fullName = (interfaceName.startsWith(helper.accessorBase()) ? "" : helper.accessorBase()) + interfaceName;
			//System.out.println("full: " + fullName);
			cn.interfaces.add(fullName);
		}
	}
}