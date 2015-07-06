package org.nullbool.pi.core.engine.impl.factory;

import java.applet.Applet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.constants.IOHelper;
import org.nullbool.pi.constants.ResourceConstants;
import org.nullbool.pi.core.engine.api.IContextFactory;
import org.nullbool.pi.core.engine.api.IPageCrawler;
import org.nullbool.pi.core.engine.api.IVirtualGameBrowser;
import org.nullbool.pi.core.engine.impl.AppletClientContext;
import org.nullbool.pi.core.engine.impl.DefaultScriptingEngine;
import org.nullbool.pi.core.engine.impl.factory.ext.FileSet;
import org.nullbool.pi.core.engine.impl.factory.ext.IActor;
import org.nullbool.pi.core.scripting.api.klassmodel.HierarchalClassLoader;
import org.nullbool.pi.core.scripting.api.loader.BasicResourcePool;
import org.nullbool.topdank.eventbus.api.EventBus;
import org.objectweb.asm.tree.ClassNode;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.topdank.byteengineer.commons.data.JarInfo;
import org.topdank.byteengineer.commons.data.LocateableJarContents;
import org.topdank.byteio.in.MultiJarDownloader;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 00:24:52
 */
public class OldschoolContextFactory implements IContextFactory<AppletClientContext<IGameClient>> {
	
	private static final String[] POSSIBLE_LAUNCH_CLASSES = new String[]{"Client", "client"};
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.game.api.IContextFactory#create(org.nullbool.pi.core.game.api.IVirtualGameBrowser)
	 */
	@Override
	public AppletClientContext<IGameClient> create(IVirtualGameBrowser browser) throws Exception {

//		if(shared_cache == null) {
//			shared_cache = new LoadedLibraryCache();
//			System.out.println("Loading api providers.");
//			loadProviders0(ResourceConstants.API_PROVIDER_DIR, shared_cache);
//		}
		
		// FIXME: Revision checking
		int latestVer = 81;
		
		/* First we see if there is a straight load 
		 * and run jar (refactor, deob or transformed).*/
		File cacheDir = new File(ResourceConstants.CACHE_DIR, "/" + latestVer);
		/* Sorted by priority.*/
		List<FileSet> existing = FileSet.existing(cacheDir);

		AppletClientContext<IGameClient> context = null;
		
		for(FileSet fs : existing) {
			/* We take the first one which is 
			 * runnable and has resolved dependencies.*/
			System.out.println("fs: " + fs.relativeName() + " " + fs.runnable() + " " + fs.resolved(existing));
			if(fs.runnable() && fs.resolved(existing)) {
				File f = fs.getAbsoluteLocation(cacheDir);
				System.err.printf("Attempting to load %s.%n", f);
				context = run(fs, cacheDir);
				if(context != null) {
					return context;
				} else {
					System.err.printf("Error loading, continuing%s.%n", f);
				}
			}
		}
		
		if(!cacheDir.exists())
			cacheDir.mkdir();
		
		/* All installed stuff failed, so we download everything. */
		
		System.err.println("Falling back to default.");
		
		// TODO: Check hashes
		URL baseURL = ResourceConstants.baseURL(latestVer);
		for(FileSet fs : FileSet.values()) {
			if(!fs.runnable() && fs.priority() == -1) {
				File file = fs.getAbsoluteLocation(cacheDir);
				if(!file.exists() || (file.exists() && file.isDirectory()))
					IOHelper.download(fs.getRemoteLocation(baseURL), file);
			}
		}
		
		File arcFile = FileSet.VANILLA.getAbsoluteLocation(cacheDir);
		if(!arcFile.exists()) {
			IPageCrawler crawler = browser.getCrawler();
			URL archiveURL = new URL(browser.getCodeBase().toExternalForm() + crawler.getAppletParameters().get("archive"));
			
			System.out.println("Downloading archive from " + archiveURL + " to " + arcFile);
			IOHelper.download(archiveURL, arcFile);
		}

		return run(FileSet.VANILLA, cacheDir);
	}
	
	public AppletClientContext<IGameClient> run(FileSet fs, File dir) {
		System.out.println("Running from " + fs.relativeName());
		
		List<FileSet> fsets = new ArrayList<FileSet>();
		fsets.add(fs);
		
		List<Integer> reqs = fs.requires();
		List<JarInfo> jarInfos = new ArrayList<JarInfo>();
		
		jarInfos.add(new JarInfo(fs.getAbsoluteLocation(dir)));

		for(int id : reqs) {
			/* Dependencies have already been checked so
			 * we can just add them.*/
			FileSet fs2 = FileSet.byId(id);
			fsets.add(fs2);
			if(fs2.relativeName().endsWith(".jar"))
				jarInfos.add(new JarInfo(fs2.getAbsoluteLocation(dir)));
		}
		
		for(JarInfo ji : jarInfos) {
			System.out.println("Loading " + ji);
		}
		
		MultiJarDownloader<ClassNode> downloader = new MultiJarDownloader<ClassNode>(jarInfos.toArray(new JarInfo[0]));
		
		try {
			downloader.download();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.printf("Deleting fs #%d with dependencies %s.%n", fs.id(), reqs);
			clean(fsets, dir);
			
			return null;
		}
		
		LocateableJarContents<ClassNode> contents = downloader.getJarContents();
		
		Map<Class<?>, Object> executed = new HashMap<Class<?>, Object>();
		for(FileSet f : fsets) {
			IActor<?> actor = f.actor();
			if(actor != null) {
				try {
					executed.put(actor.type(), actor.act(fs, contents, dir));
				} catch (Exception e) {
					e.printStackTrace();
					System.err.printf("Actor #%d failed.%n", f.id());
					clean(fsets, dir);
					return null;
				}
			}
		}
		
		ClassNode launchClassNode = findLaunchableClass(contents.getClassContents().namedMap());
		
		if(launchClassNode == null) {
			System.err.println("No launch class...");
			clean(fsets, dir);
			return null;
		}
		
		// set classloader
		// TODO: verify
		
		HierarchalClassLoader classLoader = new HierarchalClassLoader(notNull((ClassLoader) executed.get(ClassLoader.class), getClass().getClassLoader()), contents);
		LookupFilter<String> filter = new LookupFilter<String>();
		for(ClassNode cn : contents.getClassContents()) {
			filter.add(cn.name.replace("/", "."));
		}
		classLoader.filter().connect(filter);
		
		Class<?> clientClass = null;
		try {
			clientClass = classLoader.loadClass(launchClassNode.name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Couldn't load launch class.");
			clean(fsets, dir);
			return null;
		}
		
		Applet gameApplet = null;
		try {
			gameApplet = (Applet) clientClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassCastException e) {
			e.printStackTrace();
			System.err.println("Couldn't start launch class.");
			clean(fsets, dir);
			return null;
		}
		
		BundleContext bundleContext = FrameworkUtil.getBundle(OldschoolContextFactory.class).getBundleContext();
		ServiceReference<EventBus> svcRef = bundleContext.getServiceReference(EventBus.class);
		EventBus bus = bundleContext.getService(svcRef);
		
		BasicResourcePool scriptpool = new BasicResourcePool();
		BasicResourcePool taskpool = new BasicResourcePool();
		DefaultScriptingEngine scriptingEngine = new DefaultScriptingEngine(scriptpool, taskpool);
		
		AppletClientContext<IGameClient> context = new AppletClientContext<IGameClient>(Thread.currentThread().getThreadGroup(), classLoader, bus, scriptingEngine, (IGameClient) gameApplet);
		scriptingEngine.initContext(context);
		
		return context;
	}
	
	private static <T> T notNull(T t1, T t2) {
		if(t1 == null)
			return t2;
		return t1;
	}
	
	private static void clean(List<FileSet> fsets, File dir) {
		for(FileSet fs : fsets) {
			fs.getAbsoluteLocation(dir).delete();
		}
	}
	
	public static ClassNode findLaunchableClass(Map<String, ClassNode> classes) {
		for(String lc : POSSIBLE_LAUNCH_CLASSES) {
			if(classes.containsKey(lc))
				return classes.get(lc);
		}
		
		return null;
	}
}