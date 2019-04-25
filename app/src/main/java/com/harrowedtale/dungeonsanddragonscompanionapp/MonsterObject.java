package com.harrowedtale.dungeonsanddragonscompanionapp;

public class MonsterObject{

   // private String monster_name;
    private int challengeRating;
    private int hitPoints;

    public MonsterObject() {

    }

    public MonsterObject(int challengeRating, int hitPoints, String monster_name) {
        this.challengeRating = challengeRating;
        this.hitPoints = hitPoints;
    //    this.monster_name = monster_name;
    }

    /*public String getMonster_name(){
        return monster_name;
    }*/

    public int getMonster_CR(){
        return challengeRating;
    }

    public int getMonster_HP(){
        return hitPoints;
    }
}
