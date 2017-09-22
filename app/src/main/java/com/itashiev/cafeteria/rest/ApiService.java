package com.itashiev.cafeteria.rest;


import com.itashiev.cafeteria.model.FoodMenu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/menus")
    Call<List<FoodMenu>> getMenus();

    @GET("/menus/{date}")
    Call<FoodMenu> getTodayMenu(@Path("date") String date);
}
