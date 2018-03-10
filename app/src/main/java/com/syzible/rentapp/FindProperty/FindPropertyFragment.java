package com.syzible.rentapp.FindProperty;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syzible.rentapp.Common.Objects.Property;
import com.syzible.rentapp.R;

import java.util.List;

public class FindPropertyFragment extends Fragment implements FindPropertyView {

    private View view;
    private RecyclerView recyclerView;
    private FindPropertyPresenter findPropertyPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find_property, container, false);
        setupRecyclerView(view);
        return view;
    }

    @Override
    public void onResume() {
        if (findPropertyPresenter == null)
            findPropertyPresenter = new FindPropertyPresenterImpl();

        findPropertyPresenter.attach(this);
        findPropertyPresenter.getProperties();

        super.onResume();
    }

    @Override
    public void onPause() {
        findPropertyPresenter.detach();

        super.onPause();
    }

    @Override
    public void showProperties(List<Property> properties) {
        RecyclerView.Adapter adapter = new PropertyAdapter(properties);
        recyclerView.setAdapter(adapter);
    }

    private void setupRecyclerView(View view) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = view.findViewById(R.id.properties_holder);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerDecorator(getActivity(), 16));
    }
}
