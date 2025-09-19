package com.example.listycitylab3;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    private ArrayList<City> dataList;
    private CityArrayAdapter cityAdapter;
    private int editingPosition = -1;

    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    private void editCity(City updatedCity) {
        if (editingPosition != -1) {
            dataList.set(editingPosition, updatedCity);
            cityAdapter.notifyDataSetChanged();
            editingPosition = -1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initial data
        String[] cities = {"Edmonton", "Vancouver", "Toronto"};
        String[] provinces = {"AB", "BC", "ON"};
        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        // set up adapter + list
        ListView cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        // edit city when clicked
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            editingPosition = position;
            City cityToEdit = dataList.get(position);
            EditCityFragment editFragment = new EditCityFragment(cityToEdit, this::editCity);
            editFragment.show(getSupportFragmentManager(), "Edit City");
        });

        // add city button
        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });
    }
}