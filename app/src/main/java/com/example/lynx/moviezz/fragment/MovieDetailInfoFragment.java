package com.example.lynx.moviezz.fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.activity.FullscreenPosterActivity;
import com.example.lynx.moviezz.adapter.ReviewsAdapter;
import com.example.lynx.moviezz.adapter.SimilarsAdapter;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.global.Logg;
import com.example.lynx.moviezz.model.get_genres.Genre;
import com.example.lynx.moviezz.model.get_movie_info_by_id.MovieRelease;
import com.example.lynx.moviezz.model.get_movie_info_by_id.ProductionCountry;
import com.example.lynx.moviezz.model.get_movie_info_by_id.ResponseDetailMovieInfo;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lynx on 14.12.2015.
 */
public class MovieDetailInfoFragment extends Fragment {

    private ResponseDetailMovieInfo data;
    private RecyclerView rvReviews;
    private RecyclerView.Adapter reviewsAdapter;
    private RecyclerView.LayoutManager lmReviews;
    private RecyclerView.Adapter similarsAdapter;
    private RecyclerView.LayoutManager lmSimilars;

    //region view binds
    @Bind(R.id.ivPoster_FMDI)
    protected ImageView ivPoster_FMDI;

    @Bind(R.id.tvTitle_FMDI)
    protected TextView tvTitle_FMDI;

    @Bind(R.id.tvTagline_FMDI)
    protected TextView tvTagline_FMDI;

    @Bind(R.id.tvGenres_FMDI)
    protected TextView tvGenres_FMDI;

    @Bind(R.id.tvCountries_FMDI)
    protected TextView tvCountries_FMDI;

    @Bind(R.id.tvDuration_FMDI)
    protected TextView tvDuration_FMDI;

    @Bind(R.id.tvReleaseState_FMDI)
    protected TextView tvReleaseState_FMDI;

    @Bind(R.id.tvReleaseDate_FMDI)
    protected TextView tvReleaseDate_FMDI;

    @Bind(R.id.tvBudget_FMDI)
    protected TextView tvBudget_FMDI;

    @Bind(R.id.tvProfit_FMDI)
    protected TextView tvProfit_FMDI;

    @Bind(R.id.rbMovieRating_FMDI)
    protected RatingBar rbRating_FMDI;

    @Bind(R.id.tvRating_FMDI)
    protected TextView tvRating_FMDI;

    @Bind(R.id.tvOverview_FMDI)
    protected TextView tvOverview_FMDI;

    @Bind(R.id.nsvAllScroll_FMDI)
    protected NestedScrollView nsvAllScroll_FMDI;

    @Bind(R.id.tvCertification_FMDI)
    protected TextView tvCertification_FMDI;

    @Bind(R.id.ivOverviewIndicator_FMDI)
    protected ImageView ivOverviewIndicator_FMDI;

    @Bind(R.id.btnWeb_FMDI)
    protected Button btnWeb_FMDI;

    @Bind(R.id.btnImdb_FMDI)
    protected Button btnImdb_FMDI;

    @Bind(R.id.btnReview_FMDI)
    protected Button btnReview_FMDI;

    @Bind(R.id.rvSimilar_FMDI)
    protected RecyclerView rvSimilar_FMDI;

    @Bind(R.id.cvSimilars_FMDI)
    protected CardView cvSimilars_FMDI;
    //endregion

    //region view listeners
    @OnClick(R.id.cvOverview_FMDI)
    protected void clickOverview(View v) {
        if(tvOverview_FMDI.getVisibility() == View.VISIBLE) {
            tvOverview_FMDI.setVisibility(View.GONE);
            ivOverviewIndicator_FMDI.setImageResource(R.drawable.ic_expand_more_black_18dp);
        }
        else {
            tvOverview_FMDI.setVisibility(View.VISIBLE);
            ivOverviewIndicator_FMDI.setImageResource(R.drawable.ic_expand_less_black_18dp);
            nsvAllScroll_FMDI.post(new Runnable() {
                @Override
                public void run() {
                    nsvAllScroll_FMDI.scrollTo(0, tvOverview_FMDI.getBottom());
                }
            });
        }
    }

    @OnClick(R.id.btnWeb_FMDI)
    protected void clickWeb(View v) {
        String url = data.homepage;
        Intent webIntent = new Intent();
        webIntent.setData(Uri.parse(url));
        startActivity(webIntent);
    }

    @OnClick(R.id.btnImdb_FMDI)
    protected void clickImdb(View v) {
        String url = String.format("http://www.imdb.com/title/%s/", data.imdb_id).toString();
        Intent imdbIntent = new Intent();
        imdbIntent.setData(Uri.parse(url));
        startActivity(imdbIntent);
    }

    @OnClick(R.id.ivPoster_FMDI)
    protected void clickPoster(View v) {
        Intent intent = new Intent(getActivity(), FullscreenPosterActivity.class);
        intent.putExtra(Constants.EXTRA_FULLSCREEN_POSTER_PATH, data.poster_path);
        startActivity(intent);
    }

    @OnClick(R.id.btnReview_FMDI)
    protected void clickReview(View v) {
        //TODO here handle reviews list
        final AppCompatDialog dialogReview = new AppCompatDialog(getActivity(), android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_review, null);
        rvReviews = (RecyclerView) dialogView.findViewById(R.id.rvReviews_DR);
        lmReviews = new LinearLayoutManager(getActivity());
        rvReviews.setLayoutManager(lmReviews);
        reviewsAdapter = new ReviewsAdapter(data.reviews.results);
        rvReviews.setAdapter(reviewsAdapter);
        Toolbar dialogToolbar = (Toolbar) dialogView.findViewById(R.id.tbDialogReview_DR);
        dialogToolbar.setTitle(data.title);
        dialogToolbar.setSubtitle(data.tagline);
        dialogToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        dialogToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogReview.dismiss();
            }
        });

        dialogReview.setContentView(dialogView);
        dialogReview.setTitle(data.title);
        dialogReview.show();
    }
    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        data = (ResponseDetailMovieInfo) getArguments().getSerializable(Constants.EXTRA_DATA);
        View view = inflater.inflate(R.layout.fragment_movie_detail_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Glide.with(this).load(Constants.BASE_IMAGE_URL + data.poster_path).into(ivPoster_FMDI);
        tvTitle_FMDI.setText(data.title + " (" + getYear(data.release_date) + ")");
        tvTagline_FMDI.setText(data.tagline);
        tvGenres_FMDI.setText(getGenresStr());
        tvCountries_FMDI.setText(getCountriesStr());
        tvDuration_FMDI.setText(getPrettyDuration());
        tvReleaseState_FMDI.setText(data.status);
        tvReleaseDate_FMDI.setText(getFormattedDataStr(data.release_date));
        tvBudget_FMDI.setText(getPrettyAmount(data.budget));
        tvProfit_FMDI.setText(getPrettyAmount(data.revenue));

        LayerDrawable stars = (LayerDrawable) rbRating_FMDI.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        tvRating_FMDI.setText(String.format("%s (%d votes)", String.valueOf(data.vote_average), data.vote_count));

        tvOverview_FMDI.setText(data.overview);
        tvCertification_FMDI.setText(getMainCertificationStr());
        if(data.reviews.total_results>0) btnReview_FMDI.setVisibility(View.VISIBLE);
        if(!data.homepage.equalsIgnoreCase("")) btnWeb_FMDI.setVisibility(View.VISIBLE);

        if(data.similar.results.size()>0) cvSimilars_FMDI.setVisibility(View.VISIBLE);
        similarsAdapter = new SimilarsAdapter(getActivity(), data.similar.results);
        lmSimilars = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvSimilar_FMDI.setLayoutManager(lmSimilars);
        rvSimilar_FMDI.setAdapter(similarsAdapter);
    }

    //region custom methods
    private int getYear(String date) {
        String[] str = date.split("-");
        return Integer.valueOf(str[0]);
    }

    private String getGenresStr() {
        String result = "";
        for(Genre g : data.genres) {
            result += g.name + ", ";
        }
        if(result.length()>=2) result = result.substring(0, result.length()-2);
        return result;
    }

    private String getCountriesStr() {
        String result = "";
        for(ProductionCountry pd : data.production_countries) {
            result += pd.name + ", ";
        }
        if(result.length()>=2) result = result.substring(0, result.length()-2);
        return result;
    }

    private String getFormattedDataStr(String unformattedData) {
        String[] arr = unformattedData.split("-");
        return String.format("%s.%s.%s", arr[2], arr[1], arr[0]).toString();
    }

    private String getPrettyAmount(long amount) {
        NumberFormat canadaFrench = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
        canadaFrench.setMaximumFractionDigits(0);
        return canadaFrench.format(amount);
    }

    private String getMainCertificationStr() {
        String result = "";
        for(MovieRelease mr : data.releases.countries) {
            if(mr.iso_3166_1.equalsIgnoreCase("us")) result+= mr.certification + ", ";
        }
        if(result.length()>=2) result = result.substring(0, result.length()-2);
        return "USA: " + result;
    }

    private String getPrettyDuration() {
        String h = (data.runtime/60) > 0 ? data.runtime/60 + "hr " : "";
        String m = (data.runtime%60) > 0 ? data.runtime%60 + "min " : "";
        return h + m + " (" + data.runtime + "min)";
    }
    //endregion

}
