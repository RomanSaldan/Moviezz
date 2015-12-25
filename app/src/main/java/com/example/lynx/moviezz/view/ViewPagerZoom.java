package com.example.lynx.moviezz.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Lynx on 23.12.2015.
 */
public class ViewPagerZoom extends ViewPager {

    public ViewPagerZoom(Context context) {
        super(context);
    }

    public ViewPagerZoom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
