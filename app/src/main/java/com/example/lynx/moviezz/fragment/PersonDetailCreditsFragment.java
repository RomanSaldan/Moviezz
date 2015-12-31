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
import com.example.lynx.moviezz.adapter.PersonCreditsAdapter;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_person_by_id.ResponsePersonById;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lynx on 28.12.2015.
 */
public class PersonDetailCreditsFragment extends Fragment {

    private ResponsePersonById data;
    private RecyclerView.Adapter creditsAdapter;
    private RecyclerView.LayoutManager lmCredits;

    @Bind(R.id.rvCredits_FPDC)
    protected RecyclerView rvCredits_FPDC;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        data = (ResponsePersonById) getArguments().getSerializable(Constants.EXTRA_DATA);
        View rootView = inflater.inflate(R.layout.fragment_person_detail_credits, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        creditsAdapter = new PersonCreditsAdapter(getActivity(), data.movie_credits);
        lmCredits = new LinearLayoutManager(getActivity());
        rvCredits_FPDC.setLayoutManager(lmCredits);
        rvCredits_FPDC.setAdapter(creditsAdapter);
    }
}
