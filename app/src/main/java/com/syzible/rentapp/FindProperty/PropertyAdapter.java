package com.syzible.rentapp.FindProperty;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syzible.rentapp.Common.Objects.Property;
import com.syzible.rentapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ed on 30/10/2016
 */
public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {
    private List<Property> properties = new ArrayList<>();

    public PropertyAdapter(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_tile, parent, false);
        return new ViewHolder(view);
    }

    private void formatCard(final ViewHolder holder, Property property) {
        holder.propertyType.setText(property.getType());
        holder.bedroomCount.setText(property.getBedrooms() + " bedrooms");
        holder.bathroomCount.setText(property.getBathrooms() + " bathrooms");
        holder.address.setText(property.getAddress().getFullAddress());
        holder.rent.setText("€550.00 pm");
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Property property = properties.get(position);
        formatCard(holder, property);
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView propertyType, bedroomCount, bathroomCount, address, rent;

        ViewHolder(View itemView) {
            super(itemView);

            propertyType = itemView.findViewById(R.id.property_tile_type);
            bedroomCount = itemView.findViewById(R.id.property_tile_bedroom_count);
            bathroomCount = itemView.findViewById(R.id.property_tile_bathroom_count);
            address = itemView.findViewById(R.id.property_tile_address);
            rent = itemView.findViewById(R.id.property_tile_rent);
        }
    }
}

