package com.example.lynx.moviezz.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.fragment.MovieDetailFragment;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_person_by_id.MovieCredits;
import com.example.lynx.moviezz.model.get_person_by_id.PersonCreditsCast;
import com.example.lynx.moviezz.model.get_person_by_id.PersonCreditsCrew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lynx on 29.12.2015.
 */
public class PersonCreditsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mCtx;
    private MovieCredits data;
    private ArrayList<Object> dataList;

    public PersonCreditsAdapter(Context mCtx, MovieCredits _data) {
        this.mCtx = mCtx;
        dataList = new ArrayList<>();
        if(_data.cast.size() > 0) {
            Collections.sort(_data.cast, new Comparator<PersonCreditsCast>() {
                @Override
                public int compare(PersonCreditsCast lhs, PersonCreditsCast rhs) {
                    return getYear(rhs.release_date) - getYear(lhs.release_date);
                }
            });
            dataList.add("Cast");
            transformCastData(_data.cast);
            for(PersonCreditsCast pcc : _data.cast) {
                dataList.add(pcc);
            }
        }
        if(_data.crew.size() > 0) {
            Collections.sort(_data.crew, new Comparator<PersonCreditsCrew>() {
                @Override
                public int compare(PersonCreditsCrew lhs, PersonCreditsCrew rhs) {
                    return getYear(rhs.release_date) - getYear(lhs.release_date);
                }
            });
            dataList.add("Crew");
            transformCrewData(_data.crew);
            for(PersonCreditsCrew pcc : _data.crew) {
                dataList.add(pcc);
            }
        }
        this.data = _data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case Constants.LIST_ITEM_HEADER:
                View creditHeaderView = layoutInflater.inflate(R.layout.list_item_peson_header, parent, false);
                return new CreditsHeaderVH(creditHeaderView);
            case Constants.LIST_ITEM_ACTOR:
                View creditCastView = layoutInflater.inflate(R.layout.list_item_person_cast, parent, false);
                return new CreditsCastVH(creditCastView);
            case Constants.LIST_ITEM_CREW:
                View creditCrewView = layoutInflater.inflate(R.layout.list_item_person_crew, parent, false);
                return new CreditsCrewVH(creditCrewView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object o = dataList.get(position);
        if(o instanceof String) {
            CreditsHeaderVH vh = (CreditsHeaderVH) holder;
            String str = (String) o;
            vh.tvPersonHeader_LPH.setText(str);
        } else if(o instanceof PersonCreditsCast) {
            CreditsCastVH vh = (CreditsCastVH) holder;
            final PersonCreditsCast infoCast = (PersonCreditsCast) o;
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startMovieScreen(infoCast.id);
                }
            });
            Glide.with(mCtx).load(Constants.BASE_SMALL_IMAGE_URL + infoCast.poster_path).placeholder(R.drawable.placeholder_poster).into(vh.ivPoster_LIPCast);
            vh.tvTitle_LIPCast.setText(String.format("%s (%d)", infoCast.title, getYear(infoCast.release_date)));
            vh.tvCharacter_LIPCast.setText(infoCast.character);

        } else {
            CreditsCrewVH vh = (CreditsCrewVH) holder;
            final PersonCreditsCrew infoCrew = (PersonCreditsCrew) o;
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startMovieScreen(infoCrew.id);
                }
            });
            Glide.with(mCtx).load(Constants.BASE_SMALL_IMAGE_URL + infoCrew.poster_path).placeholder(R.drawable.placeholder_poster).into(vh.ivPoster_LIPCrew);
            vh.tvTitle_LIPCrew.setText(String.format("%s (%d)", infoCrew.title, getYear(infoCrew.release_date)));
            vh.tvJob_LIPCrew.setText(infoCrew.job);
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(dataList.get(position) instanceof String) return Constants.LIST_ITEM_HEADER;
        else if(dataList.get(position) instanceof PersonCreditsCast) return Constants.LIST_ITEM_ACTOR;
        else return Constants.LIST_ITEM_CREW;
    }

    public static class CreditsHeaderVH extends RecyclerView.ViewHolder {

        @Bind(R.id.tvPersonHeader_LPH)
        protected TextView tvPersonHeader_LPH;

        public CreditsHeaderVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class CreditsCastVH extends RecyclerView.ViewHolder {

        @Bind(R.id.ivPoster_LIPCast)
        protected ImageView ivPoster_LIPCast;

        @Bind(R.id.tvTitle_LIPCast)
        protected TextView tvTitle_LIPCast;

        @Bind(R.id.tvCharacter_LIPCast)
        protected TextView tvCharacter_LIPCast;

        public CreditsCastVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class CreditsCrewVH extends RecyclerView.ViewHolder {

        @Bind(R.id.ivPoster_LIPCrew)
        protected ImageView ivPoster_LIPCrew;

        @Bind(R.id.tvTitle_LIPCrew)
        protected TextView tvTitle_LIPCrew;

        @Bind(R.id.tvJob_LIPCrew)
        protected TextView tvJob_LIPCrew;

        public CreditsCrewVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //get get list of distinct data items (similar id item combined by character)
    private void transformCrewData(List<PersonCreditsCrew> _list) {
        ArrayList<PersonCreditsCrew> toDel = new ArrayList<>();
        for(int i = 0; i < _list.size() - 1; i++) {
            for(int k = i + 1; k < _list.size(); k++) {
                if(_list.get(i).id == _list.get(k).id) {
                    _list.get(i).job += "," + _list.get(k).job;
                    toDel.add(_list.get(k));
                }
            }
        }
        _list.removeAll(toDel);
    }

    //get get list of distinct data items (similar id item combined by job)
    private void transformCastData(List<PersonCreditsCast> _list) {
        ArrayList<PersonCreditsCast> toDel = new ArrayList<>();
        for(int i = 0; i < _list.size() - 1; i++) {
            for(int k = i + 1; k < _list.size(); k++) {
                if(_list.get(i).id == _list.get(k).id) {
                    _list.get(i).character += ", " + _list.get(k).character;
                    toDel.add(_list.get(k));
                }
            }
        }
        _list.removeAll(toDel);
    }

    //get year of formatted data
    private int getYear(String _data) {
        if(_data == null) return 0;
        String[] arr = _data.split("-");
        return Integer.valueOf(arr[0]);
    }

    private void startMovieScreen(int id) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle movieBundle = new Bundle();
        movieBundle.putInt(Constants.EXTRA_MOVIE_ID, id);
        movieDetailFragment.setArguments(movieBundle);
        ((AppCompatActivity) mCtx).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_AM, movieDetailFragment).addToBackStack("tagga").commit();
    }
}
