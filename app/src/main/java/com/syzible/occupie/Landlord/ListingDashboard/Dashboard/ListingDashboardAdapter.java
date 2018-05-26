package com.syzible.occupie.Landlord.ListingDashboard.Dashboard;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syzible.occupie.Common.Objects.Property;
import com.syzible.occupie.R;

import java.util.List;

public class ListingDashboardAdapter extends RecyclerView.Adapter<ListingDashboardAdapter.ViewHolder> {
    private List<Property> properties;

    ListingDashboardAdapter(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_listing_tile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Property property = properties.get(position);
        holder.street.setText(property.getAddress().getQuickAddress());
        holder.area.setText(property.getAddress().getArea());
        holder.listingType.setText(property.getFormattedType().toUpperCase());

        int imageIndex = position % property.getImages().size();
        Picasso.with(holder.itemView.getContext())
                .load(property.getImages().get(imageIndex))
                .fit()
                .centerCrop()
                .into(holder.thumbnail);

        if (!(property.getListing().getStatus().equals("active"))) {
            holder.thumbnail.setColorFilter(Color.argb(150,200,200,200));
        } else {
            holder.thumbnail.clearColorFilter();
        }
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView street, area, daysLeft, listingType;
        ImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            street = itemView.findViewById(R.id.street);
            area = itemView.findViewById(R.id.area);
            daysLeft = itemView.findViewById(R.id.days_left);
            thumbnail = itemView.findViewById(R.id.property_thumbnail);
            listingType = itemView.findViewById(R.id.listing_type);
        }
    }
}
