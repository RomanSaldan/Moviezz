package com.example.lynx.moviezz.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lynx.moviezz.fragment.FullscreenPageFragment;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_movie_images_by_id.BaseImage;
import com.example.lynx.moviezz.model.get_person_by_id.PersonTaggedImages;
import com.example.lynx.moviezz.model.get_person_by_id.TaggedImageObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 23.12.2015.
 */
public class FullscreenPageAdapter extends FragmentStatePagerAdapter {

    private List<BaseImage> data;

    public FullscreenPageAdapter(FragmentManager fm, List<BaseImage> data) {
        super(fm);
        this.data = data;
    }

    public FullscreenPageAdapter(FragmentManager fm, PersonTaggedImages taggedData) {
        super(fm);
        List<BaseImage> baseImages = new ArrayList<>();
        for(TaggedImageObject taggedImageObject : taggedData.results) {
            BaseImage image = new BaseImage();
            image.file_path = taggedImageObject.file_path;
            image.aspect_ratio = taggedImageObject.aspect_ratio;
            image.height = taggedImageObject.height;
            image.width = taggedImageObject.width;
            image.iso_639_1 = taggedImageObject.iso_639_1;
            image.vote_average = taggedImageObject.vote_average;
            image.vote_count = taggedImageObject.vote_count;
            baseImages.add(image);
        }
        data = baseImages;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.EXTRA_IMAGE, data.get(position));
        FullscreenPageFragment pageFragment = new FullscreenPageFragment();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public int getCount() {
        return data.size();
    }

}
