package com.syzible.rentapp.FindProperty;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syzible.rentapp.Common.Objects.Property;
import com.syzible.rentapp.R;
import com.viewpagerindicator.CirclePageIndicator;

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
        holder.bedroomCount.setText(String.valueOf(property.getBedrooms()));
        holder.bathroomCount.setText(String.valueOf(property.getBathrooms()));
        holder.address.setText(property.getAddress().getFullAddress());
        holder.rent.setText("€550.00 pm");

        ImageAdapter adapter = new ImageAdapter(holder.itemView.getContext(), property.getImages());
        holder.viewPager.setAdapter(adapter);
        holder.viewPager.setOffscreenPageLimit(3);

        CirclePageIndicator indicator = holder.itemView.findViewById(R.id.indicator);
        indicator.setViewPager(holder.viewPager);
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
        ViewPager viewPager;
        TextView bedroomCount, bathroomCount, address, rent;

        ViewHolder(View itemView) {
            super(itemView);

            viewPager = itemView.findViewById(R.id.property_image);
            rent = itemView.findViewById(R.id.property_tile_rent);
            bedroomCount = itemView.findViewById(R.id.property_tile_bedroom_count);
            bathroomCount = itemView.findViewById(R.id.property_tile_bathroom_count);
            address = itemView.findViewById(R.id.property_tile_address);
        }
    }
}

