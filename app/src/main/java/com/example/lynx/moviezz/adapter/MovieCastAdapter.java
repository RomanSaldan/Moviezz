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
import com.example.lynx.moviezz.fragment.PersonDetailFragment;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.global.Logg;
import com.example.lynx.moviezz.model.get_movie_info_by_id.ActorInfo;
import com.example.lynx.moviezz.model.get_movie_info_by_id.Casts;
import com.example.lynx.moviezz.model.get_movie_info_by_id.CrewInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 22.12.2015.
 */
public class MovieCastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mCtx;
    private Casts data;
    private int directorCount = 0;
    private List<CrewInfo> directorList = new ArrayList<>();

    public MovieCastAdapter(Context context, Casts data) {
        mCtx = context;
        this.data = data;
        for(CrewInfo ci : data.crew)
            if(ci.job.equalsIgnoreCase("Director")) {
                directorCount++;
                directorList.add(ci);
            }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case Constants.LIST_ITEM_HEADER:
                View headerView = layoutInflater.inflate(R.layout.list_item_cast_header, parent, false);
                return new CastHeaderHV(headerView);
            case Constants.LIST_ITEM_ACTOR:
                View actorView = layoutInflater.inflate(R.layout.list_item_cast_actor, parent, false);
                return new CastActorVH(actorView);
            case Constants.LIST_ITEM_CREW:
                View crewView = layoutInflater.inflate(R.layout.list_item_cast_crew, parent, false);
                return new CastCrewVH(crewView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == 0) {
            ((CastHeaderHV) holder).tvHeader_LICH.setText("Directors:");
        } else if(position <= directorCount) {
            final CrewInfo director = directorList.get(position-1);
            Glide.with(mCtx)
                    .load(Constants.BASE_SMALL_IMAGE_URL + director.profile_path)
                    .placeholder(R.drawable.placeholder_portrait)
                    .animate(R.animator.fade_out)
                    .thumbnail(0.7f)
                    .into(((CastCrewVH)holder).ivCrew_LICC);
            ((CastCrewVH)holder).tvCrewName_LICC.setText(director.name);
            ((CastCrewVH)holder).tvJob_LICC.setText(director.job);
            ((CastCrewVH) holder).rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logg.d("You clicked on " + director.name + " " + director.id);
                    //TODO handle director here
                    startPersonInfoScreen(director.id);

                }
            });
        } else if(position == directorCount+1) {
            ((CastHeaderHV) holder).tvHeader_LICH.setText("Actors:");
        } else if(position < directorCount+2+data.cast.size()) {
            final ActorInfo actor = data.cast.get(position - directorCount - 2);
            Glide.with(mCtx)
                    .load(Constants.BASE_SMALL_IMAGE_URL + actor.profile_path)
                    .placeholder(R.drawable.placeholder_portrait)
                    .animate(R.animator.fade_out)
                    .thumbnail(0.7f)
                    .into(((CastActorVH)holder).ivActor_LICA);
            ((CastActorVH)holder).tvActorName_LICA.setText(actor.name);
            ((CastActorVH)holder).tvCharacter_LICA.setText(actor.character);
            ((CastActorVH) holder).rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logg.d("You clicked on " + actor.name + " " + actor.id);
                    //TODO handle actor here
                    startPersonInfoScreen(actor.id);
                }
            });
        } else if(position == directorCount+2+data.cast.size()) {
            ((CastHeaderHV) holder).tvHeader_LICH.setText("Crew:");
        } else {
            final CrewInfo crew = data.crew.get(position - directorCount-2-data.cast.size());
            if(crew.job.equalsIgnoreCase("Director")) return;
            Glide.with(mCtx)
                    .load(Constants.BASE_SMALL_IMAGE_URL + crew.profile_path)
                    .placeholder(R.drawable.placeholder_portrait)
                    .animate(R.animator.fade_out)
                    .thumbnail(0.7f)
                    .into((((CastCrewVH)holder).ivCrew_LICC));
            ((CastCrewVH)holder).tvCrewName_LICC.setText(crew.name);
            ((CastCrewVH)holder).tvJob_LICC.setText(crew.job);
            ((CastCrewVH) holder).rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logg.d("You clicked on " + crew.name + " " + crew.id);
                    //TODO handle crew here
                    startPersonInfoScreen(crew.id);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return Constants.LIST_ITEM_HEADER;
        else if(position <= directorCount) return Constants.LIST_ITEM_CREW;
        else if(position == directorCount+1) return Constants.LIST_ITEM_HEADER;
        else if(position < directorCount+2+data.cast.size()) return Constants.LIST_ITEM_ACTOR;
        else if(position == directorCount+2+data.cast.size()) return Constants.LIST_ITEM_HEADER;
        else return Constants.LIST_ITEM_CREW;
    }

    @Override
    public int getItemCount() {
        return data.cast.size() + data.crew.size() + 3;
    }

    public static class CastHeaderHV extends RecyclerView.ViewHolder {
        public TextView tvHeader_LICH;

        public CastHeaderHV(View itemView) {
            super(itemView);
            tvHeader_LICH = (TextView) itemView.findViewById(R.id.tvHeader_LICH);
        }
    }

    public static class CastActorVH extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView ivActor_LICA;
        public TextView tvActorName_LICA;
        public TextView tvCharacter_LICA;

        public CastActorVH(View itemView) {
            super(itemView);
            rootView = itemView;
            ivActor_LICA = (ImageView) itemView.findViewById(R.id.ivActor_LICA);
            tvActorName_LICA = (TextView) itemView.findViewById(R.id.tvActorName_LICA);
            tvCharacter_LICA = (TextView) itemView.findViewById(R.id.tvCharacter_LICA);
        }
    }

    public static class CastCrewVH extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView ivCrew_LICC;
        public TextView tvCrewName_LICC;
        public TextView tvJob_LICC;

        public CastCrewVH(View itemView) {
            super(itemView);
            rootView = itemView;
            ivCrew_LICC = (ImageView) itemView.findViewById(R.id.ivCrew_LICC);
            tvCrewName_LICC = (TextView) itemView.findViewById(R.id.tvCrewName_LICC);
            tvJob_LICC = (TextView) itemView.findViewById(R.id.tvJob_LICC);
        }
    }

    private void startPersonInfoScreen(int id) {
        PersonDetailFragment personDetailFragment = new PersonDetailFragment();
        Bundle personBundle = new Bundle();
        personBundle.putInt(Constants.EXTRA_PERSON_ID, id);
        personDetailFragment.setArguments(personBundle);
        ((AppCompatActivity)mCtx).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_AM, personDetailFragment).commit();
    }
}
