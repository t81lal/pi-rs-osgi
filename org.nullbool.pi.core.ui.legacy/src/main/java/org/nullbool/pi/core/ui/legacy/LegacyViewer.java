package org.nullbool.pi.core.ui.legacy;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.osgi.util.ShutdownHelper;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.ui.api.IMenu;
import org.nullbool.pi.core.ui.api.IMenuDecorator;
import org.nullbool.pi.core.ui.api.IViewer;
import org.nullbool.taskqueue.TaskQueue;
import org.nullbool.taskqueue.TaskQueueImpl;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 11:33:31
 */
public class LegacyViewer implements IViewer {
	
	private final ImageIcon splashGif;
	private final BundleContext bundleContext;
	private TaskQueue eventQueue;
	private JFrame frame;
	private JLabel label;
	
	private IClientContext<IGameClient> context;
	
	public LegacyViewer(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
		
		// don't like this but bundlecontext.getentry isn't working.
		splashGif = new ImageIcon(getClass().getResource("/splash.gif"));
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#show(java.awt.Component)
	 */
	@Override
	public void show(Component c) {
		if(context == null)
			return;
		
		if(c.equals(context.getApplet())) {
			eventQueue.submit(new Runnable() {
				@Override
				public void run() {
					if(frame != null) {
						frame.getContentPane().add(c);
						
						if(label != null) {
							frame.getContentPane().remove(label);
							label = null;
						}
					}
				}
			});
		}
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#initComponents(org.osgi.framework.BundleContext)
	 */
	@Override
	public void initComponents(BundleContext context) {
		if(frame != null)
			throw new IllegalStateException();

		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		eventQueue = new TaskQueueImpl(){
			@Override
			public void runImpl(Runnable runnable) {
				SwingUtilities.invokeLater(runnable);
			}
		};
		
		eventQueue.start();
		eventQueue.submit(new Runnable() {
			@Override
			public void run() {
				frame = new JFrame("pi");
				frame.getContentPane().setBackground(Color.BLACK);
				
				GlobalMenuBar mb = new GlobalMenuBar(bundleContext);
				frame.setJMenuBar(mb);
				
				label = new JLabel(splashGif);
				frame.getContentPane().add(label);
				
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						ShutdownHelper.shutdown(FrameworkUtil.getBundle(Activator.class).getBundleContext());
					}
				});
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#display()
	 */
	@Override
	public void display() {
		eventQueue.submit(new Runnable() {
			@Override
			public void run() {
				if(frame != null) {
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#hide()
	 */
	@Override
	public void hide() {
		eventQueue.submit(new Runnable() {
			@Override
			public void run() {
				if(frame != null) {
					frame.setVisible(false);
				}
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#exit()
	 */
	@Override
	public void exit() {
		eventQueue.submit(new Runnable() {
			@Override
			public void run() {
				if(frame != null) {
					frame.dispose();
				}
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#getSize()
	 */
	@Override
	public Dimension getSize() {
		if(frame == null)
			throw new IllegalStateException();
		
		return frame.getSize();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#getActiveContext()
	 */
	@Override
	public IClientContext<IGameClient> getActiveContext() {
		return context;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#acceptContext(org.nullbool.pi.core.game.api.IClientContext)
	 */
	@Override
	public void acceptContext(IClientContext<IGameClient> context) throws UnsupportedOperationException {
		if(this.context != null)
			throw new UnsupportedOperationException();
		
		this.context = context;
	}
	
	private static final class GlobalMenuBar extends JMenuBar implements IMenu {
		private static final long serialVersionUID = -602536898937469576L;
		
		private final Map<String, JMenu> menus;
		
		public GlobalMenuBar(BundleContext context) {
			menus = new HashMap<String, JMenu>();
			
			JMenu fileMenu = new JMenu("File");
			registerMenu("File", fileMenu);
			
			JMenu developerMenu = new JMenu("Developer");
			registerMenu("Developer", developerMenu);
			
			try {
				// null filter matches all services.
				Collection<ServiceReference<IMenuDecorator>> decorators = context.getServiceReferences(IMenuDecorator.class, null);
				for(ServiceReference<IMenuDecorator> decSvcRef : decorators) {
					IMenuDecorator dec = context.getService(decSvcRef);
					dec.decorate(this);
					context.ungetService(decSvcRef);
				}
			} catch (InvalidSyntaxException e1) {
				e1.printStackTrace();
			}
			
			{
				JMenuItem exitMenuItem = new JMenuItem("Quit");
				exitMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ShutdownHelper.shutdown(context);
					}
				});
				fileMenu.add(exitMenuItem);
			}
		}

		/* (non-Javadoc)
		 * @see org.nullbool.pi.core.ui.api.IMenu#registerMenu(java.lang.String, javax.swing.JMenu)
		 */
		@Override
		public void registerMenu(String key, JMenu menu) {
			menus.put(key, menu);
			add(menu);
		}

		/* (non-Javadoc)
		 * @see org.nullbool.pi.core.ui.api.IMenu#findMenu(java.lang.String)
		 */
		@Override
		public JMenu findMenu(String key) {
			return menus.get(key);
		}
	}
}