package com.syzible.occupie.Tenant.FindProperty.Results.HouseShareResults;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syzible.occupie.Common.Objects.HouseShare;
import com.syzible.occupie.MainActivity;
import com.syzible.occupie.R;
import com.syzible.occupie.Tenant.FindProperty.Common.ImageAdapter;
import com.syzible.occupie.Tenant.FindProperty.Listings.HouseShareListing.ViewHouseShareFragment;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ed on 30/10/2016
 */
public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {
    private List<HouseShare> houseShares = new ArrayList<>();

    PropertyAdapter(List<HouseShare> houseShares) {
        this.houseShares = houseShares;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_tile, parent, false);
        return new ViewHolder(view);
    }

    private void formatCard(final ViewHolder holder, HouseShare houseShare) {
        holder.bedroomCount.setText("1");
        holder.address.setText(houseShare.getAddress().getTileAddress());
        holder.rent.setText(String.format("€%s pm", houseShare.getListing().getRent()));

        ImageAdapter adapter = new ImageAdapter(holder.itemView.getContext(), houseShare.getImages());
        holder.viewPager.setAdapter(adapter);
        holder.viewPager.setOffscreenPageLimit(3);

        CirclePageIndicator indicator = holder.itemView.findViewById(R.id.indicator);
        indicator.setViewPager(holder.viewPager);

        holder.itemView.setOnClickListener(v -> {
            ViewHouseShareFragment fragment = ViewHouseShareFragment.getInstance(houseShare);
            MainActivity.setFragmentBackstack(((AppCompatActivity) holder.itemView.getContext()).getFragmentManager(), fragment);
        });
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        HouseShare property = houseShares.get(position);
        formatCard(holder, property);
    }

    @Override
    public int getItemCount() {
        return houseShares.size();
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

