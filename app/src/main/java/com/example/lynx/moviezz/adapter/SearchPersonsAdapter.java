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
import com.example.lynx.moviezz.fragment.PersonDetailFragment;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.search_person_by_name.PersonByName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 03.02.2016.
 */
public class SearchPersonsAdapter extends BaseAdapter {

    private Context mCtx;
    private List<PersonByName> mData;

    public SearchPersonsAdapter(Context mCtx) {
        this.mCtx = mCtx;
        mData = new ArrayList<>();
    }

    public void updateData(List<PersonByName> newData) {
        mData = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
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
        Glide.with(mCtx).load(Constants.BASE_SMALL_IMAGE_URL + mData.get(position).profile_path).placeholder(R.drawable.placeholder_portrait).into(ivSuggestionsPoster_LIS);
        tvSuggestion_LIS.setText(mData.get(position).name);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonDetailFragment personDetailFragment = new PersonDetailFragment();
                Bundle movieBundle = new Bundle();
                movieBundle.putInt(Constants.EXTRA_PERSON_ID, mData.get(position).id);
                personDetailFragment.setArguments(movieBundle);
                ((AppCompatActivity) mCtx).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_AM, personDetailFragment).commit();

                InputMethodManager imm = (InputMethodManager)mCtx.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        return view;
    }
}
