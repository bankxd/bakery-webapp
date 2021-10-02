package com.bakerysystem.Model;

public class Ingredient {

    private int ingredientId;
    private String ingredientName;
    private int quantity;
    private String unitOfMeasurement;

    public Ingredient() {
    }

    public Ingredient(int ingredientId, String ingredientName, int quantity, String unitOfMeasurement) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Ingredient(String ingredientName, int quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public void cosume() {

    }

    public Ingredient(String ingredientName, int quantity, String unitOfMeasurement) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "ingredientId=" + ingredientId + ", ingredientName=" + ingredientName + ", quantity=" + quantity + ", unitOfMeasurement=" + unitOfMeasurement + '}';
    }
    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
}