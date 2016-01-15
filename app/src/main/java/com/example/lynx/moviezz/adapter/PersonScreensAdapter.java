package com.example.lynx.moviezz.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.lynx.moviezz.model.get_person_by_id.PersonTaggedImages;
import com.example.lynx.moviezz.model.get_person_by_id.TaggedImageObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 12.01.2016.
 */
public class PersonScreensAdapter extends RecyclerView.Adapter {

    private Context mCtx;
    private PersonTaggedImages stills;

    public PersonScreensAdapter(Context mCtx, PersonTaggedImages stills) {
        this.mCtx = mCtx;
        this.stills = filterStillsFromPosters(stills);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View stillView = inflater.inflate(R.layout.list_item_person_still, parent, false);
        return new PersonStillImgVH(stillView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        PersonStillImgVH vh = (PersonStillImgVH) holder;
        TaggedImageObject current = stills.results.get(position);
        Glide.with(mCtx)
                .load(Constants.BASE_SMALL_IMAGE_URL + current.file_path)
                .placeholder(R.drawable.placeholder_image)
                .animate(R.animator.fade_out)
                .thumbnail(0.7f)
                .into(vh.ivPersonStill_LIPS);
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
    }

    @Override
    public int getItemCount() {
        return stills.results.size();
    }

    public static class PersonStillImgVH extends RecyclerView.ViewHolder {
        public ImageView ivPersonStill_LIPS;
        public TextView tvTitle_LIPS;
        public PersonStillImgVH(View itemView) {
            super(itemView);
            ivPersonStill_LIPS = (ImageView) itemView.findViewById(R.id.ivPersonStill_LIPS);
            tvTitle_LIPS = (TextView) itemView.findViewById(R.id.tvTitle_LIPS);
        }
    }

    /**
     * Filter input data stills from posters
     * @param _list
     * @return data object with filtered images
     */
    public PersonTaggedImages filterStillsFromPosters(PersonTaggedImages _list) {
        List<TaggedImageObject> toDel = new ArrayList<>();
        for(TaggedImageObject tio : _list.results) {   // filter only backdrops
            if(!tio.image_type.equalsIgnoreCase("backdrop")) toDel.add(tio);
        }
        _list.results.removeAll(toDel);
        return _list;
    }
}
