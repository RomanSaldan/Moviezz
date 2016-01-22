package com.example.lynx.moviezz.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.activity.FullscreenImageActivity;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.SerializedListContainer;
import com.example.lynx.moviezz.model.get_movie_images_by_id.BaseImage;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Lynx on 14.01.2016.
 */
public class MoviePostersAdapter extends RecyclerView.Adapter {

    private Context mCtx;
    private List<BaseImage> posters;

    public MoviePostersAdapter(Context mCtx, List<BaseImage> posters) {
        this.mCtx = mCtx;
        this.posters = posters;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View imageView = inflater.inflate(R.layout.list_item_poster_image, parent, false);
        return new MoviePosterVH(imageView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MoviePosterVH vh = (MoviePosterVH) holder;
        BaseImage currentImg = posters.get(position);
        Glide.with(mCtx)
                .load(Constants.BASE_SMALL_IMAGE_URL + currentImg.file_path)
                .placeholder(R.drawable.placeholder_poster)
                .animate(R.animator.fade_out)
                .thumbnail(0.7f)
                .into(vh.ivPerson_LIPI);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, FullscreenImageActivity.class);
                SerializedListContainer listContainer = new SerializedListContainer();
                listContainer.data = posters;
                intent.putExtra(Constants.EXTRA_DATA, listContainer);
                intent.putExtra(Constants.EXTRA_CURRENT_IMAGE_POSITION, position);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posters.size();
    }

    public static class MoviePosterVH extends RecyclerView.ViewHolder {
        public ImageView ivPerson_LIPI;
        public MoviePosterVH(View itemView) {
            super(itemView);
            ivPerson_LIPI = (ImageView) itemView.findViewById(R.id.ivPoster_LIPI);
        }
    }
}
