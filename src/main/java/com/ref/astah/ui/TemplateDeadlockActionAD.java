package com.ref.astah.ui;

import javax.swing.JOptionPane;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.IActivityDiagram;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate;
import com.change_vision.jude.api.inf.ui.IWindow;
import com.ref.ActivityController;
import com.ref.ActivityController.VerificationType;
import com.ref.exceptions.FDRException;
import com.ref.exceptions.ParsingException;
import com.ref.exceptions.WellFormedException;
import com.ref.ui.CheckingProgressBar;

public class TemplateDeadlockActionAD implements IPluginActionDelegate {

	
	public Object run(IWindow window) {

		try {
			IDiagram diagram = AstahAPI.getAstahAPI().getViewManager().getDiagramViewManager().getCurrentDiagram();

			if (diagram instanceof IActivityDiagram) {
				CheckingProgressBar progressBar = new CheckingProgressBar();
				progressBar.setNewTitle("Checking deadlock");
				progressBar.setAssertion(0);

				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							ActivityController.getInstance().AstahInvocation(diagram, VerificationType.DEADLOCK,progressBar);
						}catch(ParsingException e) {
							JOptionPane.showMessageDialog( window.getParent(), e.getMessage(),"File Error", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						} catch(FDRException e) {
							JOptionPane.showMessageDialog(window.getParent(), e.getMessage(),"Verification Error", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						} catch (WellFormedException e) {
							JOptionPane.showMessageDialog( window.getParent(), e.getMessage(),"Well-formedness Error", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						} catch (Exception e) {
							JOptionPane.showMessageDialog( window.getParent(), "An error occurred during checking deadlock.","Checking Deadlock Error", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
					}
				}).start();
			}
			
		}  catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidUsingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

}
