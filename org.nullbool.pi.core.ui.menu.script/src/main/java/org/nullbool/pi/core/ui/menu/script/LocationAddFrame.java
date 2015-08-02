package org.nullbool.pi.core.ui.menu.script;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author Bibl (don't ban me pls)
 * @created 2 Aug 2015 02:20:42
 */
public class LocationAddFrame extends JFrame {

	private static final long serialVersionUID = 3684221089322012062L;

	private final JButton fileButton;
	private final JRadioButton fileRadioButton;
	private final JRadioButton dirRadioButton;
	private final ButtonGroup bg;
	
//	private final JRadioButton scriptRadioButton;
//	private final JRadioButton taskRadioButton;
//	private final ButtonGroup bg2;
	
	public LocationAddFrame() {
		super("Add Location");
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setPreferredSize(new Dimension(436, 150));
		
		JPanel cp = new JPanel();
		cp.setLayout(null);
		setContentPane(cp);
		
		fileButton = new JButton("Choose Location");
		fileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fc.showOpenDialog(LocationAddFrame.this);
				File f = fc.getSelectedFile();
				if(f != null) {
					fileButton.setText(f.getAbsolutePath());
					
					if(f.isDirectory()) {
						dirRadioButton.setSelected(true);
					} else {
						fileRadioButton.setSelected(true);
					}
				} else {
					// error("No file selected.");
				}
			}
		});
		fileButton.setFocusable(false);
		fileButton.setBounds(10, 10, 400, 25);
		cp.add(fileButton);

		fileRadioButton = new JRadioButton("File");
		fileRadioButton.setEnabled(false);
		fileRadioButton.setBounds(10, 40, 100, 20);
		add(fileRadioButton);
		
		dirRadioButton = new JRadioButton("Directory");
		dirRadioButton.setEnabled(false);
		dirRadioButton.setBounds(10, 60, 100, 20);
		add(dirRadioButton);
		
		bg = new ButtonGroup();
		bg.add(fileRadioButton);
		bg.add(dirRadioButton);
		
//		scriptRadioButton = new JRadioButton("Script");
//		taskRadioButton = new JRadioButton("");
		pack();
	}	
	
	@Override
	public void setVisible(boolean b) {
		if(b) {
			fileButton.setText("Choose Location");
		}
		super.setVisible(b);
	}
	
	public void error(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
//	public static void main(String[] args) {
//		LocationAddFrame frame = new LocationAddFrame();
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
//	}
}