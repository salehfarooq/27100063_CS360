package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    public interface OnFragmentInteractionListener {
        void onOkPressed(@NonNull final City newCity);
        void onEditPressed(@NonNull final City city);
    }

    private OnFragmentInteractionListener listener;
    private EditText cityNameEditText;
    private EditText provinceNameEditText;

    public static AddCityFragment newInstance(@NonNull final City city) {
        final AddCityFragment fragment = new AddCityFragment();
        final Bundle args = new Bundle();
        args.putSerializable("city", city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull final Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        final View view = LayoutInflater.from(requireActivity()).inflate(R.layout.fragment_add_city, null);
        initializeViews(view);

        final AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        setupDialog(builder);

        return builder
                .setView(view)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", (dialog, which) -> handleSubmission())
                .create();
    }

    private void initializeViews(@NonNull final View view) {
        cityNameEditText = view.findViewById(R.id.city_name_edit_text);
        provinceNameEditText = view.findViewById(R.id.province_name_edit_text);
    }

    private void setupDialog(@NonNull final AlertDialog.Builder builder) {
        final Bundle args = getArguments();
        if (args != null) {
            builder.setTitle("Edit City");
            final City existingCity = (City) args.getSerializable("city");
            if (existingCity != null) {
                cityNameEditText.setText(existingCity.getCityName());
                provinceNameEditText.setText(existingCity.getProvinceName());
            }
        } else {
            builder.setTitle("Add City");
        }
    }

    private void handleSubmission() {
        final String cityName = cityNameEditText.getText().toString().trim();
        final String provinceName = provinceNameEditText.getText().toString().trim();

        if (TextUtils.isEmpty(cityName) || TextUtils.isEmpty(provinceName)) {
            return;
        }

        final Bundle args = getArguments();
        if (args != null) {
            final City existingCity = (City) args.getSerializable("city");
            if (existingCity != null) {
                existingCity.setCityName(cityName);
                existingCity.setProvinceName(provinceName);
                listener.onEditPressed(existingCity);
            }
        } else {
            listener.onOkPressed(new City(cityName, provinceName));
        }
    }
}
