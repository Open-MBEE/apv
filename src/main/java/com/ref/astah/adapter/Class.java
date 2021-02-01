package com.ref.astah.adapter;

import com.ref.interfaces.activityDiagram.IClass;

public class Class implements IClass {
	com.change_vision.jude.api.inf.model.IClass classe;
	
	public Class(com.change_vision.jude.api.inf.model.IClass classe) {
		super();
		this.classe = classe;
	}

	@Override
	public String getId() {
		return classe.getId();
	}

	@Override
	public String getName() {
		return classe.getName();
	}

	@Override
	public String getDefinition() {
		return classe.getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return classe.getStereotypes();
	}


}
