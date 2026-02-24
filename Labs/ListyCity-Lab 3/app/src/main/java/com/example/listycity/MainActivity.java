package com.example.listycity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    private ListView cityList;
    private CustomList cityAdapter;
    private ArrayList<City> dataList;

    private FirebaseFirestore db;
    private CollectionReference citiesRef;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        citiesRef = db.collection("cities");

        initializeViews();
        setupAdapter();
        setupListeners();

        citiesRef.addSnapshotListener((value, error) -> {
            if (error != null) {
                Log.w("MainActivity", "Listen failed.", error);
                return;
            }

            dataList.clear();
            if (value != null) {
                for (QueryDocumentSnapshot doc : value) {
                    String cityName = doc.getString("name");
                    String provinceName = doc.getString("province");
                    if (cityName != null && provinceName != null) {
                        dataList.add(new City(cityName, provinceName));
                    }
                }
            }
            cityAdapter.notifyDataSetChanged();
        });
    }

    private void initializeViews() {
        cityList = findViewById(R.id.city_list);
        dataList = new ArrayList<>();
    }

    private void setupAdapter() {
        cityAdapter = new CustomList(this, dataList);
        cityList.setAdapter(cityAdapter);
    }

    private void setupListeners() {
        final FloatingActionButton addCityFab = findViewById(R.id.add_city_fab);
        addCityFab.setOnClickListener(v -> new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY"));

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            final City cityToEdit = dataList.get(position);
            AddCityFragment.newInstance(cityToEdit).show(getSupportFragmentManager(), "EDIT_CITY");
        });

        cityList.setOnItemLongClickListener((parent, view, position, id) -> {
            final City cityToDelete = dataList.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Delete City")
                    .setMessage("Are you sure you want to delete this city?")
                    .setPositiveButton("Delete", (dialog, which) -> citiesRef.document(cityToDelete.getCityName()).delete())
                    .setNegativeButton("Cancel", null)
                    .show();
            return true;
        });
    }

    @Override
    public void onOkPressed(@NonNull final City newCity) {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", newCity.getCityName());
        data.put("province", newCity.getProvinceName());
        citiesRef.document(newCity.getCityName()).set(data)
                .addOnSuccessListener(aVoid -> Log.d("MainActivity", "DocumentSnapshot successfully written!"))
                .addOnFailureListener(e -> Log.w("MainActivity", "Error writing document", e));
    }

    @Override
    public void onEditPressed(@NonNull final City city) {
        onOkPressed(city);
    }
}
