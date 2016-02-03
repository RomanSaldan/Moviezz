package com.example.lynx.moviezz.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.adapter.MovieDetailTabsAdapter;
import com.example.lynx.moviezz.adapter.SearchMoviesAdapter;
import com.example.lynx.moviezz.api.TmdbApiManager;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.global.Logg;
import com.example.lynx.moviezz.model.get_movie_info_by_id.ResponseDetailMovieInfo;
import com.example.lynx.moviezz.model.search_movie_by_title.MovieByTitle;
import com.example.lynx.moviezz.model.search_movie_by_title.ResponseSearchMovieByTitle;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

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
    private SearchMoviesAdapter searchMoviesAdapter;

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

    @Nullable
    @Bind(R.id.search_view)
    protected MaterialSearchView search_view;
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

        searchMoviesAdapter = new SearchMoviesAdapter(getActivity());
        search_view.setAdapter(searchMoviesAdapter);
        assert search_view != null;
        search_view.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                TmdbApiManager.getInstance().getTmdbApi().searchMovieByTitle(query, 1, new Callback<ResponseSearchMovieByTitle>() {
                    @Override
                    public void success(ResponseSearchMovieByTitle responseSearchMovieByTitle, Response response) {
                        if(responseSearchMovieByTitle.results.size() > 0) {
                            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
                            Bundle movieBundle = new Bundle();
                            movieBundle.putInt(Constants.EXTRA_MOVIE_ID, responseSearchMovieByTitle.results.get(0).id);
                            movieDetailFragment.setArguments(movieBundle);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_AM, movieDetailFragment).commit();
                        } else {
                            Toast.makeText(getActivity(), "No result found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                if(newText.length() >= 4) {
                    TmdbApiManager.getInstance().getTmdbApi().searchMovieByTitle(newText, 1, new Callback<ResponseSearchMovieByTitle>() {
                        @Override
                        public void success(ResponseSearchMovieByTitle responseSearchMovieByTitle, Response response) {
                            List<MovieByTitle> suggestions = new ArrayList<MovieByTitle>();
                            if(responseSearchMovieByTitle.results.size() <= 4) {
                                suggestions = responseSearchMovieByTitle.results;
                            } else {
                                for(int i = 0; i < 4; i++) {
                                    suggestions.add(responseSearchMovieByTitle.results.get(i));
                                }
                            }
                            searchMoviesAdapter.updateData(suggestions);
                            search_view.showSuggestions();
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                } else searchMoviesAdapter.updateData(new ArrayList<MovieByTitle>());
                return false;
            }
        });
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_movie_detail_fragment, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        search_view.setMenuItem(searchItem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
            case R.id.action_search:

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
