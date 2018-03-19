package com.syzible.rentapp.FindProperty.Listing;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syzible.rentapp.Common.Objects.Property;
import com.syzible.rentapp.FindProperty.Results.ImageAdapter;
import com.syzible.rentapp.FindProperty.Results.PropertyAdapter;
import com.syzible.rentapp.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class SimilarHomesAdapter extends RecyclerView.Adapter<SimilarHomesAdapter.ViewHolder>{
    private List<Property> properties = new ArrayList<>();

    public SimilarHomesAdapter(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public SimilarHomesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_tile, parent, false);
        return new SimilarHomesAdapter.ViewHolder(view);
    }

    private void formatCard(final SimilarHomesAdapter.ViewHolder holder, Property property) {
        holder.address.setText(property.getAddress().getFullAddress());

        String rent = String.format("€%s.00", property.getListing().getRent()) + " pm";
        holder.rent.setText(rent);

        //ImageAdapter adapter = new ImageAdapter(holder.itemView.getContext(), property.getImages());
        //holder.viewPager.setAdapter(adapter);
        //holder.viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onBindViewHolder(final SimilarHomesAdapter.ViewHolder holder, int position) {
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
