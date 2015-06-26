package org.nullbool.pi.core.ui.menu.script;

import java.util.HashSet;
import java.util.Set;

import org.nullbool.pi.core.scripting.api.loader.ExternalResourceDefinition;
import org.nullbool.pi.core.scripting.api.loader.ResourceDefinition;
import org.nullbool.pi.core.scripting.api.loader.RunnableResourceLocation;
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
public class DescribedManifestResourceLocation extends RunnableResourceLocation<ResourceDefinition> {

	protected final FinderStrategy<JarInfo> strategy;

	public DescribedManifestResourceLocation(FinderStrategy<JarInfo> strategy) {
		this.strategy = strategy;
	}

	@Override
	public Set<ResourceDefinition> load() throws Exception {
		Set<ResourceDefinition> set = new HashSet<ResourceDefinition>();
		for (JarInfo jar : strategy.find()) {
			try {
				SingleJarDownloader<ClassNode> downloader = new SingleJarDownloader<ClassNode>(jar);
				downloader.download();
				LocateableJarContents<ClassNode> contents = downloader.getJarContents();
				DataContainer<JarResource> resources = contents.getResourceContents();
				JarResource desc = resources.namedMap().get("info.json");
				ExternalResourceDefinition ext = new Gson().fromJson(new String(desc.getData()), ExternalResourceDefinition.class);
				ResourceDefinition def = new ResourceDefinition(ext.getPriority(), jar, contents, ext.getKlassName(), ext.getName(), ext.getDescription(),
						ext.getVersion(), ext.getAuthors());
				set.add(def);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return set;
	}
}