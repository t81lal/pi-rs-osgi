package org.nullbool.pi.core.ui.menu.script;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.engine.api.IContextRegistry;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author Bibl (don't ban me pls)
 * @created 26 Jun 2015 11:49:45
 */
public class ScriptViewer {

	private final JFrame window;

	private final JMenuBar menuBar;
	private final JTabbedPane tabbedPane;
	private final JMenu debugMenu;

	private ScriptsView scriptsView;
	private LocationsView locationsView;

	public ScriptViewer() {
		window = new JFrame("Script Viewer");
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		window.setPreferredSize(new Dimension(500, 300));

		menuBar = new JMenuBar();

		debugMenu = new JMenu("Debug");

		// menuBar.add(runMenu);
		menuBar.add(debugMenu);

		window.setJMenuBar(menuBar);

		tabbedPane = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFocusable(false);

		scriptsView = new ScriptsView();
		tabbedPane.add("Scripts", scriptsView);
		tabbedPane.addChangeListener(scriptsView);

		locationsView = new LocationsView();
		tabbedPane.add("Locations", locationsView);
		tabbedPane.addChangeListener(locationsView);

		window.getContentPane().add(tabbedPane);
	}

	public IClientContext<?>[] contexts() {
		BundleContext bundleContext = FrameworkUtil.getBundle(ScriptMenuDecorator.class).getBundleContext();
		ServiceReference<IContextRegistry> cxtSvcRef = bundleContext.getServiceReference(IContextRegistry.class);
		IContextRegistry contextRegistry = bundleContext.getService(cxtSvcRef);

		Set<IClientContext<IGameClient>> contexts = contextRegistry.retrieveAll();
		IClientContext<?>[] engines = new IClientContext[contexts.size()];
		Iterator<IClientContext<IGameClient>> it = contexts.iterator();

		int i = 0;
		while (it.hasNext()) {
			engines[i++] = it.next();
		}

		bundleContext.ungetService(cxtSvcRef);

		return engines;
	}

	private abstract class TableView extends JPanel implements ChangeListener {
		private static final long serialVersionUID = -6290752993048084438L;
		protected final DefaultTableModel model;
		protected final JTable table;

		public TableView(String[] headers) {
			model = new DefaultTableModel(headers, 0);
			table = new JTable(model);
		}
	}

	private class ScriptsView extends TableView {
		private static final long serialVersionUID = -8401803159915227029L;
		private final JMenu runMenu;

		public ScriptsView() {
			super(new String[] { "Name", "Version", "Description", "Authors", "Location" });

			JMenuItem inspectMenuItem = new JMenuItem("Inspect");
			JMenuItem startMenuItem = new JMenuItem("Start");
			JMenuItem interuptMenuItem = new JMenuItem("Interupt");
			JMenuItem interuptTrueMenuItem = new JMenuItem("Interupt(true)");
			JMenuItem interuptFalseMenuItem = new JMenuItem("Interupt(false)");
			JMenuItem stopMenuItem = new JMenuItem("Stop");

			inspectMenuItem.setActionCommand("inspect");
			startMenuItem.setActionCommand("start");
			interuptMenuItem.setActionCommand("interupt");
			interuptTrueMenuItem.setActionCommand("interupt(true)");
			interuptFalseMenuItem.setActionCommand("interupt(false)");
			stopMenuItem.setActionCommand("stop");

			runMenu = new JMenu("Execution");
			runMenu.add(inspectMenuItem);
			runMenu.add(startMenuItem);
			runMenu.add(interuptMenuItem);
			runMenu.add(interuptTrueMenuItem);
			runMenu.add(interuptFalseMenuItem);
			runMenu.add(stopMenuItem);

			menuBar.add(runMenu);
			runMenu.setEnabled(true);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.
		 * ChangeEvent)
		 */
		@Override
		public void stateChanged(ChangeEvent e) {
			Component c = tabbedPane.getSelectedComponent();
			if (this == c) {
				runMenu.setEnabled(true);
			} else {
				runMenu.setEnabled(false);
			}
		}
	}

	private class LocationsView extends TableView {
		private static final long serialVersionUID = -6733216332953420715L;
		private JMenu locationsMenu;
		private JMenuItem addMenuItem;
		private JMenuItem modifyMenuItem;
		private JMenuItem removeMenuItem;
		private ContextSelectorFrame selector;

		public LocationsView() {
			super(new String[] { "Path", "Remote" });
			
			selector = new ContextSelectorFrame();
			
			locationsMenu = new JMenu("Locations");
			addMenuItem = new JMenuItem("Add");
			modifyMenuItem = new JMenuItem("Modify");
			removeMenuItem = new JMenuItem("Remove");

			addMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String loc = ask("Location?");
					if (loc != null) {
						File file = new File(loc);
						if (file.exists()) {
							selector.setVisible(true);
						} else {
							error("Path doesn't exist.");
						}
					} else {
						error("Null location.");
					}
				}
			});

			locationsMenu.add(addMenuItem);
			locationsMenu.add(modifyMenuItem);
			locationsMenu.add(removeMenuItem);

			menuBar.add(locationsMenu);
			locationsMenu.setEnabled(tabbedPane.getSelectedComponent() == this);
		}

		String ask(String msg) {
			return JOptionPane.showInputDialog(this, msg, "", JOptionPane.QUESTION_MESSAGE);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.
		 * ChangeEvent)
		 */
		@Override
		public void stateChanged(ChangeEvent e) {
			Component c = tabbedPane.getSelectedComponent();
			if (this == c) {
				locationsMenu.setEnabled(true);
			} else {
				locationsMenu.setEnabled(false);
			}
		}
	}

	public void error(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void show() {
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	private JButton makeButton(String name) {
		ImageIcon icon = new ImageIcon(ScriptViewer.class.getResource("/icons/" + name));
		JButton button = new JButton(icon);
		button.setFocusable(false);
		return button;
	}
}