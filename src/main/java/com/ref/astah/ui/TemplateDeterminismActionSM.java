package com.ref.astah.ui;

import javax.swing.JOptionPane;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.IStateMachineDiagram;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate;
import com.change_vision.jude.api.inf.ui.IWindow;
import com.ref.StateMachineController;
import com.ref.StateMachineController.VerificationType;
import com.ref.exceptions.FDRException;
import com.ref.exceptions.ParsingException;
import com.ref.exceptions.WellFormedException;
import com.ref.ui.CheckingProgressBar;

public class TemplateDeterminismActionSM implements IPluginActionDelegate{
	public Object run(IWindow window) {

		try {
			IDiagram diagram = AstahAPI.getAstahAPI().getViewManager().getDiagramViewManager().getCurrentDiagram();

			if (diagram instanceof IStateMachineDiagram) {
				CheckingProgressBar progressBar = new CheckingProgressBar();
				progressBar.setNewTitle("Checking Non-determinism");
				progressBar.setAssertion(0);

				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							StateMachineController.getInstance().AstahInvocation(diagram, VerificationType.DETERMINISM,progressBar);
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
							JOptionPane.showMessageDialog( window.getParent(), "An error occurred during checking determinism.","Checking Deadlock Error", JOptionPane.ERROR_MESSAGE);
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
