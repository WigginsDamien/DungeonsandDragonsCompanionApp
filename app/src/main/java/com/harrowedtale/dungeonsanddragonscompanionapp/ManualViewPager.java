package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class ManualViewPager extends ViewPager {

    private boolean touchEnabled;

    public ManualViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.touchEnabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.touchEnabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.touchEnabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean executeKeyEvent(KeyEvent event) {
        return touchEnabled && super.executeKeyEvent(event);
    }

    public void setTouchEnabled(boolean enabled) {
        this.touchEnabled = enabled;
    }
}
