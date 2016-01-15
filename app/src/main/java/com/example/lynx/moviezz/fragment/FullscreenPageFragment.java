package com.example.lynx.moviezz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_movie_images_by_id.BaseImage;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Lynx on 23.12.2015.
 */
public class FullscreenPageFragment extends Fragment {

    private PhotoViewAttacher mAttacher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_fullscreen_image, container, false);
        final ImageView ivFullscreenImage_FPF = (ImageView) rootView.findViewById(R.id.ivFullscreenImage_FPF);
        final ProgressBar pbImageDownload_FOF = (ProgressBar) rootView.findViewById(R.id.pbImageDownload_FPF);
        BaseImage image = (BaseImage) getArguments().getSerializable(Constants.EXTRA_IMAGE);
        Glide.with(getActivity()).
                load(Constants.BASE_IMAGE_URL + image.file_path)
                .animate(R.animator.fade_out)
                .thumbnail(0.7f)
                .listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                pbImageDownload_FOF.setVisibility(View.GONE);
                mAttacher = new PhotoViewAttacher(ivFullscreenImage_FPF);
                return false;
            }
        }).into(ivFullscreenImage_FPF);
        return rootView;
    }


}
