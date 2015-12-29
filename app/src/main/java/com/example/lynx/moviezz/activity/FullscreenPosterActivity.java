package com.example.lynx.moviezz.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.global.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Lynx on 25.12.2015.
 */
public class FullscreenPosterActivity extends AppCompatActivity {

    private PhotoViewAttacher mAttacher;

    @Bind(R.id.ivFullscreenPoster_AFP)
    protected ImageView ivFullscreenPoster_AFP;

    @Bind(R.id.pbIPosterDownload_AFP)
    protected ProgressBar pbIPosterDownload_AFP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_poster);
        ButterKnife.bind(this);
        String posterPath = getIntent().getStringExtra(Constants.EXTRA_FULLSCREEN_IMAGE_PATH);
        Glide.with(this).load(Constants.BASE_IMAGE_URL + posterPath).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                pbIPosterDownload_AFP.setVisibility(View.GONE);
                mAttacher = new PhotoViewAttacher(ivFullscreenPoster_AFP);
                return false;
            }
        }).into(ivFullscreenPoster_AFP);
    }
}
