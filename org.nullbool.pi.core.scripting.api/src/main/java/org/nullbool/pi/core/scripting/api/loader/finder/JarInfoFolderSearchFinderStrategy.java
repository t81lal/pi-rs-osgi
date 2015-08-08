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
import java.util.List;

import org.topdank.byteengineer.commons.data.JarInfo;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 24 Apr 2015 at 21:56:35 <br>
 */
public class JarInfoFolderSearchFinderStrategy extends FolderSearchFinderStrategy<JarInfo> {

	public JarInfoFolderSearchFinderStrategy(boolean deepSearch, File... dirs) {
		super(deepSearch, dirs);
	}

	public JarInfoFolderSearchFinderStrategy(boolean deepSearch, List<File> directories) {
		super(deepSearch, directories);
	}

	@Override
	protected JarInfo create(File f) {
		return new JarInfo(f);
	}

	@Override
	protected boolean accept(File f) {
		return f.getName().endsWith(".jar");
	}
}
