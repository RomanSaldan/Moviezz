package com.example.lynx.moviezz.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.adapter.TrailersAdapter;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_movie_info_by_id.ResponseDetailMovieInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lynx on 14.12.2015.
 */
public class MovieDetailTrailersFragment extends Fragment {

    private ResponseDetailMovieInfo data;
    private RecyclerView.Adapter trailersAdapter;
    private RecyclerView.LayoutManager lmTrailers;

    @Bind(R.id.rvTrailers_FMDT)
    protected RecyclerView rvTrailers_FMDT;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        data = (ResponseDetailMovieInfo) getArguments().getSerializable(Constants.EXTRA_DATA);
        View view = inflater.inflate(R.layout.fragment_movie_detail_trailers, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        trailersAdapter = new TrailersAdapter(data.trailers);
        lmTrailers = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvTrailers_FMDT.setAdapter(trailersAdapter);
        rvTrailers_FMDT.setLayoutManager(lmTrailers);
    }
}
