package com.example.lynx.moviezz.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.activity.FullscreenImageActivity;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.SerializedListContainer;
import com.example.lynx.moviezz.model.get_movie_images_by_id.BaseImage;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Lynx on 12.01.2016.
 */
public class ProfileImgAdapter extends RecyclerView.Adapter {

    private Context mCtx;
    private List<BaseImage> profiles;

    public ProfileImgAdapter(Context mCtx, List<BaseImage> profiles) {
        this.mCtx = mCtx;
        this.profiles = profiles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View imageView = inflater.inflate(R.layout.list_item_person_image, parent, false);
        return new ProfileImgVH(imageView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ProfileImgVH vh = (ProfileImgVH) holder;
        BaseImage currentImg = profiles.get(position);
        //TODO here define my own fade in animation coz glide default animation resize image so with placeholder its became an issue!!
        Picasso.with(mCtx)
                .load(Constants.BASE_SMALL_IMAGE_URL + currentImg.file_path)
                .placeholder(R.drawable.placeholder_portrait)
                .into(vh.ivProfileImage);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, FullscreenImageActivity.class);
                SerializedListContainer listContainer = new SerializedListContainer();
                listContainer.data = profiles;
                intent.putExtra(Constants.EXTRA_DATA, listContainer);
                intent.putExtra(Constants.EXTRA_CURRENT_IMAGE_POSITION, position);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public static class ProfileImgVH extends RecyclerView.ViewHolder {
        public ImageView ivProfileImage;
        public ProfileImgVH(View itemView) {
            super(itemView);
            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivPersonImage_LIPI);
        }
    }
}
