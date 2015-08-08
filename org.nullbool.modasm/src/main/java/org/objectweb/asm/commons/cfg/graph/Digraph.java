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

package org.objectweb.asm.commons.cfg.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.objectweb.asm.commons.cfg.Block;

/**
 * @author Tyler Sedlar
 */
public class Digraph<V, E> implements Iterable<V> {

    private final Map<V, Set<E>> graph = new HashMap<>();

    @SuppressWarnings("unchecked")
    public Set<E> edgeAt(int index) {
        return (Set<E>) graph.values().toArray()[index];
    }

    public int size() {
        return graph.size();
    }

    public boolean containsVertex(V vertex) {
        return graph.containsKey(vertex);
    }

    public boolean containsEdge(V vertex, E edge) {
        return graph.containsKey(vertex) && graph.get(vertex).contains(edge);
    }

    public boolean addVertex(V vertex) {
        if (graph.containsKey(vertex)) return false;
        graph.put(vertex, new HashSet<E>());
        return true;
    }

    public void addEdge(V start, E dest) {
        if (!graph.containsKey(start)) return;
        graph.get(start).add(dest);
    }

    public void removeEdge(V start, E dest) {
        if (!graph.containsKey(start)) return;
        graph.get(start).remove(dest);
    }

    public Set<E> edgesFrom(V node) {
        return Collections.unmodifiableSet(graph.get(node));
    }

    public void graph(Digraph<V, E> graph) {
        this.graph.putAll(graph.graph);
    }

    @Override
    public final Iterator<V> iterator() {
        return graph.keySet().iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        Iterator<V> it = graph.keySet().iterator();
        while(it.hasNext()) {
        	V v = it.next();
        	sb.append(String.format("%s", v));
        	
        	Set<E> set = graph.get(v);
        	if(set.size() > 0) {
        		sb.append(System.lineSeparator());
        		
        		Iterator<E> it2 = set.iterator();
            	while(it2.hasNext()) {
            		E e = it2.next();
            		sb.append("     > ").append(toString(e));
            		if(it2.hasNext())
            			sb.append(System.lineSeparator());
            	}
        	}
        	
        	if(it.hasNext())
        		sb.append(System.lineSeparator());
        }
        
        return sb.toString();
    }
    
    public String toString(E e) {
    	String s = null;
        if(e instanceof Block) {
        	s = ((Block) e).headerString();
        } else {
        	s = e.toString();
        }
        return s;
    }
    
    public String toString(Set<E> set) {
        Iterator<E> it = set.iterator();
        if (! it.hasNext())
            return "emtpy";

        StringBuilder sb = new StringBuilder();
        for (;;) {
            E e = it.next();
            String s = null;
            if(e instanceof Block) {
            	s = ((Block) e).headerString();
            } else {
            	s = e.toString();
            }
            
            sb.append(e == this ? "(this Collection)" : s);
            if (! it.hasNext())
                return sb.toString();
            sb.append(',').append(' ');
        }
    }

    public Map<V, Set<E>> graph() {
		return graph;
	}

	public void flush() {
        graph.clear();
    }
}
