package com.itashiev.cafeteria.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itashiev.cafeteria.App;
import com.itashiev.cafeteria.R;
import com.itashiev.cafeteria.model.Food;
import com.itashiev.cafeteria.model.FoodMenu;
import com.itashiev.cafeteria.store.Cache;
import com.itashiev.cafeteria.utils.DateUtil;
import com.itashiev.cafeteria.utils.Utils;

import java.util.Collections;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_DATE_MILLS = "section_date_milliseconds";

    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(Date date) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_SECTION_DATE_MILLS, date.getTime());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        String date = DateUtil.formatted(getArguments().getLong(ARG_SECTION_DATE_MILLS));
        fillMenu(inflater, rootView, date);
        return rootView;
    }

    private void fillMenu(LayoutInflater inflater, View rootView, String date) {
        FoodMenu foodMenu = Cache.getFoodMenu(getActivity().getApplicationContext(), date);
        if (foodMenu != null) {
            fillMenuView(inflater, rootView, foodMenu);
            return;
        }
        loadMenuFromApi(inflater, rootView, date);
    }

    private void fillMenuView(LayoutInflater inflater, View rootView, FoodMenu foodMenu) {
        final LinearLayout foodsLayout = rootView.findViewById(R.id.foods_layout);
        if (foodMenu == null || foodMenu.getFoods() == null) {
            return;
        }
        rootView.findViewById(R.id.progressbar).setVisibility(View.INVISIBLE);
        for (Food food : foodMenu.getFoods()) {
            LinearLayout foodLayout = (LinearLayout) inflater.inflate(R.layout.food, null, false);
            ((TextView) foodLayout.findViewById(R.id.food_name)).setText(Utils.uppercase(food.getName()));
            ((TextView) foodLayout.findViewById(R.id.food_calories)).setText(Utils.wrapCalories(food.getCalories()));
            foodsLayout.addView(foodLayout);
        }
    }

    private void loadMenuFromApi(final LayoutInflater inflater, final View rootView, final String date) {
        App.api().getTodayMenu(date).enqueue(new Callback<FoodMenu>() {
            @Override
            public void onResponse(Call<FoodMenu> call, Response<FoodMenu> response) {
                FoodMenu foodMenu = response.body();
                if (foodMenu == null) {
                    rootView.findViewById(R.id.progressbar).setVisibility(View.INVISIBLE);
                    rootView.findViewById(R.id.empty_menu).setVisibility(View.VISIBLE);
                    return;
                }
                fillMenuView(inflater, rootView, foodMenu);
                try {
                    Cache.save(getActivity().getApplicationContext(), Collections.singletonList(foodMenu));
                } catch (Exception e) {
                    Log.e(PlaceholderFragment.class.getName(), e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<FoodMenu> call, Throwable t) {

            }
        });
    }
}