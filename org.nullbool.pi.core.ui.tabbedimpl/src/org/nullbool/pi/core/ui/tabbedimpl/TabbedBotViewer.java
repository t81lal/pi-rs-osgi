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

//package org.nullbool.pi.core.ui.tabbedimpl;
//
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.JFrame;
//
//import org.nullbool.osgi.util.ShutdownHelper;
//import org.nullbool.pi.core.bot.api.IBot;
//import org.nullbool.pi.core.ui.api.IClientContext;
//import org.nullbool.pi.core.ui.api.IGameClient;
//import org.nullbool.pi.core.ui.api.IViewer;
//import org.osgi.framework.BundleContext;
//import org.osgi.framework.FrameworkUtil;
//
///**
// * @author Bibl (don't ban me pls)
// * @created 14 Jun 2015 23:21:29
// */
//public class TabbedBotViewer implements IViewer {
//
//	private JFrame frame;
//	
//	/* (non-Javadoc)
//	 * @see org.nullbool.pi.core.ui.api.IViewer#show(java.awt.Component)
//	 */
//	@Override
//	public void show(Component c) {
//		frame.getContentPane().add(c);
//	}
//	
//	/* (non-Javadoc)
//	 * @see org.nullbool.pi.core.ui.api.IViewer#initComponents()
//	 */
//	@Override
//	public void initComponents(BundleContext context) {
//		frame = new JFrame("pi Client");
//		frame.setPreferredSize(new Dimension(800, 600));
//		
//		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		frame.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				ShutdownHelper.shutdown(FrameworkUtil.getBundle(Activator.class).getBundleContext());
//			}
//		});
//	}
//
//	/* (non-Javadoc)
//	 * @see org.nullbool.pi.core.ui.api.IViewer#display()
//	 */
//	@Override
//	public void display() {		
//		if(frame != null) {
//			frame.pack();
//			frame.setLocationRelativeTo(null);
//			frame.setVisible(true);
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see org.nullbool.pi.core.ui.api.IViewer#hide()
//	 */
//	@Override
//	public void hide() {		
//		if(frame != null) {
//			frame.setVisible(false);
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see org.nullbool.pi.core.ui.api.IViewer#exit()
//	 */
//	@Override
//	public void exit() {
//		if(frame != null) {
//			frame.dispose();
//		}
//	}
//	
////	/* (non-Javadoc)
////	 * @see org.nullbool.pi.core.ui.api.IViewer#getActiveBot()
////	 */
////	@Override
////	public IBot getActiveBot() {
////		return null;
////	}
//
//	/* (non-Javadoc)
//	 * @see org.nullbool.pi.core.ui.api.IViewer#getSize()
//	 */
//	@Override
//	public Dimension getSize() {
//		return frame.getSize();
//	}
//
////	/* (non-Javadoc)
////	 * @see org.nullbool.pi.core.ui.api.IViewer#acceptContext(org.nullbool.pi.core.ui.api.IClientContext)
////	 */
////	@Override
////	public void acceptContext(IClientContext<IGameClient> context) throws UnsupportedOperationException {
////		// TODO Auto-generated method stub
////		
////	}
////
////	/* (non-Javadoc)
////	 * @see org.nullbool.pi.core.ui.api.IViewer#getActiveContext()
////	 */
////	@Override
////	public IClientContext<IGameClient> getActiveContext() {
////		// TODO Auto-generated method stub
////		return null;
////	}
//}
