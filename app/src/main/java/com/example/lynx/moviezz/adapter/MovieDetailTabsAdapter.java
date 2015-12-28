package com.example.lynx.moviezz.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.lynx.moviezz.fragment.MovieDetailCastFragment;
import com.example.lynx.moviezz.fragment.MovieDetailGalleryFragment;
import com.example.lynx.moviezz.fragment.MovieDetailInfoFragment;
import com.example.lynx.moviezz.fragment.MovieDetailTrailersFragment;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_movie_info_by_id.ResponseDetailMovieInfo;

/**
 * Created by Lynx on 14.12.2015.
 */
public class MovieDetailTabsAdapter extends FragmentPagerAdapter {

    private ResponseDetailMovieInfo sourceData;
    private MovieDetailInfoFragment movieDetailInfoFragment;
    private MovieDetailCastFragment movieDetailCastFragment;
    private MovieDetailGalleryFragment movieDetailGalleryFragment;
    private MovieDetailTrailersFragment movieDetailTrailersFragment;

    public MovieDetailTabsAdapter(FragmentManager fm, ResponseDetailMovieInfo data) {
        super(fm);
        sourceData = data;
        Bundle dataBundle = new Bundle();
        dataBundle.putSerializable(Constants.EXTRA_DATA, sourceData);

        movieDetailInfoFragment = new MovieDetailInfoFragment();
        movieDetailCastFragment = new MovieDetailCastFragment();
        movieDetailGalleryFragment = new MovieDetailGalleryFragment();
        movieDetailTrailersFragment = new MovieDetailTrailersFragment();

        movieDetailInfoFragment.setArguments(dataBundle);
        movieDetailCastFragment.setArguments(dataBundle);
        movieDetailGalleryFragment.setArguments(dataBundle);
        movieDetailTrailersFragment.setArguments(dataBundle);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return movieDetailInfoFragment;
            case 1:
                return movieDetailCastFragment;
            case 2:
                return movieDetailGalleryFragment;
            case 3:
                return movieDetailTrailersFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "INFO";
            case 1:
                return "CAST";
            case 2:
                return "GALLERY";
            case 3:
                return "TRAILERS";
        }
        return "";
    }
}
