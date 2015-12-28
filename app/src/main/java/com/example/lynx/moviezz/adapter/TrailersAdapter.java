package com.example.lynx.moviezz.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_movie_info_by_id.Trailers;
import com.example.lynx.moviezz.model.get_movie_info_by_id.YouTubeTrailer;
import com.google.android.youtube.player.YouTubeStandalonePlayer;


/**
 * Created by Lynx on 24.12.2015.
 */
public class TrailersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Trailers data;

    public TrailersAdapter(Trailers data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trailer, parent, false);
        return new TrailersVH(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        TrailersVH vh = (TrailersVH) holder;
        final YouTubeTrailer trailer = data.youtube.get(position);
        vh.tvVideoName_LIT.setText(trailer.name);
        vh.tvTrailerDescription_LIT.setText(trailer.type + " [" + trailer.size + "]");

        vh.cvTrailer_LIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = YouTubeStandalonePlayer.createVideoIntent(
                        (Activity) holder.itemView.getContext(), Constants.GOOGLE_API_KEY, trailer.source);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.youtube.size();
    }


    public static class TrailersVH extends RecyclerView.ViewHolder {
        public TextView tvVideoName_LIT;
        public TextView tvTrailerDescription_LIT;
        public CardView cvTrailer_LIT;
        public TrailersVH(View itemView) {
            super(itemView);
            tvVideoName_LIT = (TextView) itemView.findViewById(R.id.tvVideoName_LIT);
            tvTrailerDescription_LIT = (TextView) itemView.findViewById(R.id.tvTrailerDescription_LIT);
            cvTrailer_LIT = (CardView) itemView.findViewById(R.id.cvTrailer_LIT);
        }
    }
}
