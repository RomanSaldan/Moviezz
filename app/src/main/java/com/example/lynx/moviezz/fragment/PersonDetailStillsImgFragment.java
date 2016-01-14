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
import com.example.lynx.moviezz.adapter.StillsImgAdapter;
import com.example.lynx.moviezz.api.TmdbApiManager;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_person_by_id.PersonTaggedImages;
import com.example.lynx.moviezz.model.get_person_by_id.ResponsePersonById;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Lynx on 12.01.2016.
 */
public class PersonDetailStillsImgFragment extends Fragment {

    private ResponsePersonById data;
    private RecyclerView.Adapter stillsImgAdapter;
    private RecyclerView.LayoutManager lmGallery;

    @Bind(R.id.rvPersonStills_FPDIS)
    protected RecyclerView rvPersonStills_FPDIS;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = (ResponsePersonById) getArguments().getSerializable(Constants.EXTRA_DATA);
        stillsImgAdapter = new StillsImgAdapter(getActivity(), data.tagged_images);
        if(data.tagged_images.total_pages > 1) {
            for(int i = 2; i <= data.tagged_images.total_pages; i++) {
                TmdbApiManager.getInstance().getTmdbApi().getPersonTaggedImages(data.id, i, new Callback<PersonTaggedImages>() {
                    @Override
                    public void success(PersonTaggedImages personTaggedImages, Response response) {
                        PersonTaggedImages filtered = ((StillsImgAdapter)stillsImgAdapter).filterStillsFromPosters(personTaggedImages);
                        data.tagged_images.results.addAll(filtered.results);
                        stillsImgAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                    }
                });
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_person_details_img_stills, container, false);
        ButterKnife.bind(this, rootView);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lmGallery = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        } else {
            lmGallery = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        }
        rvPersonStills_FPDIS.setLayoutManager(lmGallery);
        rvPersonStills_FPDIS.setAdapter(stillsImgAdapter);
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
