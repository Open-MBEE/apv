package com.ref.parser.activityDiagram;

import com.ref.interfaces.activityDiagram.IActivity;

public class ADDefineTokenManager {

    private IActivity ad;
    private ADUtils adUtils;

    public ADDefineTokenManager(IActivity ad, ADUtils adUtils) {
        this.ad = ad;
        this.adUtils = adUtils;
    }

    public String defineTokenManager() {
        StringBuilder tokenManager = new StringBuilder();
        String nameDiagram = adUtils.nameDiagramResolver(ad.getName());

        tokenManager.append("TokenManager_" + nameDiagram + "(id,x,init) = update_" + nameDiagram + ".id?c?y:limiteUpdate_" + nameDiagram
                + " -> x+y < 10 & x+y > -10 & TokenManager_" + nameDiagram + "(id,x+y,1) [] clear_" + nameDiagram + ".id?c -> endDiagram_" + nameDiagram
                + ".id -> SKIP [] x == 0 & init == 1 & endDiagram_" + nameDiagram + ".id -> SKIP\n");
        tokenManager.append("TokenManager_" + adUtils.nameDiagramResolver(ad.getName()) + "_t(id,x,init) = TokenManager_" + nameDiagram + "(id,x,init)\n");

        return tokenManager.toString();
    }
}
