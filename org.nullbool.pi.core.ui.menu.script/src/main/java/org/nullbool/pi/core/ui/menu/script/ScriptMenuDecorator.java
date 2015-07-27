package org.nullbool.pi.core.ui.menu.script;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Set;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.engine.api.IContextRegistry;
import org.nullbool.pi.core.scripting.api.loader.DescribedManifestResourceLocation;
import org.nullbool.pi.core.scripting.api.loader.RefreshableResourcePool;
import org.nullbool.pi.core.scripting.api.loader.ResolvedDefinition;
import org.nullbool.pi.core.scripting.api.loader.RunnableResourceLocation;
import org.nullbool.pi.core.scripting.api.loader.finder.FinderStrategy;
import org.nullbool.pi.core.scripting.api.loader.finder.FixedFinderStrategy;
import org.nullbool.pi.core.scripting.api.loader.finder.JarInfoFolderSearchFinderStrategy;
import org.nullbool.pi.core.ui.api.IMenu;
import org.nullbool.pi.core.ui.api.IMenuDecorator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.topdank.byteengineer.commons.data.JarInfo;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 18:46:19
 */
public class ScriptMenuDecorator implements IMenuDecorator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.pi.core.ui.api.IMenuDecorator#decorate(org.nullbool.pi.core
	 * .ui.api.IMenu)
	 */
	@Override
	public void decorate(IMenu menu) {
		JMenu script = menu.findMenu("Script");
		if (script == null) {
			menu.registerMenu("Script", script = new JMenu("Script"));
		}

		JMenuItem locationsMenuItem = new JMenuItem("Locations");
		locationsMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String text = JOptionPane.showInputDialog(null, "", "Enter Location", JOptionPane.INFORMATION_MESSAGE);

					File file = null;
					if (text == null || text.isEmpty()) {
						error("Invalid input.");
						return;
					} else {
						file = new File(text);
						if (!file.exists()) {
							error("Location doesn't exist.");
							return;
						}
					}

					FinderStrategy<JarInfo> strategy = null;

					if (file.isDirectory()) {
						strategy = new JarInfoFolderSearchFinderStrategy(false, new File[] { file });
					} else if (file.isFile()) {
						strategy = new FixedFinderStrategy<JarInfo>(new JarInfo[] { new JarInfo(file) });
					} else {
						error("Wtf..., report thids. (file).");
						return;
					}

					BundleContext bundleContext = FrameworkUtil.getBundle(ScriptMenuDecorator.class).getBundleContext();
					ServiceReference<IContextRegistry> cxtSvcRef = bundleContext.getServiceReference(IContextRegistry.class);
					IContextRegistry contextRegistry = bundleContext.getService(cxtSvcRef);

					Set<IClientContext<IGameClient>> contexts = contextRegistry.retrieveAll();
					for (IClientContext<IGameClient> cxt : contexts) {
						cxt.scriptingEngine().getScriptPool().add(new DescribedManifestResourceLocation(strategy));
						cxt.scriptingEngine().refresh();
					}

					bundleContext.ungetService(cxtSvcRef);

					JOptionPane.showMessageDialog(null, "Added to " + contexts.size() + " context engines.", "Success.", JOptionPane.INFORMATION_MESSAGE);
				} catch (RuntimeException ex) {
					error("Error, check console: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		});

		script.add(locationsMenuItem);

		ScriptViewer viewer = new ScriptViewer();
		JMenuItem viewMenuItem = new JMenuItem("View");
		viewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				 //viewer.show();

				BundleContext bundleContext = FrameworkUtil.getBundle(ScriptMenuDecorator.class).getBundleContext();
				ServiceReference<IContextRegistry> cxtSvcRef = bundleContext.getServiceReference(IContextRegistry.class);
				IContextRegistry contextRegistry = bundleContext.getService(cxtSvcRef);

				Set<IClientContext<IGameClient>> contexts = contextRegistry.retrieveAll();
				for (IClientContext<IGameClient> cxt : contexts) {
					RefreshableResourcePool<ResolvedDefinition, RunnableResourceLocation<ResolvedDefinition>> pool = cxt.scriptingEngine().getScriptPool();
					cxt.scriptingEngine().startScript(pool.iterator().next().getValue().iterator().next());
				}

				bundleContext.ungetService(cxtSvcRef);
			}
		});

		script.add(viewMenuItem);
	}

	public void error(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
}