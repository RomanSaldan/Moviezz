package com.example.lynx.moviezz.adapter;

import android.content.Context;
import android.content.Intent;
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

/**
 * Created by Lynx on 14.01.2016.
 */
public class MovieImagesAdapter extends RecyclerView.Adapter {

    private Context mCtx;
    private List<BaseImage> images;

    public MovieImagesAdapter(Context mCtx, List<BaseImage> images) {
        this.mCtx = mCtx;
        this.images = images;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View iamgeView = inflater.inflate(R.layout.list_item_movie_image, parent, false);
        return new MovieImageVH(iamgeView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MovieImageVH vh = (MovieImageVH) holder;
        BaseImage current = images.get(position);
        Glide.with(mCtx)
                .load(Constants.BASE_SMALL_IMAGE_URL + current.file_path)
                .placeholder(R.drawable.placeholder_image)
                .animate(R.animator.fade_out)
                .thumbnail(0.7f)
                .into(vh.ivMovieImage_LIMI);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, FullscreenImageActivity.class);
                SerializedListContainer container = new SerializedListContainer();
                container.data = images;
                intent.putExtra(Constants.EXTRA_DATA, container);
                intent.putExtra(Constants.EXTRA_CURRENT_IMAGE_POSITION, position);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class MovieImageVH extends RecyclerView.ViewHolder {
        public ImageView ivMovieImage_LIMI;
        public MovieImageVH(View itemView) {
            super(itemView);
            ivMovieImage_LIMI = (ImageView) itemView.findViewById(R.id.ivMovieImage_LIMI);
        }
    }
}
