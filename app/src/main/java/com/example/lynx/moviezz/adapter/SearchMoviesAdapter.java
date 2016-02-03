package com.example.lynx.moviezz.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.fragment.MovieDetailFragment;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.search_movie_by_title.MovieByTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 22.01.2016.
 */
public class SearchMoviesAdapter extends BaseAdapter {

    private Context mCtx;
    private List<MovieByTitle> data;

    public SearchMoviesAdapter(Context context) {
        mCtx = context;
        data = new ArrayList<>();
    }

    public void updateData(List<MovieByTitle> newData) {
        data = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        final View view = inflater.inflate(R.layout.list_item_suggestion, parent, false);
        TextView tvSuggestion_LIS = (TextView) view.findViewById(R.id.tvSuggestion_LIS);
        ImageView ivSuggestionsPoster_LIS = (ImageView) view.findViewById(R.id.ivSuggestionPoster_LIS);
        Glide.with(mCtx).load(Constants.BASE_SMALL_IMAGE_URL + data.get(position).poster_path).placeholder(R.drawable.placeholder_poster).into(ivSuggestionsPoster_LIS);
        tvSuggestion_LIS.setText(data.get(position).title);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
                Bundle movieBundle = new Bundle();
                movieBundle.putInt(Constants.EXTRA_MOVIE_ID, data.get(position).id);
                movieDetailFragment.setArguments(movieBundle);
                ((AppCompatActivity) mCtx).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_AM, movieDetailFragment).commit();

                InputMethodManager imm = (InputMethodManager)mCtx.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        return view;
    }
}
