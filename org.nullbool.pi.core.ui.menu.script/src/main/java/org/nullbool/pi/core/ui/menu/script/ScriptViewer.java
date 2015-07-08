package org.nullbool.pi.core.ui.menu.script;

import java.awt.Dimension;

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

/**
 * @author Bibl (don't ban me pls)
 * @created 26 Jun 2015 11:49:45
 */
public class ScriptViewer {

	private final JFrame window;
	
	private final JMenuBar menuBar;
	private final JTabbedPane tabbedPane;
	private final JMenu locationsMenu;
	private final JMenu runMenu;
	private final JMenu debugMenu;
	
	public ScriptViewer() {
		window = new JFrame("Script Viewer");
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		window.setPreferredSize(new Dimension(500, 300));

		menuBar = new JMenuBar();
		
		locationsMenu = new JMenu("Locations");
		
		JMenuItem addMenuItem = new JMenuItem("Add");
		JMenuItem modifyMenuItem = new JMenuItem("Modify");
		JMenuItem removeMenuItem = new JMenuItem("Remove");
		
		runMenu = new JMenu("Execution");

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
		
		runMenu.add(inspectMenuItem);
		runMenu.add(startMenuItem);
		runMenu.add(interuptMenuItem);
		runMenu.add(interuptTrueMenuItem);
		runMenu.add(interuptFalseMenuItem);
		runMenu.add(stopMenuItem);
		
		debugMenu = new JMenu("Debug");
		
		
		
		menuBar.add(runMenu);
		menuBar.add(debugMenu);
		
		
		window.setJMenuBar(menuBar);
	
		tabbedPane = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFocusable(false);
		
//		scriptsView = new ScriptsView();
//		tabbedPane.add("Scripts", scriptsView);
		
		window.getContentPane().add(tabbedPane);
	}
	
	private class TableView extends JPanel {
		private final DefaultTableModel model;
		private final JTable table;
		
		public TableView(String[] headers) {
			model = new DefaultTableModel(headers, 0);
			table = new JTable(model);
		}
	}
	private class ScriptsView extends TableView {

		public ScriptsView() {
			super(new String[]{"Name", "Version", "Description", "Authors", "Location"});
		}
	}
	
	private class LocationsView extends TableView {

		public LocationsView() {
			super(new String[]{"Path", "Remote"});
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