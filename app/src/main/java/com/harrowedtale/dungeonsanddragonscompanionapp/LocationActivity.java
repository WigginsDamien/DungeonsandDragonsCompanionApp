package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LocationActivity extends AppCompatActivity {

    private String[] imageUrls = new String[]{
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/altar.jpg?alt=media&token=c1ece9de-2660-421b-b174-428d479bfd9d",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/castle.jpg?alt=media&token=48b4f519-7e19-47c7-9ccd-fb7b345a7144",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/cave.jpg?alt=media&token=da6d3955-29f9-45c1-abca-06a81b4ec7d5",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/desert.jpg?alt=media&token=26c80a44-7266-41b3-b7da-9b61b3ba1daf",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/field.jpg?alt=media&token=33e96caa-b9aa-4579-8b99-5d36a6392fbb",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/forest.jpg?alt=media&token=a56cc906-8176-4ed3-a529-af8c662af698",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/forked_road.jpg?alt=media&token=40a3a5ba-cdf7-4915-ba3b-0c262b147dc6",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/keep.jpg?alt=media&token=ce37b2e9-e537-478d-8aaf-fb23b4c3ec50",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/mountain.jpg?alt=media&token=fcff7b28-5c02-4057-9d97-9ac00cc16130",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/port.jpg?alt=media&token=0a794ac2-463c-4301-8b9f-c16a7a7bd241",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/river.jpg?alt=media&token=132f7d68-6662-4d2f-abc8-8369c08e0588",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/ruins.jpg?alt=media&token=f00be1fa-05a4-4e74-86fe-be3884e91251",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/village.jpg?alt=media&token=b1a40ffa-5197-4b85-8709-df74fcf1eeb5",
            "https://firebasestorage.googleapis.com/v0/b/dungeons-and-dragons-comp-app.appspot.com/o/volcano.jpg?alt=media&token=8df87339-b1ef-4bfd-9b35-40f8712d2081",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ViewPager viewPager = findViewById(R.id.view_pager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);
        setTitle(adapter.getPageTitle(0));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {//updates page title based on current position in the array
                setTitle(adapter.getPageTitle(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //   viewPager.addOnPageChangeListener(ViewPager.OnPageChangeListener listener);
        int position = viewPager.getCurrentItem();
    }
}
