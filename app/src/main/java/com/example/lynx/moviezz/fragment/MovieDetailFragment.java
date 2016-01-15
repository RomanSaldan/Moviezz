package com.example.lynx.moviezz.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.activity.MainActivity;
import com.example.lynx.moviezz.adapter.MovieDetailTabsAdapter;
import com.example.lynx.moviezz.api.TmdbApiManager;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.global.Logg;
import com.example.lynx.moviezz.model.get_movie_info_by_id.ResponseDetailMovieInfo;

import java.net.HttpURLConnection;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Lynx on 15.12.2015.
 */
public class MovieDetailFragment extends Fragment {

    private int movieId;
    private ResponseDetailMovieInfo movieData;
    private MovieDetailTabsAdapter movieDetailTabsAdapter;

    //region ButterKnife binds
    @Nullable
    @Bind(R.id.ivCollapsed_FMD)
    protected ImageView collapsingImage;

    @Nullable
    @Bind(R.id.viewpager_FMD)
    protected ViewPager viewPager;

    @Nullable
    @Bind(R.id.toolbar_FMD)
    protected Toolbar toolbar_AMD;

    @Nullable
    @Bind(R.id.tabLayout_FMD)
    protected TabLayout tabLayout_AMD;

    @Nullable
    @Bind(R.id.collapsingToolbar_FMD)
    protected CollapsingToolbarLayout collapsingToolbarLayout;

    @Nullable
    @Bind(R.id.pbInital_FMD)
    protected ProgressBar pbInital_FMD;
    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_AMD);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");

        movieId = getArguments().getInt(Constants.EXTRA_MOVIE_ID);
        initMovieData(movieId);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_movie_detail_fragment, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchItem.getActionView();
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

    private final void initMovieData(int _movieId) {
        TmdbApiManager.getInstance().getTmdbApi().getMovieInfoByID(_movieId, new Callback<ResponseDetailMovieInfo>() {
            @Override
            public void success(ResponseDetailMovieInfo responseDetailMovieInfo, Response response) {
                if(response.getStatus() == HttpURLConnection.HTTP_OK) {
                    movieData = responseDetailMovieInfo;
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(movieData.title);
                    Glide.with(MovieDetailFragment.this)
                            .load(Constants.BASE_IMAGE_URL + movieData.backdrop_path)
                            .animate(R.animator.fade_out)
                            .thumbnail(0.7f)
                            .into(collapsingImage);
                    pbInital_FMD.setVisibility(View.GONE);
                    movieDetailTabsAdapter = new MovieDetailTabsAdapter(getChildFragmentManager(), responseDetailMovieInfo);
                    viewPager.setAdapter(movieDetailTabsAdapter);
                    tabLayout_AMD.setupWithViewPager(viewPager);
                }
                else {
                    Logg.d("fail to launch activity while fetching data, code: " + response.getStatus());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Logg.d("fail to launch activity while fetching data " + error.getMessage());
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        collapsingImage.setScaleType(ImageView.ScaleType.FIT_XY);
        super.onConfigurationChanged(newConfig);
    }
}
