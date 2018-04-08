package com.syzible.occupie.Tenant.FindProperty.Listings.RentalListing;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syzible.occupie.Common.Objects.Rental;
import com.syzible.occupie.R;

import java.util.ArrayList;
import java.util.List;

public class SimilarHomesAdapter extends RecyclerView.Adapter<SimilarHomesAdapter.ViewHolder>{
    private List<Rental> properties = new ArrayList<>();

    public SimilarHomesAdapter(List<Rental> properties) {
        this.properties = properties;
    }

    @Override
    public SimilarHomesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_tile, parent, false);
        return new SimilarHomesAdapter.ViewHolder(view);
    }

    private void formatCard(final SimilarHomesAdapter.ViewHolder holder, Rental property) {
        holder.address.setText(property.getAddress().getTileAddress());
        holder.rent.setText(String.format("€%s", property.getRent()));

        //ImageAdapter adapter = new ImageAdapter(holder.itemView.getContext(), property.getImages());
        //holder.viewPager.setAdapter(adapter);
        //holder.viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onBindViewHolder(final SimilarHomesAdapter.ViewHolder holder, int position) {
        Rental property = properties.get(position);
        formatCard(holder, property);
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewPager viewPager;
        TextView bedroomCount, address, rent;

        ViewHolder(View itemView) {
            super(itemView);

            viewPager = itemView.findViewById(R.id.property_image);
            rent = itemView.findViewById(R.id.property_tile_rent);
            bedroomCount = itemView.findViewById(R.id.property_tile_bedroom_count);
            address = itemView.findViewById(R.id.property_tile_address);
        }
    }
}
