package com.syzible.occupie.Tenant.Applications;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syzible.occupie.Common.Objects.Application;
import com.syzible.occupie.R;

import java.util.List;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ViewHolder> {
    private List<Application> applications;

    ApplicationAdapter(List<Application> applications) {
        this.applications = applications;
    }

    @Override
    public ApplicationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.application_tile, parent, false);
        return new ApplicationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ApplicationAdapter.ViewHolder holder, int position) {
        Application application = applications.get(position);
        holder.status.setText(application.getStatus());
        holder.userId.setText(application.getUserId());
        holder.listingId.setText(application.getListingId());
        holder.landlordId.setText(application.getLandlordId());
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView status, userId, landlordId, listingId;

        ViewHolder(View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.application_status);
            userId = itemView.findViewById(R.id.application_user_id);
            landlordId = itemView.findViewById(R.id.application_landlord_id);
            listingId = itemView.findViewById(R.id.application_listing_id);
        }
    }
}

