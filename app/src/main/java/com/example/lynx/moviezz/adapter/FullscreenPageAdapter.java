package com.example.lynx.moviezz.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lynx.moviezz.fragment.FullscreenPageFragment;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_movie_images_by_id.BaseImage;

import java.util.List;

/**
 * Created by Lynx on 23.12.2015.
 */
public class FullscreenPageAdapter extends FragmentStatePagerAdapter {

    private List<BaseImage> data;

    public FullscreenPageAdapter(FragmentManager fm, List<BaseImage> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.EXTRA_IMAGE, data.get(position));
        FullscreenPageFragment pageFragment = new FullscreenPageFragment();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public int getCount() {
        return data.size();
    }


}
