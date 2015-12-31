package com.example.lynx.moviezz.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.activity.FullscreenImageActivity;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.SerializedListContainer;
import com.example.lynx.moviezz.model.get_movie_images_by_id.BaseImage;
import com.example.lynx.moviezz.model.get_person_by_id.PersonTaggedImages;
import com.example.lynx.moviezz.model.get_person_by_id.TaggedImageObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 30.12.2015.
 */
public class PersonGalleryAdapter extends RecyclerView.Adapter {

    private Context mCtx;
    private List<BaseImage> profiles;
    private PersonTaggedImages stills;

    public PersonGalleryAdapter(Context mCtx, List<BaseImage> profiles) {   // for images
        this.mCtx = mCtx;
        this.profiles = profiles;
    }

    public PersonGalleryAdapter(Context mCtx, PersonTaggedImages stills) {  // for stills
        this.mCtx = mCtx;
        List<TaggedImageObject> toDel = new ArrayList<>();
        for(TaggedImageObject tio : stills.results) {   // filter only backdrops
            if(!tio.image_type.equalsIgnoreCase("backdrop")) toDel.add(tio);
        }
        stills.results.removeAll(toDel);
        this.stills = stills;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case Constants.LIST_ITEM_PERSON_GALLERY_IMAGE:
                View imageView = inflater.inflate(R.layout.list_item_person_image, parent, false);
                return new PersonImageVH(imageView);
            case Constants.LIST_ITEM_PERSON_GALLERY_STILL:
                View stillView = inflater.inflate(R.layout.list_item_person_still, parent, false);
                return new PersonStillVH(stillView);
        }
        return null; //never
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(profiles == null) {
            //TODO build still | work with tagged image
            PersonStillVH vh = (PersonStillVH) holder;
            TaggedImageObject current = stills.results.get(position);
            Glide.with(mCtx).load(Constants.BASE_SMALL_IMAGE_URL + current.file_path).placeholder(R.drawable.placeholder_image).dontTransform().into(vh.ivPersonStill_LIPS);
            vh.tvTitle_LIPS.setText(current.media.title);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, FullscreenImageActivity.class);
                    intent.putExtra(Constants.EXTRA_TAGGED_IMAGE_DATA, stills);
                    intent.putExtra(Constants.EXTRA_CURRENT_IMAGE_POSITION, position);
                    mCtx.startActivity(intent);
                }
            });
        } else {
            //TODO build image | work with profiles
            PersonImageVH vh = (PersonImageVH) holder;
            BaseImage currentImg = profiles.get(position);
            Glide.with(mCtx).load(Constants.BASE_SMALL_IMAGE_URL + currentImg.file_path).placeholder(R.drawable.placeholder_portrait).dontTransform().into(vh.ivPersonImage_LIPI);
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
    }

    @Override
    public int getItemCount() {
        if(profiles == null) return stills.results.size();
        return profiles.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(profiles == null) return Constants.LIST_ITEM_PERSON_GALLERY_STILL;
        else return Constants.LIST_ITEM_PERSON_GALLERY_IMAGE;
    }

    public static class PersonImageVH extends RecyclerView.ViewHolder {
        public ImageView ivPersonImage_LIPI;
        public PersonImageVH(View itemView) {
            super(itemView);
            ivPersonImage_LIPI = (ImageView) itemView.findViewById(R.id.ivPersonImage_LIPI);
        }
    }

    public static class PersonStillVH extends RecyclerView.ViewHolder {
        public ImageView ivPersonStill_LIPS;
        public TextView tvTitle_LIPS;
        public PersonStillVH(View itemView) {
            super(itemView);
            ivPersonStill_LIPS = (ImageView) itemView.findViewById(R.id.ivPersonStill_LIPS);
            tvTitle_LIPS = (TextView) itemView.findViewById(R.id.tvTitle_LIPS);
        }
    }
}
