package com.example.listycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<City> {

    private static class ViewHolder {
        final TextView cityName;
        final TextView provinceName;

        ViewHolder(@NonNull final View convertView) {
            this.cityName = convertView.findViewById(R.id.city_text);
            this.provinceName = convertView.findViewById(R.id.province_text);
        }
    }

    public CustomList(@NonNull final Context context, @NonNull final ArrayList<City> cities) {
        super(context, 0, cities);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_content, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final City city = getItem(position);

        if (city != null) {
            holder.cityName.setText(city.getCityName());
            holder.provinceName.setText(city.getProvinceName());
        }

        return convertView;
    }
}
