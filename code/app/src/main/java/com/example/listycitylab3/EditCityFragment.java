package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

// used AI in this file to help with the code
public class EditCityFragment extends DialogFragment {

    private City city;
    private EditCityDialogListener listener;

    interface EditCityDialogListener {
        void editCity(City updatedCity);
    }

    public EditCityFragment(City city, EditCityDialogListener listener) {
        this.city = city;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable android.os.Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save Edit", (dialog, which) -> {
                    String updatedName = editCityName.getText().toString();
                    String updatedProvince = editProvinceName.getText().toString();
                    listener.editCity(new City(updatedName, updatedProvince));
                })
                .create();
    }
}