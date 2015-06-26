package org.nullbool.pi.core.scripting.api.loader;

import java.util.Map;
import java.util.Set;

public class BasicResourcePool extends RefreshableResourcePool<ResourceDefinition, RunnableResourceLocation<ResourceDefinition>> {

	public BasicResourcePool() {
		super();
	}

	public BasicResourcePool(Map<RunnableResourceLocation<ResourceDefinition>, Set<ResourceDefinition>> load) {
		super(load);
	}
}