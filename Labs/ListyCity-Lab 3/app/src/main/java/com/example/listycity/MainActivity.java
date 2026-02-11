package com.example.listycity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    private ListView cityList;
    private CustomList cityAdapter;
    private ArrayList<City> dataList;
    private City selectedCity = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeData();
        setupAdapter();
        setupListeners();
    }

    private void initializeViews() {
        cityList = findViewById(R.id.city_list);
    }

    private void initializeData() {
        dataList = new ArrayList<>();
        dataList.add(new City("Edmonton", "AB"));
        dataList.add(new City("Vancouver", "BC"));
        dataList.add(new City("Moscow", "RU"));
        dataList.add(new City("Sydney", "AU"));
    }

    private void setupAdapter() {
        cityAdapter = new CustomList(this, dataList);
        cityList.setAdapter(cityAdapter);
    }

    private void setupListeners() {
        final Button addCityButton = findViewById(R.id.add_city_button);
        addCityButton.setOnClickListener(v -> new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY"));

        final Button deleteCityButton = findViewById(R.id.delete_city_button);
        deleteCityButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                selectedCity = null;
            }
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            final City cityToEdit = dataList.get(position);
            AddCityFragment.newInstance(cityToEdit).show(getSupportFragmentManager(), "EDIT_CITY");
        });

        cityList.setOnItemLongClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
            return true;
        });
    }

    @Override
    public void onOkPressed(@NonNull final City newCity) {
        dataList.add(newCity);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditPressed(@NonNull final City city) {
        cityAdapter.notifyDataSetChanged();
    }
}
