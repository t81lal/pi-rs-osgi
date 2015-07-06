package org.nullbool.pi.core.scripting.api.loader;

import java.util.Map;
import java.util.Set;

public class BasicResourcePool extends RefreshableResourcePool<ResolvedDefinition, RunnableResourceLocation<ResolvedDefinition>> {

	public BasicResourcePool() {
		super();
	}

	public BasicResourcePool(Map<RunnableResourceLocation<ResolvedDefinition>, Set<ResolvedDefinition>> load) {
		super(load);
	}
}