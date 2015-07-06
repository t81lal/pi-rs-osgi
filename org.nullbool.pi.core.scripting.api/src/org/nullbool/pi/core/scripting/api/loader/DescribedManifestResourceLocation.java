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