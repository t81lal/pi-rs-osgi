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

package org.nullbool.pi.core.scripting.api.loader.finder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 24 Apr 2015 at 21:34:23 <br>
 */
public abstract class FolderSearchFinderStrategy<T> implements FinderStrategy<T> {

	private final boolean deepSearch;
	private final Set<File> directories;

	public FolderSearchFinderStrategy(boolean deepSearch, Collection<File> dirs) {
		this.deepSearch = deepSearch;
		directories = new HashSet<File>();
		for (File f : dirs) {
			directories.add(f);
		}
	}

	public FolderSearchFinderStrategy(boolean deepSearch, File... dirs) {
		this.deepSearch = deepSearch;
		directories = new HashSet<File>();
		for (File f : dirs) {
			directories.add(f);
		}
	}

	@Override
	public Set<T> find() {
		Set<T> aggregateSet = new HashSet<T>();
		for (File dir : directories) {
			if (deepSearch) {
				aggregateSet.addAll(convert(aggregateSet, searchRecursively(dir)));
			} else {
				aggregateSet.addAll(convert(aggregateSet, searchFlat(dir)));
			}
		}
		return aggregateSet;
	}

	protected abstract T create(File f);

	protected abstract boolean accept(File f);

	private List<T> convert(Set<T> previous, List<File> files) {
		List<T> jars = new ArrayList<T>(files.size());
		for (File file : files) {
			T t = create(file);
			if ((t != null) && !previous.contains(t))
				jars.add(create(file));
		}
		return jars;
	}

	private List<File> searchFlat(File dir) {
		List<File> files = new ArrayList<File>();
		for (File f : dir.listFiles()) {
			if (f.isFile() && accept(f)) {
				files.add(f);
			}
		}
		return files;
	}

	private List<File> searchRecursively(File dir) {
		List<File> files = new ArrayList<File>();
		for (File f : dir.listFiles()) {
			if (f.isFile() && accept(f)) {
				files.add(f);
			} else if (f.isDirectory()) {
				files.addAll(searchRecursively(f));
			}
		}
		return files;
	}
}
