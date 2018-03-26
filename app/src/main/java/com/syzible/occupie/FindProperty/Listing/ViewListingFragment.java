package com.syzible.occupie.FindProperty.Listing;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syzible.occupie.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

public class ViewListingFragment extends Fragment implements ViewListingView {

    private ViewListingPresenter presenter;
    private FloatingActionButton favouriteFab;
    private boolean isFavourite = false;

    private DiscreteScrollView similarHomesCarousel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listing_rental, container, false);

        favouriteFab = view.findViewById(R.id.view_listing_favourite_fab);
        favouriteFab.setOnClickListener((v) -> onFavouriteClick());

        similarHomesCarousel = view.findViewById(R.id.property_listing_similar_homes);
        // TODO implement interactor for similar homes
        // TODO also move to presenter
        // similarHomesCarousel.setAdapter(new SimilarHomesAdapter());

        return view;
    }

    @Override
    public void onResume() {
        if (presenter == null)
            presenter = new ViewListingPresenterImpl();

        presenter.attach(this);
        super.onResume();
    }

    @Override
    public void onFavouriteClick() {
        Log.d(getClass().getSimpleName(), String.valueOf(isFavourite));
        isFavourite = !isFavourite;
        int icon = isFavourite ? R.drawable.heart_filled : R.drawable.heart_outline;
        favouriteFab.setImageDrawable(ContextCompat.getDrawable(getContext(), icon));
    }
}
