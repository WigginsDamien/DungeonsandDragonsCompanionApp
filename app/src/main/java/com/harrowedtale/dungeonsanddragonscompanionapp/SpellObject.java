package com.harrowedtale.dungeonsanddragonscompanionapp;

public class SpellObject {

    private String monster_name;
    private int level;
    private int range;

    public SpellObject() {

    }

    public SpellObject(int level, int range, String spell_name) {
        this.level = level;
    }

    public String getMonster_name () {
        return monster_name;
    }

    public int getMonster_CR () {
        return level;
    }

    public int getMonster_HP () {
        return range;
    }

}