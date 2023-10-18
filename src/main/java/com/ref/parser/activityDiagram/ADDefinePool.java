package com.ref.parser.activityDiagram;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ref.interfaces.activityDiagram.IActivity;

public class ADDefinePool {

    private IActivity ad;
    private HashMap<String, List<IActivity>> signalChannels;
    private String firstDiagram;
    private List<Pair<String, Integer>> countAccept;
    private ADUtils adUtils;
	private HashMap<String, List<String>> signalPins;//<Signal,tipos dos pinos dele>

    public ADDefinePool(IActivity ad, HashMap<String, List<IActivity>> signalChannels2, HashMap<String, List<String>> signalPins, String firstDiagram, List<Pair<String, Integer>> countAccept, ADUtils adUtils) {
        this.ad = ad;
        this.signalChannels = signalChannels2;
        this.signalPins = signalPins;//(por enquanto não serve pra nada, mas pode ser necessário em alterações futuras)
        this.firstDiagram = firstDiagram;
        this.countAccept = countAccept;
        this.adUtils = adUtils;
    }

    public String definePool() {
        StringBuilder pool = new StringBuilder();
        String nameDiagram = adUtils.nameDiagramResolver(ad.getName());
        List<String> keySignalChannels = new ArrayList<String>();
    	keySignalChannels.addAll(signalChannels.keySet());
    	
        if (firstDiagram.equals(ad.getId())) {
        	
        	if(!signalChannels.isEmpty()) {
	        	String poolDatatype = "datatype POOLNAME = ";
	        	pool.append(poolDatatype);
        	
	        	for(String signal: keySignalChannels) {
	        		pool.append(signal + "|");
	        	}
	        	if(pool.lastIndexOf("|") != -1) pool.replace(pool.lastIndexOf("|"),pool.lastIndexOf("|")+1,"\n");
        	
	        	for(String signal: keySignalChannels) {
	        		pool.append("POOL(id,"+signal+") = pool_"+signal+"_t(id,<>)\n");
	        	}
	        	pool.append("pools(id) =[|{|endDiagram_"+nameDiagram+".id|}|]x:POOLNAME @ POOL(id,x)\n");
        	}
        	
            for (String signal: keySignalChannels) {
                String poolName = "pool_" + signal;
                String eventName = "event_" + signal + "_" + nameDiagram;

                pool.append( poolName + "(l) = ");
                pool.append("(signal_" + signal + "?id?" + eventName + " -> ");
                pool.append("if length(l) < 5 then " + poolName + "(l^<" + eventName + ">) ");
                pool.append("else " + poolName + "(l))");

                int lengthAccept = 0;

                for (int i = 0; i < countAccept.size(); i++) {
                    if (countAccept.get(i).getKey().equals(signal)) {
                        lengthAccept = countAccept.get(i).getValue();
                        break;
                    }
                }

                for (int i = 0; i < lengthAccept - 1; i++) {
                    pool.append(" [] (length(l) > 0 & accept_" + signal + "?id."+ (i+1) + "!head(l) -> " + poolName + "(tail(l)))");
                }

                pool.append("\n");

                pool.append(poolName + "_t(id,l) = " + poolName + "(l) /\\ END_DIAGRAM_" + nameDiagram + "(id)\n");
            }
            
        }

        return pool.toString();
    }
}
