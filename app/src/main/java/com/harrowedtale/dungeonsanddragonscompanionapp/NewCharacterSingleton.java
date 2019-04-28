package com.harrowedtale.dungeonsanddragonscompanionapp;

public class NewCharacterSingleton {
    private static NewCharacterSingleton single_instance = null;

    //variables
    //Step 1
    private String name;
    private String character_class;

    //Step 2
    private int level;
    private int hp;
    private String alignment;
    private String race;

    //Step 3
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    //Step 4
    private String prof1;
    private String prof2;
    private String prof3; //may be empty
    private String prof4; //may be empty

    private NewCharacterSingleton(){
        //set defaults for all character values
        name = "default";
        character_class = "default";

        level = -1;
        hp = -1;
        alignment = "default";
        race = "default";

        strength = -1;
        dexterity = -1;
        constitution = -1;
        intelligence = -1;
        wisdom = -1;
        charisma = -1;

        prof1 = "";
        prof2 = "";
        prof3 = "";
        prof4 = "";
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

    public void setWizardPageTwo(int l, int h, String a, String r){
        level = l;
        hp = h;
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

    public void setWizardPageFour(String p1, String p2, String p3, String p4){
        prof1 = p1;
        prof2 = p2;

        switch (getProficiencyCount())
        {
            case 2:
                break;
            case 3:
                prof3 = p3;
                break;
            case 4:
                prof3 = p3;
                prof4 = p4;
                break;
            default:
                //shouldn't happen
                break;
        }
    }

    public String getName(){
        return name;
    }

    public String getCharacterClass(){
        return character_class;
    }

    public String getLevel(){ return String.valueOf(level); }

    public String getHP() {
        return String.valueOf(hp);
    }

    public String getAlignment(){ return alignment; }

    public String getRace(){return race;}

    public String getStrength(){ return String.valueOf(strength); }

    public String getDexterity(){ return String.valueOf(dexterity); }

    public String getConstitution(){ return String.valueOf(constitution); }

    public String getIntelligence(){ return String.valueOf(intelligence); }

    public String getWisdom(){ return String.valueOf(wisdom); }

    public String getCharisma(){ return String.valueOf(charisma); }

    public String getFirstProficiency(){return prof1;}

    public String getSecondProficiency(){return prof2;}

    public String getThirdProficiency(){return prof3;}

    public String getFourthProficiency(){return prof4;}

    public int getProficiencyCount(){
        //this function returns the number of proficiencies a class gets to pick
        int profCount = 0;

        switch(character_class)
        {
            case "Barbarian":
                profCount = 2;
                break;
            case "Bard":
                profCount = 3;
                break;
            case "Cleric":
                profCount = 2;
                break;
            case "Druid":
                profCount = 2;
                break;
            case "Fighter":
                profCount = 2;
                break;
            case "Monk":
                profCount = 2;
                break;
            case "Paladin":
                profCount = 2;
                break;
            case "Ranger":
                profCount = 3;
                break;
            case "Rogue":
                profCount = 4;
                break;
            case "Sorcerer":
                profCount = 2;
                break;
            case "Warlock":
                profCount = 2;
                break;
            case "Wizard":
                profCount = 2;
                break;
        }

        return profCount;
    }

    public int getClassProficiencies(){
        int arrayId = 0;

        switch(character_class)
        {
            case "Barbarian":
                arrayId = R.array.barbarian_profs;
                break;
            case "Bard":
                arrayId = R.array.bard_profs;
                break;
            case "Cleric":
                arrayId = R.array.cleric_profs;
                break;
            case "Druid":
                arrayId = R.array.druid_profs;
                break;
            case "Fighter":
                arrayId = R.array.fighter_profs;
                break;
            case "Monk":
                arrayId = R.array.monk_profs;
                break;
            case "Paladin":
                arrayId = R.array.paladin_profs;
                break;
            case "Ranger":
                arrayId = R.array.ranger_profs;
                break;
            case "Rogue":
                arrayId = R.array.rogue_profs;
                break;
            case "Sorcerer":
                arrayId = R.array.sorcerer_profs;
                break;
            case "Warlock":
                arrayId = R.array.warlock_profs;
                break;
            case "Wizard":
                arrayId = R.array.wizard_profs;
                break;
        }

        return arrayId;
    }
}

