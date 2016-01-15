package com.example.lynx.moviezz.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.adapter.MovieImagesAdapter;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_movie_info_by_id.ResponseDetailMovieInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lynx on 14.01.2016.
 */
public class MovieDetailImagesFragment extends Fragment {

    private ResponseDetailMovieInfo data;
    private RecyclerView.Adapter movieImagesAdapter;
    private RecyclerView.LayoutManager lmGallery;

    @Bind(R.id.rvMovieImages_FMDI)
    protected RecyclerView rvMovieImages_FMDI;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        data = (ResponseDetailMovieInfo) getArguments().getSerializable(Constants.EXTRA_DATA);
        View rootView = inflater.inflate(R.layout.fragment_movie_detail_images, container, false);
        ButterKnife.bind(this, rootView);
        movieImagesAdapter = new MovieImagesAdapter(getActivity(), data.images.backdrops);
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lmGallery = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        } else {
            lmGallery = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        }
        rvMovieImages_FMDI.setLayoutManager(lmGallery);
        rvMovieImages_FMDI.setAdapter(movieImagesAdapter);
        return rootView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ((GridLayoutManager)lmGallery).setSpanCount(3);
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ((GridLayoutManager)lmGallery).setSpanCount(2);
        }
    }
}
