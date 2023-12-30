package com.ref;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.ref.astah.adapter.Activity;
import com.ref.astah.adapter.ActivityDiagram;
import com.ref.exceptions.ParsingException;
import com.ref.exceptions.WellFormedException;
import com.ref.parser.activityDiagram.ADParser;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.IActivityDiagram;


public class SubActivity {
	private static SubActivity instancia = null;

	private String cspFile;
	private IDiagram activityDiagram;

	private SubActivity() {
		this.cspFile = "";
		this.activityDiagram = null;
	}

	public static SubActivity getInstance() {
		if (instancia == null) {
			instancia = new SubActivity();
		}
		return instancia;
	}
	
	private void parseActivityDiagram() throws WellFormedException, ParsingException {
		Activity activity = new Activity(((IActivityDiagram) this.getActivityDiagram()).getActivity());
		ActivityDiagram activityDiagram = new ActivityDiagram((IActivityDiagram) this.getActivityDiagram());
		
		ADParser activityParser = new ADParser(activity, activityDiagram.getName(),activityDiagram);
		String diagramActivityCSP = activityParser.parserDiagram(true);
		this.setCspFile(this.getCspFile() + "\n\n" + diagramActivityCSP);
		activityParser.resetStatic();
	}
	
	public String getCspFile() {
		return cspFile;
	}

	private void setCspFile(String cspFile) {
		this.cspFile = cspFile;
	}
	
	private IDiagram getActivityDiagram() {
		return this.activityDiagram;
	}

	public void setSubActivityDiagram(String diagramName) {
		try {
			if(!this.cspFile.contains("ID_"+diagramName + " =")) {
				ProjectAccessor prjAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
				INamedElement[] iNamedElements = prjAccessor.findElements(IActivityDiagram.class, diagramName);
				IDiagram diagram = (IDiagram) iNamedElements[0];
				this.activityDiagram = diagram;
				this.parseActivityDiagram();
			}
		}  catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void reset() {
		this.activityDiagram = null;
		this.cspFile = "";
	}
}
