package com.ref.parser.activityDiagram;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IObjectFlow;
import com.ref.interfaces.activityDiagram.IObjectNode;

public class ADDefineDecision {

    private IActivity ad;

    private HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode;
    private HashMap<Pair<IActivity, String>, String> syncChannelsEdge;
    private HashMap<Pair<IActivity, String>, String> syncObjectsEdge;
    private HashMap<String, String> objectEdges;
    private ADUtils adUtils;

    public ADDefineDecision(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2, HashMap<Pair<IActivity, String>, String> syncChannelsEdge2,
                            HashMap<Pair<IActivity, String>, String> syncObjectsEdge2, HashMap<String, String> objectEdges, ADUtils adUtils) {
        this.ad = ad;
        this.alphabetNode = alphabetNode2;
        this.syncChannelsEdge = syncChannelsEdge2;
        this.syncObjectsEdge = syncObjectsEdge2;
        this.objectEdges = objectEdges;
        this.adUtils = adUtils;
    }
    
    public String defineDecision(IActivityNode activityNode) throws ParsingException {
    	StringBuilder decision = new StringBuilder();
        ArrayList<String> alphabet = new ArrayList<>();
        String nameDecision = adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName());
        String nameDecisionTermination = adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_t";
        String endDiagram = "END_DIAGRAM_" + adUtils.nameDiagramResolver(ad.getName());
        IFlow[] outFlows = activityNode.getOutgoings();
        IFlow[] inFlows = activityNode.getIncomings();
        

        if (inFlows.length != 1) {
			throw new ParsingException("Decision node must have exactly one incoming edge.");
		}
       
        IFlow inEdge = inFlows[0];
        Pair<IActivity,String> key = new Pair<IActivity, String>(ad,inEdge.getId());
        
        decision.append(nameDecision + "(id) = ");
        
        // case input is object
        if (inEdge instanceof IObjectFlow) {
        	String oeIn;
        	String typeObject;
        	try {
				typeObject = ((IObjectFlow)inEdge).getBase().getName();
			} catch (NullPointerException e) {
				throw new ParsingException("Object flow does not have a type.");
			}
        	if (syncObjectsEdge.containsKey(key)) {
                oeIn = syncObjectsEdge.get(key);
                if (!objectEdges.containsKey(oeIn)) {
	                objectEdges.put(oeIn, typeObject);
				}
            } else {
            	 oeIn = adUtils.createOE();
                 syncObjectsEdge.put(key, oeIn);
                 objectEdges.put(oeIn, typeObject);
            }

        	IFlow obEdge = inEdge;
        	while (!(obEdge.getSource() instanceof IObjectNode)) {
        		obEdge = obEdge.getSource().getIncomings()[0];
        	}
        	
        	
        	String input = ADUtils.nameResolver(obEdge.getSource().getName());
            
            
            adUtils.oe(alphabet, decision, oeIn, "?" + input, " -> ");

            decision.append("(");
            
            List<String> prevGuard = new ArrayList<>();
            
            
            // output channels
            for (int i = 0; i < outFlows.length; i++) {    //creates the parallel output channels
                
            	if (!(outFlows[i] instanceof IObjectFlow)) {
					throw new ParsingException("As the incoming edge of the decision node "+ activityNode.getName() + " is an ObjectFlow, then all outgoing edges\r\n" + 
							"shall be ObjectFlows");
				}
            	
            	String outputType;
            	try {
            		outputType = ((IObjectFlow)outFlows[i]).getBase().getName();
    			} catch (NullPointerException e) {
    				throw new ParsingException("Object flow does not have a type.");
    			}
            	
            	if (!typeObject.equals(outputType)) {
            		throw new ParsingException("Incoming edge type and outgoing edge type must be the same.");
				}
            	
            	String oeOut = "";
                
                key = new Pair<IActivity, String>(ad,outFlows[i].getId());
                if (syncObjectsEdge.containsKey(key)) {
                    oeOut = syncObjectsEdge.get(key);
                    if (!objectEdges.containsKey(oeOut)) {
    	                objectEdges.put(oeOut, outputType);
    				}
                } else {
                	 oeOut = adUtils.createOE();
                     syncObjectsEdge.put(key, oeOut);
                     objectEdges.put(oeOut, outputType);
                }
                   
                if(!adUtils.nameDiagramResolver(outFlows[i].getGuard()).equalsIgnoreCase("else")) {//If the guard is not else
                	decision.append(outFlows[i].getGuard() == "" ? "true & (dc -> ": (outFlows[i].getGuard() + " & (dc -> "));//if the guard is empty then it is assumed true
                	prevGuard.add(outFlows[i].getGuard()); //saves the guard for the next else
                }else {
                	
                	boolean first = true;
                	
                	for (String prev : prevGuard) {
                		if (first) {
                			decision.append("not(" + prev + ") ");
                			first = false;
                		} else {
                			decision.append("and not(" + prev + ") ");
                		}
                	}
                	
                	decision.append("& (dc -> ");
//                	decision.append("not "+prevGuard.get(prevGuard.size()-1) + " & (dc -> ");
//                	prevGuard.remove(prevGuard.size()-1);
                }
                
                if (!alphabet.contains("dc")) {
                    alphabet.add("dc");
                }             

                if (i >= 0 && i < outFlows.length - 1) {
                	adUtils.oe(alphabet, decision, oeOut, "!" + input, " -> SKIP) [] ");
                } else {
                    adUtils.oe(alphabet, decision, oeOut, "!" + input, " -> SKIP)");
                }
            }

            decision.append("); ");

            decision.append(nameDecision + "(id)\n");

            decision.append(nameDecisionTermination + "(id) = ");
            decision.append("(" + nameDecision + "(id) /\\ " + endDiagram + "(id)) \\{|dc|}\n");

            alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName())+".id");
            key = new Pair<IActivity, String>(ad,adUtils.nameDiagramResolver(activityNode.getName()));
            alphabetNode.put(key, alphabet);
            
		} else { // when input is control

			Pair<IActivity, String> sync = new Pair<IActivity, String>(ad, inFlows[0].getId());

            String ceIn;
           
            if (syncChannelsEdge.containsKey(sync)) {
                ceIn = syncChannelsEdge.get(sync);
            } else {
            	ceIn = adUtils.createCE();
                syncChannelsEdge.put(sync, ceIn);
            }
                       
            adUtils.ce(alphabet, decision, ceIn, " -> ");            

            String allGuards = "";
            int countGuards = 0;

            for (int i = 0; i < outFlows.length; i++) {
            	if (outFlows[i] instanceof IObjectFlow) {
            		throw new ParsingException("As the incoming edge of the decision node "+ activityNode.getName() + " is an ControlFlow, then all outgoing edges\r\n" + 
    						"shall be ControlFlows");
				}
                if (outFlows[i].getGuard().length() > 0 &&
                        !adUtils.nameDiagramResolver(outFlows[i].getGuard()).equalsIgnoreCase("else")) {
                    countGuards = adUtils.addCountGuard(nameDecision + "_guard");
                    allGuards += "?" + adUtils.nameDiagramResolver(outFlows[i].getGuard());
                }
            }

            if (countGuards > 0) {
                decision.append(nameDecision + "_guard" + allGuards + " -> ");
                alphabet.add(nameDecision + "_guard");
            }

            decision.append("(");

            for (int i = 0; i < outFlows.length; i++) {    //creates the parallel output channels
            	String ce;
                key = new Pair<IActivity, String>(ad,outFlows[i].getId());

            	if (syncChannelsEdge.containsKey(key)) {
    				ce = syncChannelsEdge.get(key);
    			} else {
    				ce = adUtils.createCE();
    				syncChannelsEdge.put(key, ce);
    			}

                // Guard treatment
                if (outFlows[i].getGuard().length() == 0) {
                    decision.append("(dc -> ");
                    if (!alphabet.contains("dc")) {
                        alphabet.add("dc");
                    }
                } else if (adUtils.nameDiagramResolver(outFlows[i].getGuard()).equalsIgnoreCase("else")) {
                    boolean first = true;
                    for (int x = 0; x < outFlows.length; x++) {
                        if (!adUtils.nameDiagramResolver(outFlows[x].getGuard()).equalsIgnoreCase("else")) {
                            if (first) {
                                decision.append("not(" + adUtils.nameDiagramResolver(outFlows[x].getGuard()) + ") ");
                                first = false;
                            } else {
                                decision.append("and not(" + adUtils.nameDiagramResolver(outFlows[x].getGuard()) + ") ");
                            }
                        }
                    }

                    decision.append("& (");
                } else {
                    decision.append(adUtils.nameDiagramResolver(outFlows[i].getGuard()) + " & (");
                }

                if (i >= 0 && i < outFlows.length - 1) {
                    adUtils.ce(alphabet, decision, ce, " -> SKIP) [] ");
                } else {
                    adUtils.ce(alphabet, decision, ce, " -> SKIP)");
                }
            }

            decision.append("); ");

            decision.append(nameDecision + "(id)\n");

            decision.append(nameDecisionTermination + "(id) = ");
            decision.append(nameDecision + "(id) /\\ " + endDiagram + "(id) \\{|dc|}\n");

            alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName())+".id");
            key = new Pair<IActivity, String>(ad,adUtils.nameDiagramResolver(activityNode.getName()));
            alphabetNode.put(key, alphabet);
		}
        return decision.toString();
    }
    
}
