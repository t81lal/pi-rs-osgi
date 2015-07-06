package org.nullbool.pi.core.ui.menu.script;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
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
import javax.swing.table.DefaultTableModel;

import org.nullbool.pi.core.scripting.api.loader.ExternalResourceDefinition;
import org.nullbool.pi.core.scripting.api.loader.RefreshableResourcePool;
import org.nullbool.pi.core.scripting.api.loader.ResolvedDefinition;
import org.nullbool.pi.core.scripting.api.loader.RunnableResourceLocation;

/**
 * @author Bibl (don't ban me pls)
 * @created 26 Jun 2015 11:49:45
 */
public class ScriptViewer {

	private static final String INTERUPT_ACTION_COMMAND = "interupt";
	private static final String INTERUPT_TRUE_ACTION_COMMAND = "interupt-true";
	private static final String INTERUPT_FALSE_ACTION_COMMAND = "interupt-false";
	
	private final JFrame window;
	
	private final JMenuBar menuBar;
	private final JMenu runMenu;
	private final JMenu debugMenu;
	
	private final JTabbedPane tabbedPane;
	private final ScriptsView scriptsView;
	
	public ScriptViewer() {
		window = new JFrame("Script Viewer");
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		window.setPreferredSize(new Dimension(500, 300));

		menuBar = new JMenuBar();
		
		
		runMenu = new JMenu("Run");
		JMenuItem addMenuItem = new JMenuItem("Add");
		addMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				error("Not implemented yet.");
			}
		});
		JMenuItem removeMenuItem = new JMenuItem("Remove");
		removeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				error("Not implemented yet.");
			}
		});
		ActionListener forwardEventActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = tabbedPane.getSelectedIndex();
				if(selected == -1) {
					error("No tab selected... wtf.");
					return;
				}
				
				Component c = tabbedPane.getComponentAt(selected);
				if(!(c instanceof ActionListener)) {
					error("Current tab component is not an action listener? wtf.");
					return;
				}
				
				ActionListener al = (ActionListener) c;
				al.actionPerformed(e);
			}
		};
		
		JMenuItem interuptMenuItem = new JMenuItem("Interupt");
		JMenuItem interuptTrueMenuItem = new JMenuItem("Interupt(true)");
		JMenuItem interuptFalseMenuItem = new JMenuItem("Interupt(false)");

		interuptMenuItem.setActionCommand(INTERUPT_ACTION_COMMAND);
		interuptTrueMenuItem.setActionCommand(INTERUPT_TRUE_ACTION_COMMAND);
		interuptFalseMenuItem.setActionCommand(INTERUPT_FALSE_ACTION_COMMAND);
		
		interuptMenuItem.addActionListener(forwardEventActionListener);
		interuptTrueMenuItem.addActionListener(forwardEventActionListener);
		interuptFalseMenuItem.addActionListener(forwardEventActionListener);
		
		runMenu.add(interuptMenuItem);
		runMenu.add(interuptTrueMenuItem);
		runMenu.add(interuptFalseMenuItem);
		
		debugMenu = new JMenu("Debug");
		
		menuBar.add(runMenu);
		menuBar.add(debugMenu);
		
		
		window.setJMenuBar(menuBar);
	
		tabbedPane = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFocusable(false);
		
		scriptsView = new ScriptsView();
		tabbedPane.add("Scripts", scriptsView);
		
		window.getContentPane().add(tabbedPane);
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
	
	private class ScriptsView extends JPanel implements ActionListener {
		
		private final List<ResolvedDefinition> definitions;
		private final DefaultTableModel model;
		private final JTable table;
		
		public ScriptsView() {
			definitions = new ArrayList<ResolvedDefinition>();
			model = new DefaultTableModel(new String[]{"Name", "Desc", "Authors", "Version"}, 0);
			table = new JTable(model);
			
			add(table);
		}

		// TODO:
		public void populate(RefreshableResourcePool<ResolvedDefinition, RunnableResourceLocation<ResolvedDefinition>> pool) {
			definitions.clear();
			
			for(Entry<RunnableResourceLocation<ResolvedDefinition>, Set<ResolvedDefinition>> e : pool) {
				for(ResolvedDefinition _def : e.getValue()) {
					ExternalResourceDefinition def = _def.getDefinition();
					model.addRow(new String[]{def.getName(), def.getDescription(), Arrays.toString(def.getAuthors()), def.getVersion()});
					definitions.add(_def);
				}
			}
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int selected = table.getSelectedRow();
			int selectedCol = table.getSelectedColumn();
			if(selected == -1 || selectedCol == -1) {
				error("No table item selected!");
				return;
			}
			
			if(selected > definitions.size()) {
				error(selected + " > " + definitions.size() + " wtf..");
				return;
			}
			
			ResolvedDefinition def = definitions.get(selected);
			if(def == null) {
				error("Null def in col " + selected);
				return;
			}
			
		}
	}
}