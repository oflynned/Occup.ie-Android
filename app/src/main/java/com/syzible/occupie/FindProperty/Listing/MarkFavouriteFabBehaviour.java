package com.syzible.occupie.FindProperty.Listing;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

public class MarkFavouriteFabBehaviour extends CoordinatorLayout.Behavior<FloatingActionButton> {
    public MarkFavouriteFabBehaviour(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                                       final View directTargetChild, final View target, final int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout,
                               final FloatingActionButton child,
                               final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            child.hide();
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            child.show();
        }
    }
}