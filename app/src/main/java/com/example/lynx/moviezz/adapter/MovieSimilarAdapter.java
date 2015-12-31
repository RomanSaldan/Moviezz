package com.example.lynx.moviezz.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.fragment.MovieDetailFragment;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.global.Logg;
import com.example.lynx.moviezz.model.find_movie_by_imdb_id.ShortMovieInfo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Lynx on 21.12.2015.
 */
public class MovieSimilarAdapter extends RecyclerView.Adapter<MovieSimilarAdapter.ViewHolder> {

    private List<ShortMovieInfo> data;
    private Context mCtx;

    public MovieSimilarAdapter(Context context, List<ShortMovieInfo> data) {
        this.data = data;
        Collections.sort(data, new Comparator<ShortMovieInfo>() {
            @Override
            public int compare(ShortMovieInfo lhs, ShortMovieInfo rhs) {
                return rhs.vote_count-lhs.vote_count;
            }
        });
        mCtx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_similar, parent, false);
        ViewHolder vh = new ViewHolder((CardView) view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(mCtx).load(Constants.BASE_SMALL_IMAGE_URL + data.get(position).poster_path).into(holder.ivSimilarPoster_LIS);
        holder.tvRating_LIS.setText(String.valueOf(data.get(position).vote_average) + " (" + data.get(position).vote_count + ")");
        holder.tvTitle_LIS.setText(data.get(position).title);
        LayerDrawable stars = (LayerDrawable) holder.rbSimilars_LIS.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(holder.ivSimilarPoster_LIS.getContext().getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO handle similar movie click
                Logg.d("im here!");
                MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.EXTRA_MOVIE_ID, data.get(position).id);
                movieDetailFragment.setArguments(bundle);
                ((AppCompatActivity) mCtx).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_AM, movieDetailFragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivSimilarPoster_LIS;
        public TextView tvRating_LIS;
        public TextView tvTitle_LIS;
        public RatingBar rbSimilars_LIS;
        public CardView rootView;

        public ViewHolder(CardView similarCard) {
            super(similarCard);
            rootView = similarCard;
            ivSimilarPoster_LIS = (ImageView) similarCard.findViewById(R.id.ivSimilarPoster_LIS);
            tvRating_LIS = (TextView) similarCard.findViewById(R.id.tvRating_LIS);
            tvTitle_LIS = (TextView) similarCard.findViewById(R.id.tvTitle_LIS);
            rbSimilars_LIS = (RatingBar) similarCard.findViewById(R.id.rbSimilars_LIS);
        }
    }
}
