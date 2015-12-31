package com.example.lynx.moviezz.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.adapter.MovieCastAdapter;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_movie_info_by_id.ResponseDetailMovieInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lynx on 14.12.2015.
 */
public class MovieDetailCastFragment extends Fragment {

    private ResponseDetailMovieInfo data;
    private RecyclerView.Adapter castAdapter;
    private RecyclerView.LayoutManager lmCast;

    @Bind(R.id.rvCast_FMDC)
    protected RecyclerView rvCast_FMDC;

    @Bind(R.id.tvCastError_FMDC)
    protected TextView tvCastError_FMDC;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        data = (ResponseDetailMovieInfo) getArguments().getSerializable(Constants.EXTRA_DATA);
        View view = inflater.inflate(R.layout.fragment_movie_detail_cast, container, false);
        ButterKnife.bind(this, view);
        if(data.casts.crew.size()==0 && data.casts.cast.size()==0) {
            rvCast_FMDC.setVisibility(View.GONE);
            tvCastError_FMDC.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        castAdapter = new MovieCastAdapter(getActivity(), data.casts);
        lmCast = new LinearLayoutManager(getActivity());
        rvCast_FMDC.setLayoutManager(lmCast);
        rvCast_FMDC.setAdapter(castAdapter);
    }
}
