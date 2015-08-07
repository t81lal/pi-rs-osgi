package org.nullbool.pi.core.engine.impl.factory.ext;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.nullbool.pi.core.engine.impl.factory.OldschoolContextFactory;
import org.objectweb.asm.tree.ClassNode;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.BundleWiring;
import org.topdank.byteengineer.commons.data.JarContents;
import org.topdank.byteengineer.commons.data.JarInfo;
import org.topdank.byteengineer.commons.data.JarResource;
import org.topdank.byteio.in.SingleJarDownloader;

/**
 * @author Bibl (don't ban me pls)
 * @created 28 Jun 2015 09:49:56
 */
public class InstallerActor implements IActor<ClassLoader> {

	static ClassLoader installProvider(File f) {
		System.out.println("Installing bundle from " + f.getAbsolutePath());
		
		BundleContext context = FrameworkUtil.getBundle(OldschoolContextFactory.class).getBundleContext();
		try {
			JarInfo info = new JarInfo(f);
			SingleJarDownloader<ClassNode> dler = new SingleJarDownloader<ClassNode>(info);
			dler.download();
			
			Map<String, JarResource> resources = dler.getJarContents().getResourceContents().namedMap();
//			JarResource res = resources.get("manifest.json");
//			if(res == null) {
//				System.err.printf("  No manifest.json in %s!%n", f.getAbsolutePath());
//				return null;
//			}
//			
//			DefinedLibrary def = new Gson().fromJson(new String(res.getData()), DefinedLibrary.class);
//			if(def == null)
//				throw new NullPointerException();
			
			JarResource bundleRes = resources.get("META-INF/MANIFEST.MF");
			if(bundleRes != null) {
				System.out.printf("Attemping to load OSGi bundle for %s.%n", f.getAbsolutePath());
				try {
					Bundle bundle = context.installBundle(f.toURI().toString());
					System.out.println("Installed bundle: " + bundle + " " + bundle.getState());
					bundle.start();
					
					System.out.println("Started bundle: " + bundle + " " + bundle.getState());

					BundleWiring bundleWiring = bundle.adapt(BundleWiring.class);
					ClassLoader classLoader = bundleWiring.getClassLoader();
					return classLoader;
				} catch(BundleException e) {
					System.err.flush();
					System.out.flush();
					System.out.printf("  Error installing bundle.%n", f.getAbsolutePath());
					e.printStackTrace();
				}
			}
		} catch(IOException e) {
			System.err.flush();
			System.out.flush();
			System.err.printf("  Error loading api provder from %s.%n", f.getAbsolutePath());
			e.printStackTrace();
		}
		
		return null;
	}
	

	static class DefinedLibrary {
		String api_key;
	}

	@Override
	public ClassLoader act(FileSet runningJar, JarContents<ClassNode> contents, File baseDir) throws Exception {
		if(runningJar == FileSet.VANILLA)
			runningJar = FileSet.TRANSFORMED;
		else if(!runningJar.runnable())
			runningJar = FileSet.API;
		
		File f = runningJar.getAbsoluteLocation(baseDir);
		return installProvider(f);
	}

	@Override
	public Class<ClassLoader> type() {
		return null;
	}
}