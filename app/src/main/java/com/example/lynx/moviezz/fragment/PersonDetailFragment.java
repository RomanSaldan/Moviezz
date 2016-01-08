package com.example.lynx.moviezz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.adapter.PersonDetailsTabsAdapter;
import com.example.lynx.moviezz.api.TmdbApiManager;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.global.Logg;
import com.example.lynx.moviezz.model.get_person_by_id.ResponsePersonById;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Lynx on 28.12.2015.
 */
public class PersonDetailFragment extends Fragment {

    private int personId;
    private ResponsePersonById personData;
    private PersonDetailsTabsAdapter detailsTabsAdapter;

    //region ButterKnife binds
    @Bind(R.id.appbar_FPD)
    protected AppBarLayout appbar_FPD;

    @Bind(R.id.toolbar_FPD)
    protected Toolbar toolbar_FPD;

    @Bind(R.id.tabLayout_FPD)
    protected TabLayout tabLayout_FPD;

    @Bind(R.id.vpPerson_FPD)
    protected ViewPager vpPerson_FPD;

    @Bind(R.id.pbLoadingPersonInfo_FPD)
    protected ProgressBar pbLoadingPersonInfo_FPD;

    @Bind(R.id.ivCirclePerson_FPD)
    protected CircleImageView ivCirclePerson_FPD;

    @Bind(R.id.tvPersonTitle_FPD)
    protected TextView tvPersonTitle_FPD;
    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_person_details, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_FPD);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        personId = getArguments().getInt(Constants.EXTRA_PERSON_ID);
        initPersonData(personId);
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
        }
        return false;
    }

    private void initPersonData(int id) {
        TmdbApiManager.getInstance().getTmdbApi().getPersonByID(id, new Callback<ResponsePersonById>() {
            @Override
            public void success(ResponsePersonById responsePersonById, Response response) {
                personData = responsePersonById;
                Glide.with(getActivity()).load(Constants.BASE_SMALL_IMAGE_URL + personData.profile_path).into(ivCirclePerson_FPD);
                pbLoadingPersonInfo_FPD.setVisibility(View.GONE);
                tvPersonTitle_FPD.setText(personData.name);
                detailsTabsAdapter = new PersonDetailsTabsAdapter(getChildFragmentManager(), personData);
                vpPerson_FPD.setAdapter(detailsTabsAdapter);
                tabLayout_FPD.setupWithViewPager(vpPerson_FPD);
            }

            @Override
            public void failure(RetrofitError error) {
                Logg.d("Error while fetching person data " + error.getCause());
            }
        });
    }
}
