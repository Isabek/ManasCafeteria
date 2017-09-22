package com.itashiev.cafeteria.store;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itashiev.cafeteria.model.FoodMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cache {
    private static final String PREF_NAME = "FOOD_MENUS";
    private static final String FOOD_MENUS_LIST_JSON = "FOOD_MENUS_LIST_JSON";

    public static void save(Context context, List<FoodMenu> foodMenus) throws Exception {
        List<FoodMenu> savedFoodMenus = read(context);
        for (FoodMenu foodMenu : foodMenus) {
            if (savedFoodMenus.contains(foodMenu)) {
                savedFoodMenus.remove(foodMenu);
            } else {
                savedFoodMenus.add(foodMenu);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(savedFoodMenus);

        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(FOOD_MENUS_LIST_JSON, str);
        editor.apply();
    }

    public static FoodMenu getFoodMenu(Context context, String date) {
        List<FoodMenu> foodMenus = new ArrayList<>();

        try {
            foodMenus = read(context);
        } catch (Exception ignored) {

        }

        for (FoodMenu foodMenu : foodMenus) {
            if (foodMenu.getDate().equals(date)) {
                return foodMenu;
            }
        }
        return null;
    }

    public static void clear(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }

    private static List<FoodMenu> read(Context context) throws Exception {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String foodMenusListJson = pref.getString(FOOD_MENUS_LIST_JSON, null);
        if (foodMenusListJson == null) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        FoodMenu[] foodMenus = objectMapper.readValue(foodMenusListJson, FoodMenu[].class);
        return new ArrayList<>(Arrays.asList(foodMenus));
    }

}
