package com.ref.astah.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.StateMachineDiagramEditor;
import com.change_vision.jude.api.inf.editor.TransactionManager;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.exception.LicenseNotFoundException;
import com.change_vision.jude.api.inf.exception.ProjectLockedException;
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.IElement;
import com.change_vision.jude.api.inf.model.IModel;
import com.change_vision.jude.api.inf.model.IStateMachineDiagram;
import com.change_vision.jude.api.inf.presentation.ILinkPresentation;
import com.change_vision.jude.api.inf.presentation.INodePresentation;
import com.change_vision.jude.api.inf.presentation.PresentationPropertyConstants;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.project.ProjectAccessorFactory;
import com.change_vision.jude.api.inf.project.ProjectEvent;
import com.change_vision.jude.api.inf.project.ProjectEventListener;
import com.change_vision.jude.api.inf.ui.IPluginExtraTabView;
import com.change_vision.jude.api.inf.ui.ISelectionListener;
import com.ref.StateMachineController;
import com.ref.StateMachineController.VerificationType;
import com.ref.astah.traceability.CounterExampleAstah;
import com.ref.parser.stateMachine.SMUtils;
import com.ref.ui.CheckingProgressBar;

import java.util.List;

public class CounterView extends JPanel implements IPluginExtraTabView, ProjectEventListener {

	
	ProjectAccessor projectAccessor;
	private boolean isFirstTrans = true;
	private static int index = 0;
	private static int lastIndexColor = 0;
	private static IStateMachineDiagram smd;
	private static List<String> trace;
	private static HashMap<String, INodePresentation> nodeAdded;
    private static HashMap<String, ILinkPresentation> transitionAdded;
    private static HashMap<String, String> typeAdded;

	public CounterView() {
		try {
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			initComponents(projectAccessor);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initComponents(ProjectAccessor projectAccessor) {
		setLayout(new GridBagLayout());
		setIndex(0);
		JButton button = new JButton(">>>>>");
		JButton buttonBack = new JButton("<<<<<");
		JLabel label = new JLabel("Use the buttons to navigate the counterexample trace");
		GridBagConstraints c = new GridBagConstraints();
		GridBagConstraints c2 = new GridBagConstraints();
		GridBagConstraints c3 = new GridBagConstraints();
		
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		
		c2.gridx = 0;
		c2.gridy = 1;
		c2.gridwidth = 1;
		
		c3.gridx = 0;
		c3.gridy = 0;
		c3.gridwidth = 0;
		
		
		add(buttonBack, c2);
		add(button, c);
		add(label,c3);
		addProjectEventListener();

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(smd!=null) {
						if(index < trace.size()) {
							color(getIndex(),getLastIndexColor(),trace.size()-1);
							setIndex(getIndex() + 1);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(smd != null) {
						if(index > 0) {
							setIndex(getIndex() - 1);
							if(getLastIndexColor() > 0) {
								setLastIndexColor(getLastIndexColor()-1);
							}
							colorBack(getIndex(),getLastIndexColor());
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}
	
	public void color(int index, int lastIndexColor, int finalIndex) throws ClassNotFoundException, LicenseNotFoundException, ProjectNotFoundException, IOException, ProjectLockedException, InvalidEditingException {
		projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
		boolean flag = false;
		if (!TransactionManager.isInTransaction()) {

			TransactionManager.beginTransaction();

		}
		if(CounterView.trace.get(index).contains("tr_")) {
			if(lastIndexColor != index) {
				if(CounterView.trace.get(lastIndexColor).contains("tr_")) {
					CounterView.transitionAdded.get(SMUtils.nameResolver(CounterView.trace.get(lastIndexColor).split("tr_", 2)[1])).setProperty("line.color", "#000000");
				}else if(CounterView.trace.get(lastIndexColor).contains("enter")) {
					CounterView.nodeAdded.get(SMUtils.nameResolver(CounterView.trace.get(lastIndexColor).split("st_", 2)[1])).setProperty("fill.color", "#FFFFCC");
				}
			}
			if(index == finalIndex) {
				CounterView.nodeAdded.get(SMUtils.nameResolver(CounterView.trace.get(index).split("st_", 2)[1])).setProperty("fill.color", "#A733BB");
			}else {
				CounterView.transitionAdded.get(SMUtils.nameResolver(CounterView.trace.get(index).split("tr_", 2)[1])).setProperty("line.color", "#FF0000");
			}
			setLastIndexColor(index);
		}else if(CounterView.trace.get(index).contains("enter")) {
			if(lastIndexColor != index) {
				if(CounterView.trace.get(lastIndexColor).contains("tr_")) {
					CounterView.transitionAdded.get(SMUtils.nameResolver(CounterView.trace.get(lastIndexColor).split("tr_", 2)[1])).setProperty("line.color", "#000000");
				}else if(CounterView.trace.get(lastIndexColor).contains("enter")) {
					CounterView.nodeAdded.get(SMUtils.nameResolver(CounterView.trace.get(lastIndexColor).split("st_", 2)[1])).setProperty("fill.color", "#FFFFCC");
				}
			}
			if(!(CounterView.typeAdded.get(SMUtils.nameResolver(CounterView.trace.get(index).split("st_", 2)[1])).equals("Junction"))) {
				if(index == finalIndex) {
					CounterView.nodeAdded.get(SMUtils.nameResolver(CounterView.trace.get(index).split("st_", 2)[1])).setProperty("fill.color", "#A733BB");
				}else {
					CounterView.nodeAdded.get(SMUtils.nameResolver(CounterView.trace.get(index).split("st_", 2)[1])).setProperty("fill.color", "#FF0000");
				}
				setLastIndexColor(index);
			}else {
				flag = true;
				setIndex(getIndex() + 1);
				setLastIndexColor(index);
				index = getIndex();
				lastIndexColor = getLastIndexColor();
				TransactionManager.endTransaction();
				color(index,lastIndexColor, finalIndex);
			}
		}
		if(!flag) {
			TransactionManager.endTransaction();
		}
	}
	
	
	public void colorBack(int index, int lastIndexColor) throws ClassNotFoundException, LicenseNotFoundException, ProjectNotFoundException, IOException, ProjectLockedException, InvalidEditingException {
		projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
		boolean flag = false;
		if (!TransactionManager.isInTransaction()) {

			TransactionManager.beginTransaction();

		}
		
		if(CounterView.trace.get(lastIndexColor).contains("tr_")) {
			if(CounterView.trace.get(index).contains("tr_")) {
				CounterView.transitionAdded.get(SMUtils.nameResolver(CounterView.trace.get(index).split("tr_", 2)[1])).setProperty("line.color", "#000000");
			}else if(CounterView.trace.get(index).contains("enter")) {
				CounterView.nodeAdded.get(SMUtils.nameResolver(CounterView.trace.get(index).split("st_", 2)[1])).setProperty("fill.color", "#FFFFCC");
			}
			if(index != lastIndexColor) {
				CounterView.transitionAdded.get(SMUtils.nameResolver(CounterView.trace.get(lastIndexColor).split("tr_", 2)[1])).setProperty("line.color", "#FF0000");
			}
		}else if(CounterView.trace.get(lastIndexColor).contains("enter")) {
			if(CounterView.trace.get(index).contains("tr_")) {
				CounterView.transitionAdded.get(SMUtils.nameResolver(CounterView.trace.get(index).split("tr_", 2)[1])).setProperty("line.color", "#000000");
			}else if(CounterView.trace.get(index).contains("enter")) {
				CounterView.nodeAdded.get(SMUtils.nameResolver(CounterView.trace.get(index).split("st_", 2)[1])).setProperty("fill.color", "#FFFFCC");
			}
			if(index != lastIndexColor) {
				if(!(CounterView.typeAdded.get(SMUtils.nameResolver(CounterView.trace.get(lastIndexColor).split("st_", 2)[1])).equals("Junction"))) {
					CounterView.nodeAdded.get(SMUtils.nameResolver(CounterView.trace.get(lastIndexColor).split("st_", 2)[1])).setProperty("fill.color", "#FF0000");
				}else {
					if(index > 0) {
						flag = true;
						setIndex(getIndex() - 1);
						if(getLastIndexColor() > 0) {
							setLastIndexColor(getLastIndexColor()-1);
						}
						index = getIndex();
						lastIndexColor = getLastIndexColor();
						TransactionManager.endTransaction();
						colorBack(index,lastIndexColor);
					}
				}
			}
		}
		if(!flag) {
			TransactionManager.endTransaction();
		}
	}

	@SuppressWarnings("deprecation")
	private void addProjectEventListener() {
		try {
			ProjectAccessor projectAccessor = ProjectAccessorFactory.getProjectAccessor();
			projectAccessor.addProjectEventListener(this);
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
	}

	private Container createLabelPane() {
		JLabel label = new JLabel("COUNTEREXAMPLE");
		JScrollPane pane = new JScrollPane(label);
		return pane;
	}

	@Override
	public void projectChanged(ProjectEvent e) {
	}

	@Override
	public void projectClosed(ProjectEvent e) {
	}

	@Override
	public void projectOpened(ProjectEvent e) {
	}

	@Override
	public void addSelectionListener(ISelectionListener listener) {
	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public String getDescription() {
		return "COUNTEREXAMPLE";
	}

	@Override
	public String getTitle() {
		return "CounterExample Navigation";
	}

	public void activated() {

	}

	public void deactivated() {

	}

	public IStateMachineDiagram getSmd() {
		return smd;
	}

	public static void setSmd(IStateMachineDiagram smd) {
		CounterView.smd = smd;
	}

	public static List<String> getTrace() {
		return trace;
	}

	public static void setTrace(List<String> trace) {
		CounterView.trace = cleanList(trace);
	}
	
	public static List<String> cleanList(List<String> trace) {
		for(int i = 0; i < trace.size(); i++) {
			if(!(trace.get(i).contains("enter")) && !(trace.get(i).contains("tr_"))) {
				trace.remove(i);
				i--;
			}
		}
		return trace;
	}

	public static HashMap<String, INodePresentation> getNodeAdded() {
		return nodeAdded;
	}

	public static void setNodeAdded(HashMap<String, INodePresentation> nodeAdded) {
		CounterView.nodeAdded = nodeAdded;
	}

	public static HashMap<String, ILinkPresentation> getTransitionAdded() {
		return transitionAdded;
	}

	public static void setTransitionAdded(HashMap<String, ILinkPresentation> transitionAdded) {
		CounterView.transitionAdded = transitionAdded;
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		CounterView.index = index;
	}

	public static int getLastIndexColor() {
		return lastIndexColor;
	}

	public static void setLastIndexColor(int lastIndexColor) {
		CounterView.lastIndexColor = lastIndexColor;
	}

	public static HashMap<String, String> getTypeAdded() {
		return typeAdded;
	}

	public static void setTypeAdded(HashMap<String, String> typeAdded) {
		CounterView.typeAdded = typeAdded;
	}

	public boolean isFirstTrans() {
		return isFirstTrans;
	}

	public void setFirstTrans(boolean isFirstTrans) {
		this.isFirstTrans = isFirstTrans;
	}

	public static void setCouterView(IStateMachineDiagram smd, HashMap<String, INodePresentation> nodeAdded,
			HashMap<String, ILinkPresentation> transitionAdded, HashMap<String, String> typeAdded) {
		CounterView.setSmd(smd);
		CounterView.setNodeAdded(nodeAdded);
		CounterView.setTransitionAdded(transitionAdded);
		CounterView.setTypeAdded(typeAdded);
		CounterView.setIndex(0);
		CounterView.setLastIndexColor(0);
		
	}
}
