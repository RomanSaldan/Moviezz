package com.example.lynx.moviezz.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.adapter.FullscreenPageAdapter;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.SerializedListContainer;
import com.example.lynx.moviezz.model.get_movie_images_by_id.BaseImage;
import com.example.lynx.moviezz.view.ViewPagerZoom;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lynx on 23.12.2015.
 */
public class FullscreenImageActivity extends AppCompatActivity {

    private PagerAdapter vpAdater;
    private int currentPosition;
    private List<BaseImage> data;

    @Bind(R.id.vpFullscreenImage_AFI)
    protected ViewPagerZoom vpFullscreenImage_AFI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);
        ButterKnife.bind(this);
        currentPosition = getIntent().getIntExtra(Constants.EXTRA_CURRENT_IMAGE_POSITION, 0);
        data = ((SerializedListContainer) getIntent().getSerializableExtra(Constants.EXTRA_DATA)).data;
        vpAdater = new FullscreenPageAdapter(getSupportFragmentManager(), data);
        vpFullscreenImage_AFI.setAdapter(vpAdater);
        vpFullscreenImage_AFI.setCurrentItem(currentPosition);
    }
}
