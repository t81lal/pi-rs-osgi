package org.nullbool.topdank.eventbus.api;

public abstract interface EventBus {

	public static final String THREAD_TYPE = "thread-model";
	
	public abstract void register(Object src);

	public abstract void register(Object src, @SuppressWarnings("unchecked") Class<? extends Event>... eventClass);

	public abstract void unregister(Object src);

	public abstract void unregister(Object src, @SuppressWarnings("unchecked") Class<? extends Event>... eventClass);

	public abstract void dispatch(Event... events);
}
