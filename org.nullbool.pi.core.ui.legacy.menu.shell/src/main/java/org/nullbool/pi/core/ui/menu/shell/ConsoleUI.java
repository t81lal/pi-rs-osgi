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

package org.nullbool.pi.core.ui.menu.shell;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.nullbool.osgi.shell.api.Shell;
import org.nullbool.osgi.shell.api.handlers.OSGiStopCommandHandler;
import org.osgi.framework.BundleContext;

/**
 * @author Bibl (don't ban me pls)
 * @created 15 Jun 2015 18:51:50
 */
public class ConsoleUI extends JDialog {
	private static final long serialVersionUID = 6679448206743302133L;

	private final Shell shell;
	private final JList<String> logList;
	private final JTextField inputField;
	private final PrintStream outStream;
	//private final InputStream is;
	
	public ConsoleUI(BundleContext context) {
		outStream = new PrintStream(new OutputStream() {
			StringBuilder builder = new StringBuilder();
			@Override
			public void write(int b) throws IOException {
				if(b == '\n') {
					((DefaultListModel<String>)logList.getModel()).addElement(builder.toString());
					builder.setLength(0);
				} else {
					builder.append((char)b);
				}
			}
		});
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(400, 300));
		setFocusable(false);
		setTitle("Shell");
		
		logList = new JList<String>(new DefaultListModel<String>());
		logList.setFocusable(false);
		
		inputField = new JTextField();
		inputField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = inputField.getText();
				outStream.println(text);
				shell.execute(text, null, outStream, outStream);
				inputField.setText("");
			}
		});
		inputField.setFocusable(true);
		
		
		// TODO: Configure input stream.
		/*is = new InputStream() {
			@Override
			public int read() throws IOException {
				return 0;
			}
		};*/
		
		JScrollPane scrollPane = new JScrollPane(logList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
		add(scrollPane, BorderLayout.CENTER);
		add(inputField, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		

		shell = new ShellImpl(context);
		outStream.println("Initialised shell (console).");
	}
	
	private static class ShellImpl extends Shell {

		public ShellImpl(BundleContext context) {
			super(context);
			
			registerHandler(new OSGiStopCommandHandler(), "exit", "quit");
		}

		/* (non-Javadoc)
		 * @see org.nullbool.osgi.shell.api.Shell#start()
		 */
		@Override
		public void start() {
			
		}

		/* (non-Javadoc)
		 * @see org.nullbool.osgi.shell.api.Shell#stop()
		 */
		@Override
		public void stop() {
		}
	}
}
