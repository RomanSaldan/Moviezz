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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.adapter.GalleryAdapter;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_movie_info_by_id.ResponseDetailMovieInfo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lynx on 14.12.2015.
 */
public class MovieDetailGalleryFragment extends Fragment {

    private ResponseDetailMovieInfo data;
    public RecyclerView.Adapter galleryAdapter;
    private RecyclerView.LayoutManager lmGallery;

    //region ButterKnife binds
    @Bind(R.id.rvGallery_FMDG)
    protected RecyclerView rvGallery_FMDG;

    @Bind(R.id.llContentGallery_FMDG)
    protected LinearLayout llContentGallery_FMDG;

    @Bind(R.id.tvGalleryError_FMDG)
    protected TextView tvCastError_FMDG;
    //endregion

    //region ButterKnife clicks
    @OnClick(R.id.btnPosters_FMDG)
    protected void clickPosters(View v) {
        galleryAdapter = new GalleryAdapter(getActivity(), data.images.posters, Constants.LIST_ITEM_GALLERY_POSTER);
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lmGallery = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
        } else {
            lmGallery = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        }
        rvGallery_FMDG.setLayoutManager(lmGallery);
        rvGallery_FMDG.setAdapter(galleryAdapter);
    }

    @OnClick(R.id.btnImages_FMDG)
    protected void clickImages(View v) {
        galleryAdapter = new GalleryAdapter(getActivity(), data.images.backdrops, Constants.LIST_ITEM_GALLERY_IMAGE);
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lmGallery = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        } else {
            lmGallery = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        }
        rvGallery_FMDG.setLayoutManager(lmGallery);
        rvGallery_FMDG.setAdapter(galleryAdapter);
    }
    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        data = (ResponseDetailMovieInfo) getArguments().getSerializable(Constants.EXTRA_DATA);
        View view = inflater.inflate(R.layout.fragment_movie_detail_gallery, container, false);
        ButterKnife.bind(this, view);
        if(data.images.backdrops.size()==0 && data.images.posters.size()==0) {
            llContentGallery_FMDG.setVisibility(View.GONE);
            tvCastError_FMDG.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        galleryAdapter = new GalleryAdapter(getActivity(), data.images.backdrops, Constants.LIST_ITEM_GALLERY_IMAGE);
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lmGallery = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        } else {
            lmGallery = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        }
        rvGallery_FMDG.setLayoutManager(lmGallery);
        rvGallery_FMDG.setAdapter(galleryAdapter);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                if(galleryAdapter.getItemViewType(0) == Constants.LIST_ITEM_GALLERY_IMAGE) {
                    ((GridLayoutManager) lmGallery).setSpanCount(3);
                } else {
                    ((GridLayoutManager) lmGallery).setSpanCount(4);
                }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            if(galleryAdapter.getItemViewType(0) == Constants.LIST_ITEM_GALLERY_IMAGE) {
                ((GridLayoutManager) lmGallery).setSpanCount(2);
            } else {
                ((GridLayoutManager) lmGallery).setSpanCount(3);
            }
        }
    }
}
