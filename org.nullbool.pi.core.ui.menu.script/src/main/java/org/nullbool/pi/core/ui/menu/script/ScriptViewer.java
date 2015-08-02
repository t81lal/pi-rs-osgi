package org.nullbool.pi.core.ui.menu.script;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.loader.DescribedManifestResourceLocation;
import org.nullbool.pi.core.scripting.api.loader.ExternalResourceDefinition;
import org.nullbool.pi.core.scripting.api.loader.IScriptingPoolModel;
import org.nullbool.pi.core.scripting.api.loader.ResolvedDefinition;
import org.nullbool.pi.core.scripting.api.loader.ResourceType;
import org.nullbool.pi.core.scripting.api.loader.finder.FinderStrategy;
import org.nullbool.pi.core.scripting.api.loader.finder.FixedFinderStrategy;
import org.nullbool.pi.core.scripting.api.loader.finder.JarInfoFolderSearchFinderStrategy;
import org.nullbool.pi.core.ui.menu.script.ContextSelectorFrame.SelectorCallback;
import org.nullbool.pi.core.ui.menu.script.TypeSelectorFrame.TypeCallback;
import org.topdank.byteengineer.commons.data.JarInfo;

/**
 * @author Bibl (don't ban me pls)
 * @created 26 Jun 2015 11:49:45
 */
public class ScriptViewer {

	private final JFrame window;

	private final JMenuBar menuBar;
	private final JTabbedPane tabbedPane;
	private final JMenu debugMenu;

	private final ScriptsView scriptsView;
	private final LocationsView locationsView;

	public ScriptViewer() {
		window = new JFrame("Script Viewer") {
			private static final long serialVersionUID = 2771814124017555786L;

			@Override
			public void setVisible(boolean b) {
				if (b) {
					scriptsView.reload();
				}
				super.setVisible(b);
			}
		};
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		window.setPreferredSize(new Dimension(500, 300));

		menuBar = new JMenuBar();

		debugMenu = new JMenu("Debug");

		JMenuItem reloadMenuItem = new JMenuItem("Reload");
		reloadMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scriptsView.reload();
			}
		});

		// menuBar.add(runMenu);
		debugMenu.add(reloadMenuItem);
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

	private abstract class TableView extends JPanel implements ChangeListener {
		private static final long serialVersionUID = -6290752993048084438L;
		protected final DefaultTableModel model;
		protected final JTable table;

		public TableView(String[] headers) {
			setLayout(new BorderLayout());
			model = new DefaultTableModel(headers, 0);
			table = new JTable(model);

			add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		}
	}

	private class ScriptsView extends TableView {
		private static final long serialVersionUID = -8401803159915227029L;
		private final JMenu runMenu;
		private final ContextSelectorFrame selector;

		public ScriptsView() {
			super(new String[] { "Name", "Version", "Description", "Authors", "Location" });

			selector = new ContextSelectorFrame(null);

			JMenuItem inspectMenuItem = new JMenuItem("Inspect");
			JMenuItem startMenuItem = new JMenuItem("Start");
			JMenuItem interuptMenuItem = new JMenuItem("Interupt");
			JMenuItem interuptTrueMenuItem = new JMenuItem("Interupt(true)");
			JMenuItem interuptFalseMenuItem = new JMenuItem("Interupt(false)");
			JMenuItem stopMenuItem = new JMenuItem("Stop");

			inspectMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					error("unimplemented functionality.");
				}
			});

			startMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.convertRowIndexToModel(table.getSelectedRow());
					if (row == -1) {
						error("No item selected.");
					} else {
						ResolvedDefinition def = (ResolvedDefinition) model.getValueAt(row, 0);
						selector.setCallback(new SelectorCallback() {
							@Override
							public void complete(IClientContext<?>[] contexts, boolean all) {
								if (contexts != null) {
									for (IClientContext<?> cxt : contexts) {
										IScriptingEngine engine = cxt.getScriptingEngine();
										switch (def.getType()) {
											case SCRIPT:
												engine.startScript(def);
												break;
											case TASK:
												engine.startTask(def);
												break;
											default:
												throw new RuntimeException();
										}
									}
								}
							}

							@Override
							public IClientContext<?>[] getContexts() {
								List<IClientContext<?>> list = new ArrayList<IClientContext<?>>();
								for (IClientContext<?> c : Util.contexts()) {
									IScriptingEngine engine = c.getScriptingEngine();
									if (engine != null) {
										if (engine.getActiveScript() == null) {
											list.add(c);
										}
									}
								}
								return list.toArray(new IClientContext[list.size()]);
							}
						});
						
						selector.setVisible(true);
					}
				}
			});

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

		public void reload() {
			table.clearSelection();
			int rows = model.getRowCount();
			if (rows > 0) {
				for (int i = rows - 1; i >= 0; i++) {
					model.removeRow(i);
				}
			}

			IScriptingPoolModel model = Util.model();
			Collection<ResolvedDefinition> scripts = model.getPersistentScriptPool().pool();
			addAll(scripts);
			Collection<ResolvedDefinition> tasks = model.getPersistentTaskPool().pool();
			addAll(tasks);
		}

		public void addAll(Collection<ResolvedDefinition> defs) {
			for (ResolvedDefinition _def : defs) {
				try {
					ExternalResourceDefinition def = _def.getDefinition();
					model.addRow(new Object[] { _def, def.getVersion(), def.getDescription(), Arrays.toString(def.getAuthors()),
							_def.getJarInfo().getPath() });
				} catch (Throwable t) {
					System.err.println("Error updating scripts table model.");
					t.printStackTrace();
				}
			}
		}

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
		private final JMenu locationsMenu;
		private final JMenuItem addMenuItem;
		private final JMenuItem modifyMenuItem;
		private final JMenuItem removeMenuItem;
		private final TypeSelectorFrame selector;

		public LocationsView() {
			super(new String[] { "Path", "Type" });
			
			selector = new TypeSelectorFrame();

			locationsMenu = new JMenu("Locations");
			addMenuItem = new JMenuItem("Add");
			modifyMenuItem = new JMenuItem("Modify");
			removeMenuItem = new JMenuItem("Remove");

			addMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String path = attemptAdd();
						if (path != null) {
							selector.setCallback(new TypeCallback() {
								@Override
								public void chose(ResourceType t) {
									if(t != null) {
										File f = new File(path);
										FinderStrategy<JarInfo> finder;
										if(f.isDirectory()) {
											finder = new JarInfoFolderSearchFinderStrategy(false, f);
										} else {
											finder = new FixedFinderStrategy<JarInfo>(new JarInfo(f));
										}
										
										DescribedManifestResourceLocation loc = new DescribedManifestResourceLocation(finder);
										IScriptingPoolModel poolModel = Util.model();
										
										if(t == ResourceType.SCRIPT) {
											poolModel.getPersistentScriptPool().add(loc);
										} else if(t == ResourceType.TASK) {
											poolModel.getPersistentTaskPool().add(loc);
										} else {
											throw new IllegalStateException();
										}
										
										model.addRow(new Object[] { path, t.name() });
									} else {
										error("Null type.");
									}
								}
							});
							selector.setVisible(true);
						}
					} catch(Throwable t) {
						t.printStackTrace();
						error("Error");
					}
				}
			});

			modifyMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.convertRowIndexToModel(table.getSelectedRow());
					if (row == -1) {
						error("No item selected.");
					} else {
						String path = attemptAdd();
						if (path != null) {
							model.setValueAt(path, row, 0);
						}
					}
				}
			});

			removeMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.convertRowIndexToModel(table.getSelectedRow());
					if (row == -1) {
						error("No item selected.");
					} else {
						if (confirm("delete this entry")) {
							model.removeRow(row);
						}
					}
				}
			});
			
			locationsMenu.add(addMenuItem);
			locationsMenu.add(modifyMenuItem);
			locationsMenu.add(removeMenuItem);

			menuBar.add(locationsMenu);
			locationsMenu.setEnabled(tabbedPane.getSelectedComponent() == this);
		}

		protected String attemptAdd() {
			String loc = ask("Location?");
			if (loc != null) {
				File file = new File(loc);
				if (file.exists()) {
					String path = file.getAbsolutePath();
					if (!containsPath(path)) {
						return path;
					} else {
						error("Path already exists.");
					}
				} else {
					error("Path doesn't exist.");
				}
			} else {
				error("Null location.");
			}

			return null;
		}

		protected boolean containsPath(String path) {
			for (int i = 0; i < model.getRowCount(); i++) {
				Object o = model.getValueAt(i, 0);
				if (o != null) {
					String s = o.toString();
					if (s.equalsIgnoreCase(path))
						return true;
				} else {
					System.err.println("null item in table at " + i);
				}
			}
			return false;
		}

		String ask(String msg) {
			return JOptionPane.showInputDialog(this, msg, "", JOptionPane.QUESTION_MESSAGE);
		}

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

	private boolean confirm(String msg) {
		int res = JOptionPane.showConfirmDialog(window, "Are you sure you want to " + msg + "?", "Confirm", JOptionPane.YES_NO_OPTION);
		return res == JOptionPane.YES_OPTION;
	}

	public void error(String msg) {
		JOptionPane.showMessageDialog(window, msg, "Error", JOptionPane.ERROR_MESSAGE);
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