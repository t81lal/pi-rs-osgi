package org.nullbool.pi.core.ui.tabbedimpl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.nullbool.osgi.util.ShutdownHelper;
import org.nullbool.pi.core.bot.api.IBot;
import org.nullbool.pi.core.ui.api.IViewer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Bibl (don't ban me pls)
 * @created 14 Jun 2015 23:21:29
 */
public class TabbedBotViewer implements IViewer {

	private JFrame frame;
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#show(java.awt.Component)
	 */
	@Override
	public void show(Component c) {
		frame.getContentPane().add(c);
	}
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#initComponents()
	 */
	@Override
	public void initComponents(BundleContext context) {
		frame = new JFrame("pi Client");
		frame.setPreferredSize(new Dimension(800, 600));
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ShutdownHelper.shutdown(FrameworkUtil.getBundle(Activator.class).getBundleContext());
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#display()
	 */
	@Override
	public void display() {		
		if(frame != null) {
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#hide()
	 */
	@Override
	public void hide() {		
		if(frame != null) {
			frame.setVisible(false);
		}
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#exit()
	 */
	@Override
	public void exit() {
		if(frame != null) {
			frame.dispose();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#getActiveBot()
	 */
	@Override
	public IBot getActiveBot() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.ui.api.IViewer#getSize()
	 */
	@Override
	public Dimension getSize() {
		return frame.getSize();
	}
}