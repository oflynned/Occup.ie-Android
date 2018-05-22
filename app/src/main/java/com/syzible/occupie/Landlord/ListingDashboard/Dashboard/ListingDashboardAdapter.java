package com.syzible.occupie.Landlord.ListingDashboard.Dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syzible.occupie.Common.Objects.Rental;
import com.syzible.occupie.R;

import java.util.List;

public class ListingDashboardAdapter extends RecyclerView.Adapter<ListingDashboardAdapter.ViewHolder> {
    private List<Rental> rentals;

    public ListingDashboardAdapter(List<Rental> rentals) {
        this.rentals = rentals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_listing_tile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rental rental = rentals.get(position);
        holder.street.setText(rental.getAddress().getQuickAddress());
        holder.area.setText(rental.getAddress().getArea());

        int imageIndex = (int) Math.floor(Math.random() * rental.getImages().size());
        Picasso.with(holder.itemView.getContext())
                .load(rental.getImages().get(imageIndex))
                .fit()
                .centerCrop()
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return rentals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView street, area, daysLeft;
        ImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            street = itemView.findViewById(R.id.street);
            area = itemView.findViewById(R.id.area);
            daysLeft = itemView.findViewById(R.id.days_left);
            thumbnail = itemView.findViewById(R.id.property_thumbnail);
        }
    }
}
