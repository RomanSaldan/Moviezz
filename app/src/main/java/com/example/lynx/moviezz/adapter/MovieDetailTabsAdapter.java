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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lynx on 14.12.2015.
 */
public class MovieDetailTabsAdapter extends FragmentPagerAdapter {

    private ResponseDetailMovieInfo sourceData;
    private MovieDetailInfoFragment movieDetailInfoFragment;
    private MovieDetailCastFragment movieDetailCastFragment;
    private MovieDetailGalleryFragment movieDetailGalleryFragment;
    private MovieDetailTrailersFragment movieDetailTrailersFragment;

    private Map<Integer,Fragment> mapTabs = new HashMap<>();

    public MovieDetailTabsAdapter(FragmentManager fm, ResponseDetailMovieInfo data) {
        super(fm);
        sourceData = data;
        Bundle dataBundle = new Bundle();
        dataBundle.putSerializable(Constants.EXTRA_DATA, sourceData);

        movieDetailInfoFragment = new MovieDetailInfoFragment();
        movieDetailInfoFragment.setArguments(dataBundle);
        mapTabs.put(0, movieDetailInfoFragment);

        if(data.casts.cast.size() > 0 && data.casts.crew.size() > 0) {
            movieDetailCastFragment = new MovieDetailCastFragment();
            movieDetailCastFragment.setArguments(dataBundle);
            mapTabs.put(1, movieDetailCastFragment);
        }
        if(data.images.backdrops.size() > 0 | data.images.posters.size() > 1) {
            movieDetailGalleryFragment = new MovieDetailGalleryFragment();
            movieDetailGalleryFragment.setArguments(dataBundle);
            mapTabs.put(mapTabs.size(), movieDetailGalleryFragment);
        }
        if(data.trailers.youtube.size() > 0) {
            movieDetailTrailersFragment = new MovieDetailTrailersFragment();
            movieDetailTrailersFragment.setArguments(dataBundle);
            mapTabs.put(mapTabs.size(), movieDetailTrailersFragment);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mapTabs.get(position);
    }

    @Override
    public int getCount() {
        return mapTabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Fragment fragment = mapTabs.get(position);
        if (fragment instanceof MovieDetailInfoFragment) return "INFO";
        else if (fragment instanceof MovieDetailCastFragment) return "CAST";
        else if (fragment instanceof MovieDetailGalleryFragment) return "GALLERY";
        else if (fragment instanceof MovieDetailTrailersFragment) return "TRAILERS";
        return "";
    }
}
