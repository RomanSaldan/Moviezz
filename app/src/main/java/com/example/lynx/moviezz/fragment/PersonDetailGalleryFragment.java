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
import com.example.lynx.moviezz.adapter.PersonGalleryAdapter;
import com.example.lynx.moviezz.api.TmdbApiManager;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_person_by_id.PersonTaggedImages;
import com.example.lynx.moviezz.model.get_person_by_id.ResponsePersonById;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Lynx on 28.12.2015.
 */
public class PersonDetailGalleryFragment extends Fragment {

    private ResponsePersonById data;
    private RecyclerView.Adapter galleryAdapter;
    private RecyclerView.LayoutManager lmGallery;

    //region ButterKnife binds
    @Bind(R.id.rvGallery_FPDG)
    protected RecyclerView rvGallery_FPDG;

    @OnClick(R.id.btnProfile_FPDG)
    protected void clickProfile(View v) {
        if(galleryAdapter == null || galleryAdapter.getItemCount() == 0 || galleryAdapter.getItemViewType(0) == Constants.LIST_ITEM_PERSON_GALLERY_IMAGE) return;
        galleryAdapter = new PersonGalleryAdapter(getActivity(), data.images.profiles);
        if(galleryAdapter == null || getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lmGallery = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
        } else {
            lmGallery = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        }
        rvGallery_FPDG.setLayoutManager(lmGallery);
        rvGallery_FPDG.setAdapter(galleryAdapter);
    }

    @OnClick(R.id.btnStills_FPDG)
    protected void clickStills(View v) {
        if(galleryAdapter == null || galleryAdapter.getItemCount() == 0 || galleryAdapter.getItemViewType(0) == Constants.LIST_ITEM_PERSON_GALLERY_STILL) return;
        galleryAdapter = new PersonGalleryAdapter(getActivity(), data.tagged_images);
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lmGallery = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        } else {
            lmGallery = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        }
        rvGallery_FPDG.setLayoutManager(lmGallery);
        rvGallery_FPDG.setAdapter(galleryAdapter);
    }
    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        data = (ResponsePersonById) getArguments().getSerializable(Constants.EXTRA_DATA);
        View rootView = inflater.inflate(R.layout.fragment_person_detail_gallery, container, false);
        ButterKnife.bind(this, rootView);
        for(int i = 1; i <= data.tagged_images.total_pages; i++) {
            final int ii = i;
            TmdbApiManager.getInstance().getTmdbApi().getPersonTaggedImages(data.id, i, new Callback<PersonTaggedImages>() {
                @Override
                public void success(PersonTaggedImages personTaggedImages, Response response) {
                    data.tagged_images.results.addAll(personTaggedImages.results);
                    if(ii == data.tagged_images.total_pages) {
                        galleryAdapter = new PersonGalleryAdapter(getActivity(), data.images.profiles);
                        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            lmGallery = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
                        } else {
                            lmGallery = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
                        }
                        rvGallery_FPDG.setLayoutManager(lmGallery);
                        rvGallery_FPDG.setAdapter(galleryAdapter);
                    }
                }
                @Override
                public void failure(RetrofitError error) {
                }
            });
        }
        return rootView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(galleryAdapter.getItemViewType(0) == Constants.LIST_ITEM_PERSON_GALLERY_IMAGE) {
                //TODO image item became landscape
                ((GridLayoutManager)lmGallery).setSpanCount(4);
            } else {
                //TODO image item became portrait
                ((GridLayoutManager)lmGallery).setSpanCount(3);
            }
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if(galleryAdapter.getItemViewType(0) == Constants.LIST_ITEM_PERSON_GALLERY_STILL) {
                //TODO still image became portrait
                ((GridLayoutManager)lmGallery).setSpanCount(2);
            } else {
                //TODO still image became landscape
                ((GridLayoutManager)lmGallery).setSpanCount(3);
            }
        }
    }
}
