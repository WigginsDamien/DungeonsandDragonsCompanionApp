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

    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return("Altar");
            case 1:
                return("Castle with Cathedral");
            case 2:
                return("Lit Cave");
            case 3:
                return("Desert");
            case 4:
                return("Wheat field");
            case 5:
                return("Forest");
            case 6:
                return("Path with field");
            case 7:
                return("Castle with Rook");
            case 8:
                return("Mountain");
            case 9:
                return("Castle wall and Port");
            case 10:
                return("River in a Forest");
            case 11:
                return("Ruins");
            case 12:
                return("Village");
            case 13:
                return("Volcano");
            default:
                return("Locations");
        }
    }

    public Context getContext() {
        return context;
    }
}
