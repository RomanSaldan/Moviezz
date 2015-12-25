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
import com.example.lynx.moviezz.global.Logg;
import com.example.lynx.moviezz.model.SerializedListContainer;
import com.example.lynx.moviezz.model.get_movie_images_by_id.BaseImage;

import java.util.List;

/**
 * Created by Lynx on 23.12.2015.
 */
public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mCtx;
    private List<BaseImage> data;
    private int itemType;   // 1 - image, 2 - poster

    public GalleryAdapter(Context context, List<BaseImage> data, int itemType) {
        mCtx = context;
        this.data = data;
        this.itemType = itemType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case Constants.LIST_ITEM_GALLERY_IMAGE:
                View imageView = layoutInflater.inflate(R.layout.list_item_gallery_image, parent, false);
                return new GalleryImageVH(imageView);
            case Constants.LIST_ITEM_GALLERY_POSTER:
                View posterVIew = layoutInflater.inflate(R.layout.list_item_gallery_poster, parent, false);
                return new GalleryPosterVH(posterVIew);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final BaseImage image = data.get(position);
        final Intent intent = new Intent(mCtx, FullscreenImageActivity.class);
        SerializedListContainer container = new SerializedListContainer();
        container.data = data;
        intent.putExtra(Constants.EXTRA_DATA, container);
        if(holder instanceof GalleryImageVH) {
            Glide.with(mCtx).load(Constants.BASE_SMALL_IMAGE_URL + image.file_path).placeholder(R.drawable.placeholder_image).into(((GalleryImageVH) holder).ivImage_LIGI);
            ((GalleryImageVH) holder).ivImage_LIGI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO handle click image
                    intent.putExtra(Constants.EXTRA_CURRENT_IMAGE_POSITION, position);
                    mCtx.startActivity(intent);
                }
            });
        } else if(holder instanceof GalleryPosterVH) {
            Glide.with(mCtx).load(Constants.BASE_SMALL_IMAGE_URL + image.file_path).placeholder(R.drawable.placeholder_poster).into(((GalleryPosterVH) holder).ivPoster_LIGP);
            ((GalleryPosterVH) holder).ivPoster_LIGP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO handle click poster
                    intent.putExtra(Constants.EXTRA_CURRENT_IMAGE_POSITION, position);
                    mCtx.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemType;
    }

    public static class GalleryImageVH extends RecyclerView.ViewHolder {
        public ImageView ivImage_LIGI;

        public GalleryImageVH(View itemView) {
            super(itemView);
            ivImage_LIGI = (ImageView) itemView.findViewById(R.id.ivImage_LIGI);
        }
    }

    public static class GalleryPosterVH extends RecyclerView.ViewHolder {
        public ImageView ivPoster_LIGP;

        public GalleryPosterVH(View itemView) {
            super(itemView);
            ivPoster_LIGP = (ImageView) itemView.findViewById(R.id.ivPoster_LIGP);
        }
    }
}
