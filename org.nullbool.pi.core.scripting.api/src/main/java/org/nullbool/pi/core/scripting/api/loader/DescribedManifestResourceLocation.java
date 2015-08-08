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

package org.nullbool.pi.core.scripting.api.loader;

import java.util.HashSet;
import java.util.Set;

import org.nullbool.pi.core.scripting.api.loader.finder.FinderStrategy;
import org.objectweb.asm.tree.ClassNode;
import org.topdank.byteengineer.commons.data.DataContainer;
import org.topdank.byteengineer.commons.data.JarInfo;
import org.topdank.byteengineer.commons.data.JarResource;
import org.topdank.byteengineer.commons.data.LocateableJarContents;
import org.topdank.byteio.in.SingleJarDownloader;

import com.google.gson.Gson;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 24 Apr 2015 at 18:07:16 <br>
 */
public class DescribedManifestResourceLocation extends RunnableResourceLocation<ResolvedDefinition> {

	protected final FinderStrategy<JarInfo> strategy;

	public DescribedManifestResourceLocation(FinderStrategy<JarInfo> strategy) {
		this.strategy = strategy;
	}

	@Override
	public Set<ResolvedDefinition> load() throws Exception {
		Set<ResolvedDefinition> set = new HashSet<ResolvedDefinition>();
		for (JarInfo jar : strategy.find()) {
			try {
				SingleJarDownloader<ClassNode> downloader = new SingleJarDownloader<ClassNode>(jar);
				downloader.download();
				LocateableJarContents<ClassNode> contents = downloader.getJarContents();
				DataContainer<JarResource> resources = contents.getResourceContents();
				JarResource desc = resources.namedMap().get("manifest.json");
				ExternalResourceDefinition ext = new Gson().fromJson(new String(desc.getData()), ExternalResourceDefinition.class);
				ResolvedDefinition resolvedDef = new ResolvedDefinition(ext, ResourceType.byName(ext.getType()), jar, contents);
				set.add(resolvedDef);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return set;
	}
}
