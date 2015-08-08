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

package org.objectweb.asm.commons.cfg.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tree<E extends Tree<E>> extends CopyOnWriteArrayList<E> {
	private static final long serialVersionUID = 4993791878851883165L;
	
	protected Tree<E> parent;

	public Tree() {
		super();
	}

	public Tree(Collection<? extends E> collection) {
		super(collection);
	}

    public void addFirst(E e) {
        Collection<E> list = new ArrayList<>();
        for (E element : this) {
            list.add(element);
        }
        clear();
        e.parent = this;
        add(e);
        addAll(list);
    }

    public void set(E predecessor, E successor) {
        Iterator<E> it = parent.iterator();
        Collection<E> es = new LinkedList<>();
        while (it.hasNext()) {
            E e = it.next();
            if (e.equals(predecessor)) {
                es.add(successor);
            } else {
                es.add(e);
            }
        }
        parent.clear();
        parent.addAll(es);
    }

	@SuppressWarnings("unchecked")
	public E parent() {
		return (E) parent;
	}

	public boolean hasParent() {
		return parent() != null;
	}

	public E previous() {
		Tree<E> p = parent;
		if (p == null) {
			return null;
		}
		Iterator<E> it = parent.iterator();
		E prev = null;
		while (it.hasNext()) {
			E e = it.next();
			if (e.equals(this)) {
				return prev;
			}
			prev = e;
		}
		return null;
	}

	public boolean hasPrevious() {
		return previous() != null;
	}

	public E next() {
		Tree<E> p = parent;
		if (p == null) {
			return null;
		}
		Iterator<E> it = parent.iterator();
		while (it.hasNext()) {
			E e = it.next();
			if (e.equals(this)) {
				return it.hasNext() ? it.next() : null;
			}
		}
		return null;
	}

	public boolean hasNext() {
		return next() != null;
	}

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : this) {
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }
}
