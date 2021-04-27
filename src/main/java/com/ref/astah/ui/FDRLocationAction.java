/*
 * This class is not used anymore
 * */

package com.ref.astah.ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.change_vision.jude.api.inf.ui.IPluginActionDelegate;
import com.change_vision.jude.api.inf.ui.IWindow;

public class FDRLocationAction implements IPluginActionDelegate {

	public Object run(IWindow window){

		try {
			FDR3LocationDialog dialog = new FDR3LocationDialog((JFrame) window.getParent(), true);			
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(window.getParent(), "Plugin Property file not found!","File Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog( window.getParent(), "Error opening plugin property file!","File Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return null;
	}


}
