package com.harrowedtale.dungeonsanddragonscompanionapp;

import java.util.Map;

public class RaceObject {

    private Map<String, String> Basic;
    private Map<String, String> Subrace;
    private Map<String, String> Tinker;

    public RaceObject () {

    }

    public RaceObject(Map<String, String> Basic, Map<String, String> Subrace, Map<String, String> Tinker) {
        this.Basic = Basic;
        this.Subrace = Subrace;
        this.Tinker = Tinker;
    }

    public Map<String, String> getBasic() {
        return Basic;
    }

    public Map<String, String> getSubrace() {
        return Subrace;
    }

    public Map<String, String> getTinker() {
        return Tinker;
    }
}

