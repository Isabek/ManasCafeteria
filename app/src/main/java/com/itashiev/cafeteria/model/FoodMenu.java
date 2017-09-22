package com.itashiev.cafeteria.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FoodMenu {
    private String date;
    private String totalCalories;
    private List<Food> foods;

    @JsonCreator
    public FoodMenu(@JsonProperty("date") String date,
                    @JsonProperty("total_calories") String totalCalories,
                    @JsonProperty("foods") List<Food> foods) {
        this.date = date;
        this.totalCalories = totalCalories;
        this.foods = foods;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(String totalCalories) {
        this.totalCalories = totalCalories;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        FoodMenu o = (FoodMenu) obj;
        return this.date.equals(o.date);
    }
}
