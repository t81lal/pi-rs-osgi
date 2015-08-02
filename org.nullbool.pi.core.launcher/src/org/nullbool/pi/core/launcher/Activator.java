package org.nullbool.pi.core.launcher;

import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.osgi.shell.api.Shell;
import org.nullbool.osgi.util.ShutdownHelper;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.engine.api.IContextFactory;
import org.nullbool.pi.core.engine.api.IContextRegistry;
import org.nullbool.pi.core.engine.api.IVirtualGameBrowser;
import org.nullbool.pi.core.engine.api.IVirtualGameBrowserFactory;
import org.nullbool.pi.core.ui.api.IViewer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 22:37:40
 */
public class Activator implements BundleActivator {

	private BundleContext context;
	private Thread thisThread;
	private ServiceReference<IViewer> viewerSvcRef;

	private ServiceReference<Shell> shellSrvRef;
	
	@Override
	public void start(BundleContext context) throws Exception {	
		this.context = context;
		launchStartThread();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if(shellSrvRef != null) {
			Shell shell = context.getService(shellSrvRef);
			context.ungetService(shellSrvRef);
			shell.stop();
		}
		
		if(viewerSvcRef != null) {
			IViewer viewer = context.getService(viewerSvcRef);
			context.ungetService(viewerSvcRef);
			viewer.exit();
		}
	}

	private void launchStartThread() {
		this.thisThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(context.getServiceReference(IViewer.class.getName()));
					viewerSvcRef = context.getServiceReference(IViewer.class);
					if (viewerSvcRef == null) {
						fatal("Headless bot!");
						return;
					}
					
					IViewer viewer = context.getService(viewerSvcRef);
					context.ungetService(viewerSvcRef);
					createView(viewer);
					
					ServiceReference<IVirtualGameBrowserFactory> gbfSvcRef = context.getServiceReference(IVirtualGameBrowserFactory.class);
					System.out.println(gbfSvcRef);
					IVirtualGameBrowserFactory gbf = context.getService(gbfSvcRef);
					IVirtualGameBrowser browser = gbf.create(new URL("http://oldschool84.runescape.com/"));
					
					ServiceReference<IContextFactory> cfSvcRef = context.getServiceReference(IContextFactory.class);
					IContextFactory<IClientContext<IGameClient>> cf = context.getService(cfSvcRef);
					
					new Thread(){
						@Override
						public void run() {
							try {
								IClientContext<IGameClient> clientContext = cf.create(browser);
								
								// add it to the registry
								ServiceReference<IContextRegistry> cxtSvcRef = context.getServiceReference(IContextRegistry.class);
								IContextRegistry contextRegistry = context.getService(cxtSvcRef);
								contextRegistry.register(clientContext);
								context.ungetService(cxtSvcRef);
								cacheContext(clientContext);
								
								browser.setApplet(clientContext.getApplet());
								clientContext.init();
								viewer.acceptContext(clientContext);
								viewer.show(clientContext.getApplet());
								
							} catch(Throwable t) {
								t.printStackTrace();
								fatal(t.getClass().getSimpleName());
							} finally {
								// release services
								context.ungetService(gbfSvcRef);
								context.ungetService(cfSvcRef);
							}
						}
					}.start();
				} catch(Throwable t) {
					t.printStackTrace();
					fatal(String.format("Fatal error: ", t.getMessage()));
				}
			}
		});

		this.thisThread.start();
	}

	private void cacheContext(IClientContext<IGameClient> c) {
		ServiceReference<IContextRegistry> cxtRefSvcRef = context.getServiceReference(IContextRegistry.class);
		IContextRegistry registry = context.getService(cxtRefSvcRef);
		System.out.println(registry);
		registry.register(c);
		context.ungetService(cxtRefSvcRef);
	}
	
	private void createView(IViewer viewer) throws Throwable {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				viewer.initComponents(context);
				viewer.display();
			}
		});
	}

	// TODO: Logger
	public void fatal(String msg) {
		System.err.printf("(Fatal): %s.%n", msg);
		
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
		
		if(shellSrvRef == null) {
			shellSrvRef = context.getServiceReference(Shell.class);
			if(shellSrvRef != null) {
				Shell shell = context.getService(shellSrvRef);
				shell.start();
				return;
			}
		}

		ShutdownHelper.shutdown(context);
	}
}