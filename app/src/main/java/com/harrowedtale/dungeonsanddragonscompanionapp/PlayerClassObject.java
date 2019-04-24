package com.harrowedtale.dungeonsanddragonscompanionapp;

import java.lang.reflect.Array;
import java.util.Map;

public class PlayerClassObject{

  //  private Array classFeatures;
    private String description;
 //   private Array equipment;
    private Map<String, String> hitPoints;
    private Map proficiences;
    private String startingGold;
//    private Array subclass;
    private String suggestedAbilities;
    private Map table;

    PlayerClassObject() {

    }

    public PlayerClassObject(Array classFeatures, String description, Array equipment, Map<String, String> hitPoints, Map proficiences, String startingGold, Array subclass, String suggestedAbilities, Map table){
  //      this.classFeatures = classFeatures;
        this.description = description;
 //       this.equipment = equipment;
        this.hitPoints = hitPoints;
 //       this.proficiences = proficiences;
        this.startingGold = startingGold;
//        this.subclass = subclass;
        this.suggestedAbilities = suggestedAbilities;
   //     this.table = table;
    }

 /*   public Array getClassFeatures() {
        return classFeatures;
    }

    public Array getEquipment() {
        return equipment;
    }

    public Array getSubclass() {
        return subclass;
    }*/

    public Map getHitPoints() {
        return hitPoints;
    }

 /*   public Map getProficiences() {
        return proficiences;
    }

    public Map getTable() {
        return table;
    }*/

    public String getDescription() {
        return description;
    }

    public String getStartingGold() {
        return startingGold;
    }

    public String getSuggestedAbilities() {
        return suggestedAbilities;
    }

}
