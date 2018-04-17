package com.syzible.occupie.Tenant.Applications;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.syzible.occupie.Common.Objects.Application;
import com.syzible.occupie.R;
import com.syzible.occupie.Tenant.FindProperty.Common.DividerDecorator;

import java.util.List;

public class ApplicationsFragment extends Fragment implements ApplicationsView {

    private View view;
    private RecyclerView recyclerView;
    private ApplicationsPresenter presenter;
    private ProgressBar progressBar;

    public ApplicationsFragment() {
    }

    public static ApplicationsFragment getInstance() {
        return new ApplicationsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listing_applications, container, false);
        progressBar = view.findViewById(R.id.applications_progress_bar);
        setupRecyclerView(view);
        return view;
    }

    @Override
    public void onStart() {
        if (presenter == null)
            presenter = new ApplicationsPresenterImpl();

        presenter.attach(this);
        presenter.getApplications();

        super.onStart();
    }

    @Override
    public void onStop() {
        presenter.detach();
        super.onStop();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showApplications(List<Application> applications) {
        RecyclerView.Adapter adapter = new ApplicationAdapter(applications);
        recyclerView.setAdapter(adapter);
        hideProgressBar();
    }

    @Override
    public void showError(int statusCode, String message) {
        String error = String.format("%s: %s", statusCode, message);
        Snackbar.make(view, error, Snackbar.LENGTH_LONG).show();
        hideProgressBar();
    }

    private void setupRecyclerView(View view) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = view.findViewById(R.id.applications_holder);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerDecorator(getActivity(), 16));
    }
}
