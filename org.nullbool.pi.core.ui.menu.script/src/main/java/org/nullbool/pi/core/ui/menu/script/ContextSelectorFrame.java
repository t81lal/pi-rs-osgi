package org.nullbool.pi.core.ui.menu.script;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.engine.api.IContextRegistry;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author Bibl (don't ban me pls)
 * @created 8 Jul 2015 23:18:43
 */
public class ContextSelectorFrame extends JFrame {
	private static final long serialVersionUID = -7389619430606639579L;

	private final JPanel contentPane;
	private final DefaultTableModel model;
	private final JTable table;

	public ContextSelectorFrame() {
		setResizable(false);
		setTitle("Context Selector");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setPreferredSize(new Dimension(450, 300));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDebug = new JMenu("Debug");
		menuBar.add(mnDebug);

		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mnDebug.add(mntmRefresh);

		JMenuItem mntmSelectAll = new JMenuItem("Select All");
		mnDebug.add(mntmSelectAll);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(null);
		setContentPane(contentPane);

		model = new DefaultTableModel(new String[] { "Thread Group", "Context Instance" }, 0);
		contentPane.setLayout(new BorderLayout(0, 0));
		table = new JTable(model);
		contentPane.add(table);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("Ok");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		pack();
		setLocationRelativeTo(null);
	}

	private void populate() {
		table.clearSelection();
		for (int i = model.getRowCount() - 1; i >= 0; i++) {
			model.removeRow(i);
		}
		for (IClientContext<?> cxt : contexts()) {
			model.addRow(new Object[] { cxt.threadGroup(), cxt });
		}
	}
	
//	private int eventType = -1;
//
//	public List<IClientContext<?>> get() {
//		eventType = -1;
//		
//		populate();
//		SwingUtilities.invokeAndWait(new Runnable() {
//			@Override
//			public void run() {
//				setVisible(true);
//			}
//		});
//		wait();
//		
//		if(eventType == -1) {
//			throw new 
//		}
//		
//		SwingUtilities.invokeAndWait(new Runnable() {
//			@Override
//			public void run() {
//				setVisible(false);
//			}
//		});
//		
//		return null;
//	}

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
}
