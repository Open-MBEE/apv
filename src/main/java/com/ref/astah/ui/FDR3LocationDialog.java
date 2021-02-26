package com.ref.astah.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.ref.ActivityController;
import com.ref.exceptions.FDRException;

public class FDR3LocationDialog extends JDialog {

	private JTextField tf;
	private JButton findButton;
	private JButton applyButton;
	private JFileChooser fc;
	private JLabel msg;

	public FDR3LocationDialog(JFrame frame, boolean modal)
			throws IOException, ClassNotFoundException {
		super(frame, modal);
		initComponents();
		this.setTitle("FDR Location");
		this.setLocation(new Point(276, 182));
		this.setSize(new Dimension(450, 150));
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);

	}

	private void initComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;

		add(new JLabel("FDR folder:"), gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 100;

		tf = new JTextField();

		try {
			tf.setText(ActivityController.getInstance().getFDRLocation());
		} catch (IOException e1) {
			msg.setText("Error: could not retrieve FDR Location from property file.");
		}
		tf.setSize(300, tf.getHeight());
		add(tf, gbc);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx++;
		gbc.weightx = 0;
		findButton = new JButton("Find");
		findButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					fc = new JFileChooser();
					if (System.getProperty("os.name").startsWith("Mac OS X")) {
						fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					} else {
						fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					}
					int returnVal = fc.showDialog(findButton.getParent(), "Select");
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						tf.setText(fc.getSelectedFile().getAbsolutePath());
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		add(findButton, gbc);
		gbc.gridx = 2;
		gbc.gridy++;
		applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tf.getText().equals("")) {
					msg.setText("Please select a valid folder!");
				} else {
					
					try {
						ActivityController.getInstance().setFDRLocation(tf.getText());
						FDR3LocationDialog.this.setVisible(false);
						FDR3LocationDialog.this
								.dispatchEvent(new WindowEvent(FDR3LocationDialog.this, WindowEvent.WINDOW_CLOSING));
					} catch (FDRException ex) {
						msg.setText("Error: " + ex.getMessage());
					} catch (IOException e1) {
						msg.setText("Error: " + e1.getMessage());
					}
					
					

				}

			}
		});
		add(applyButton, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.weightx = 3;
		msg = new JLabel();
		msg.setForeground(Color.RED);
		add(msg, gbc);
	}

}
