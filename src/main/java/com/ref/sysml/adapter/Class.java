package com.ref.sysml.adapter;

import org.omg.sysml.xtext.sysml.Usage;

import com.ref.interfaces.activityDiagram.IClass;

public class Class implements IClass {
	Usage classe;
	
	public Class(Usage classe) {
		super();
		this.classe = classe;
	}

	public Usage getClasse() {
		return classe;
	}
	
	@Override
	public String getId() {
		return String.valueOf(System.identityHashCode(classe));
	}

	@Override
	public String getName() {
		return classe.getDatatype();
	}

	@Override
	public String getDefinition() {
		return "";
	}

	@Override
	public String[] getStereotypes() {
		return new String[0];
	}


}
