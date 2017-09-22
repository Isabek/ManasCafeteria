package com.itashiev.cafeteria;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.itashiev.cafeteria.adapter.SectionsPagerAdapter;
import com.itashiev.cafeteria.store.Cache;
import com.itashiev.cafeteria.utils.CustomDateRangeLimiter;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final int FIRST_TAB_INDEX = 0;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_date_select) {
            DatePickerDialog dpd = DatePickerDialog.newInstance(MainActivity.this);
            dpd.setVersion(DatePickerDialog.Version.VERSION_1);
            dpd.setDateRangeLimiter(new CustomDateRangeLimiter());
            dpd.show(getFragmentManager(), "Datepickerdialog");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        sectionsPagerAdapter.setFromDate(calendar.getTime());
        sectionsPagerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(FIRST_TAB_INDEX);
    }
}
