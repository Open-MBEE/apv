# Activity Property Verifier

[![Jira](https://img.shields.io/badge/issues-jira-blue)](https://openmbee.atlassian.net/browse/apv)

A property checker for UML/SysML activity diagrams.


## Table of contents
* [General info](#general-info)
* [Prerequisites](#Prerequisites)
* [Technologies](#technologies)
* [Using APV in AstahUML](#using-apv-in-astahuml)

## General info
Activity Property Verifier (APV) is a tool to check properties on UML/SysML activity diagrams. Currently, the supported properties are deadlock freedom and nondeterminism. The tool supports several constructs, including all control nodes, some object nodes (parameter, pins, and simple object), and several kinds of actions (send signal, accept event, opaque and call behavior). Its underlying semantics is defined in terms of CSP (Communicating Sequential Process), the well-known process algebraic language, and the semantics of fUML (Foundational UML). It automatically translates activities to CSP and invokes the FDR checker (the CSP model checker) to perform the analyses. The results from FDR are traced back to the activity level. Therefore, no knowledge of formal notation is required to manipulate APV. Nevertheless, the only requirement is to have the FDR checker installed and linked to APV in order to perform the analyses. 

APV can be integrated in any UML/SysML environment because its semantics is tool agnostic, however, adapters from these environments must be developed according to the API provided. For now, its first version works as a plugin for the Astah modeling tool. However, an integration with OpenMBEE MMS notation is being built. 

## Prerequisites

* [FDR4](https://cocotec.io/fdr/) or later.
* [AstahUML](https://astah.net/products/astah-uml/), for the current integrated environment.
	
## Technologies
Project is created with:
* Java 8
* Astah SDK
* FDR Java API
	
## Using APV in AstahUML

### Installation

1. [Download](https://github.com/Open-MBEE/apv/blob/main/bin/PropertyVerifier-1.2.jar) the plug-in .jar file.

2. Copy the downloaded plug-in .jar file to the <plugins> folder in the Astah installation directory

3. Open AstahUML and go to "Plugin -> Installed Plugins" menu, then check if the 






