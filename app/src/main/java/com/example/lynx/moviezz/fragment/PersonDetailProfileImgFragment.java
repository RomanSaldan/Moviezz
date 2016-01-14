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
import com.example.lynx.moviezz.adapter.ProfileImgAdapter;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_person_by_id.ResponsePersonById;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lynx on 12.01.2016.
 */
public class PersonDetailProfileImgFragment extends Fragment {

    private ResponsePersonById data;
    private RecyclerView.Adapter profileImgAdapter;
    private RecyclerView.LayoutManager lmGallery;

    @Bind(R.id.rvProfiles_FPDIP)
    protected RecyclerView rvProfiles_FPDIP;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        data = (ResponsePersonById) getArguments().getSerializable(Constants.EXTRA_DATA);
        View rootView = inflater.inflate(R.layout.fragment_person_details_img_profiles, container, false);
        ButterKnife.bind(this, rootView);
        profileImgAdapter = new ProfileImgAdapter(getActivity(), data.images.profiles);
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lmGallery = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
        } else {
            lmGallery = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        }
        rvProfiles_FPDIP.setLayoutManager(lmGallery);
        rvProfiles_FPDIP.setAdapter(profileImgAdapter);
        return rootView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ((GridLayoutManager)lmGallery).setSpanCount(4);
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ((GridLayoutManager)lmGallery).setSpanCount(3);
        }
    }
}
