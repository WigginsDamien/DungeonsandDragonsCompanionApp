package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;



public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private String[] imageUrls;
    private String[] titles = {
        "Altar", "Castle", "Cave", "Desert", "Field", "Forest", "Forked Road", "Keep",
            "Mountain", "Port", "River", "Ruins", "Village", "Volcano"
    };

    ViewPagerAdapter(Context context, String[] imageUrls){
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        Picasso.get().load(imageUrls[position]).fit().centerCrop().into(imageView);
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
