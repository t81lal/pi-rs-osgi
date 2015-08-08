/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

package org.nullbool.topdank.eventbus.singleimpl;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.nullbool.topdank.eventbus.api.Event;
import org.nullbool.topdank.eventbus.api.EventBus;
import org.nullbool.topdank.eventbus.api.EventPriority;
import org.nullbool.topdank.eventbus.api.EventTarget;

public class SingleThreadEventBus implements EventBus {

	private final Set<CallbackData> registered;

	public SingleThreadEventBus() {
		registered = new HashSet<CallbackData>();
	}

	@Override
	public void register(Object src) {
		if (src == null)
			return;

		if (src instanceof Class) {
			registerStatic((Class<?>) src);
		} else {
			registerObject(src);
		}

		// System.out.println(registered);
	}

	private void registerObject(Object obj) {
		Class<?> c = obj.getClass();
		List<Method> methods = ReflectionHelper.deepCollateMethods(c);
		for (Method m : methods) {
			if (!Modifier.isStatic(m.getModifiers())) {
				EventTarget manifest = m.getAnnotation(EventTarget.class);
				if (manifest != null) {
					Class<? extends Event> eventKlass = valid(m);
					if (eventKlass != null) {
						VirtualCallbackData data = new VirtualCallbackData(m, eventKlass, obj);
						registered.add(data);
					}
				}
			}
		}
	}

	private void registerStatic(Class<?> c) {
		Collection<Class<?>> classes = ReflectionHelper.getSuperClasses(c);
		List<Method> methods = ReflectionHelper.deepCollateMethods(classes, c);
		for (Method m : methods) {
			if (Modifier.isStatic(m.getModifiers())) {
				EventTarget manifest = m.getAnnotation(EventTarget.class);
				if (manifest != null) {
					Class<? extends Event> eventKlass = valid(m);
					if (eventKlass != null) {
						StaticCallbackData data = new StaticCallbackData(m, eventKlass);
						registered.add(data);
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Event> valid(Method m) {
		Parameter[] params = m.getParameters();
		if (params.length != 1)
			return null;
		Class<?> klass = params[0].getType();
		if (Event.class.isAssignableFrom(klass))
			return (Class<? extends Event>) klass;
		return null;
	}

	@Override
	public void register(Object src, @SuppressWarnings("unchecked") Class<? extends Event>... eventClass) {
		if (src == null)
			return;

		if (src instanceof Class) {
			registerStatic((Class<?>) src);
		} else {
			registerObject(src);
		}
	}

	@Override
	public void unregister(Object src) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void unregister(Object src, @SuppressWarnings("unchecked") Class<? extends Event>... eventClass) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void dispatch(Event... events) {
		for (Event e : events) {
			Class<?> eventKlass = e.getClass();
			for (CallbackData d : registered) {
				if (d.same(eventKlass)) {
					System.out.println(eventKlass + " same as " + d.eventKlass + " " + registered);
					try {
						d.invoke(e);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	public void destroy() {
		registered.clear();
	}

	static abstract class CallbackData {
		final Method method;
		final Class<? extends Event> eventKlass;

		public CallbackData(Method method, Class<? extends Event> eventKlass) {
			this.method = method;
			method.setAccessible(true);
			this.eventKlass = eventKlass;
		}

		public boolean same(Object o) {
			if (o instanceof Class)
				return eventKlass.isAssignableFrom((Class<?>) o);
			return false;
		}

		public abstract void invoke(Event e) throws Exception;
	}

	static class VirtualCallbackData extends CallbackData {
		private final Object obj;

		public VirtualCallbackData(Method method, Class<? extends Event> eventKlass, Object obj) {
			super(method, eventKlass);
			this.obj = obj;
		}

		@Override
		public boolean same(Object o) {
			if (o instanceof Class)
				return eventKlass.isAssignableFrom((Class<?>) o);
			return obj.equals(o);
		}

		@Override
		public void invoke(Event e) throws Exception {
			method.invoke(obj, e);
		}

		@Override
		public String toString() {
			String name = obj == null ? "null" : obj.getClass().getCanonicalName();
			String mName = method.getName();
			String eName = eventKlass.getSimpleName();
			return "VirtualCallbackData [obj=" + name + ", method=" + mName + ", eventKlass=" + eName + "]";
		}
	}

	static class StaticCallbackData extends CallbackData {

		public StaticCallbackData(Method method, Class<? extends Event> klass) {
			super(method, klass);
		}

		@Override
		public void invoke(Event e) throws Exception {
			method.invoke(null, e);
		}
	}

	static class DefaultSetMap<T> extends HashMap<T, Set<CallbackData>> {
		private static final long serialVersionUID = 1797882769602866826L;

		@Override
		public Set<CallbackData> get(Object o) {
			Set<CallbackData> d = super.get(o);
			if (d == null)
				d = new HashSet<CallbackData>();
			return d;
		}
	}

	@EventTarget(priority = EventPriority.HIGHEST)
	public void test(Event e) {
		System.out.println("e: " + e);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getCanonicalName()).append("\n");
		Iterator<CallbackData> it = registered.iterator();
		while (it.hasNext()) {
			CallbackData d = it.next();
			sb.append("\t").append(d);
			if (it.hasNext())
				sb.append("\n");
		}
		return sb.toString();
	}
}
