package com.syzible.occupie.FindProperty.Common;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.syzible.occupie.R;

import java.util.List;

public class ImageAdapter extends PagerAdapter {
    private Context context;
    private List<String> imageUrls;
    private LayoutInflater inflater;

    public ImageAdapter(Context context, List<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.property_image, container, false);
        ImageView imageView = imageLayout.findViewById(R.id.property_image_holder);
        Picasso.with(context).load(imageUrls.get(position)).into(imageView);

        container.addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}