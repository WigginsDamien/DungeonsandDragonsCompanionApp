package com.harrowedtale.dungeonsanddragonscompanionapp;

public class ItemObject {

    private String cost;
    private String description;
    private String equipmentCategory;
    private int index;
    private String name;
    private int weight;

    public ItemObject () {

    }

    public ItemObject(String cost, String description, String equipmentCategory, int index, String name, int weight) {
        this.description = description;
        this.cost = cost;
        this.equipmentCategory = equipmentCategory;
        this.index = index;
        this.weight = weight;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public int getWeight() {
        return weight;
    }

    public String getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getEquipmentCategory() {
        return equipmentCategory;
    }
}
