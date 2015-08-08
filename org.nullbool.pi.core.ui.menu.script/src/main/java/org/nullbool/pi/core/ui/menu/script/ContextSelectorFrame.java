/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

package org.nullbool.pi.core.ui.menu.script;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.nullbool.pi.core.engine.api.IClientContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 8 Jul 2015 23:18:43
 */
public class ContextSelectorFrame extends JFrame {
	private static final long serialVersionUID = -7389619430606639579L;

	private final JPanel contentPane;
	private final DefaultTableModel model;
	private final JTable table;
	private SelectorCallback callback;

	public ContextSelectorFrame(SelectorCallback callback) {
		this.callback = callback;
		setResizable(false);
		setTitle("Context Selector");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setPreferredSize(new Dimension(650, 300));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDebug = new JMenu("Debug");
		menuBar.add(mnDebug);

		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repopulate();
			}
		});
		mnDebug.add(mntmRefresh);

		JMenuItem mntmSelectAll = new JMenuItem("Select All");
		mntmSelectAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rows = table.getRowCount();
				if(rows > 0) {
					table.setRowSelectionInterval(0, rows - 1);
				}
			}
		});
		mnDebug.add(mntmSelectAll);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(null);
		setContentPane(contentPane);

		model = new DefaultTableModel(new String[] { "Thread Group", "Context Instance" }, 0);
		contentPane.setLayout(new BorderLayout(0, 0));
		table = new JTable(model);
		contentPane.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("Ok");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int[] selected = table.getSelectedRows();
					if(selected != null) {
						IClientContext<?>[] contexts = new IClientContext<?>[selected.length];
						for(int i=0; i < selected.length; i++) {
							int row = table.convertRowIndexToModel(selected[i]);
							Object o = model.getValueAt(row, 1);
							if(o != null) {
								contexts[i] = (IClientContext<?>) o;
							} else {
								System.err.println("null context at row " + i + ", wtf...");
							}
						}
						ContextSelectorFrame.this.callback.complete(contexts, table.getRowCount() == selected.length);
					} else {
						error("No contexts selected?");
					}
				} finally {
					ContextSelectorFrame.this.callback = null;
					setVisible(false);
				}
			}
		});

		pack();
		setLocationRelativeTo(null);
	}	
	
	public void setCallback(SelectorCallback callback) {
		this.callback = callback;
	}
	
	public void error(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void repopulate() {
		table.clearSelection();
		int rows = model.getRowCount();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				model.removeRow(model.getRowCount() - 1);
			}
		}
		for (IClientContext<?> cxt : callback != null ? callback.getContexts() : Util.contexts()) {
			model.addRow(new Object[] { cxt.getThreadGroup(), cxt });
		}
	}
	
	@Override
	public void setVisible(boolean b) {
		if(b) {
			repopulate();
		}
		super.setVisible(b);
	}
	
	public static interface SelectorCallback {
		public void complete(IClientContext<?>[] contexts, boolean all);
		
		public default IClientContext<?>[] getContexts() {
			return Util.contexts();
		}
	}
}
