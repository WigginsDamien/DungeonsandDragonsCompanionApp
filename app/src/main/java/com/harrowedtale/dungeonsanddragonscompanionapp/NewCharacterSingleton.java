package com.harrowedtale.dungeonsanddragonscompanionapp;

public class NewCharacterSingleton {
    private static NewCharacterSingleton single_instance = null;

    //variables
    //Step 1
    private String name;
    private String character_class;

    //Step 2
    private int level;
    private String alignment;
    private String race;

    //Step 3
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private NewCharacterSingleton(){
        //set defaults for all character values
        name = "default";
        character_class = "default";

        level = -1;
        alignment = "default";
        race = "default";

        strength = -1;
        dexterity = -1;
        constitution = -1;
        intelligence = -1;
        wisdom = -1;
        charisma = -1;
    }

    public static NewCharacterSingleton getInstance(){
        if(single_instance == null){
            single_instance = new NewCharacterSingleton();
        }

        return single_instance;
    }

    public void setWizardPageOne(String n, String c){
        name = n;
        character_class = c;
    }

    public void setWizardPageTwo(int l, String a, String r){
        level = l;
        alignment = a;
        race = r;
    }

    public void setWizardPageThree(int s, int d, int con, int i, int w, int ch){
        strength = s;
        dexterity = d;
        constitution = con;
        intelligence = i;
        wisdom = w;
        charisma = ch;
    }

    public String getName(){
        return name;
    }
    public String getAlignment(){
        return alignment;
    }
    public int getCharisma(){
        return charisma;
    }
}

