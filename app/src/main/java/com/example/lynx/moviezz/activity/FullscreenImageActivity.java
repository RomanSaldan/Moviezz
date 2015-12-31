package com.example.lynx.moviezz.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.adapter.FullscreenPageAdapter;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.SerializedListContainer;
import com.example.lynx.moviezz.model.get_movie_images_by_id.BaseImage;
import com.example.lynx.moviezz.model.get_person_by_id.PersonTaggedImages;
import com.example.lynx.moviezz.view.ViewPagerZoom;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lynx on 23.12.2015.
 */
public class FullscreenImageActivity extends AppCompatActivity {

    private PagerAdapter vpAdapter;
    private int currentPosition;
    private List<BaseImage> data;
    private PersonTaggedImages taggedData;

    @Bind(R.id.vpFullscreenImage_AFI)
    protected ViewPagerZoom vpFullscreenImage_AFI;

    @Bind(R.id.tvPageIndicator)
    protected TextView tvPageIndicator;

    @Bind(R.id.tvMovie_AFI)
    protected TextView tvMovie_AFI;

    @Bind(R.id.llContainerTitle_AFI)
    protected LinearLayout llContainerTitle_AFI; // container invisible by default

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);
        ButterKnife.bind(this);

        currentPosition = getIntent().getIntExtra(Constants.EXTRA_CURRENT_IMAGE_POSITION, 0);
        if(getIntent().getExtras().containsKey(Constants.EXTRA_DATA)) {
            data = ((SerializedListContainer) getIntent().getSerializableExtra(Constants.EXTRA_DATA)).data;
            vpAdapter = new FullscreenPageAdapter(getSupportFragmentManager(), data);
            tvPageIndicator.setText(currentPosition + 1 + "/" + data.size());

        } else if(getIntent().getExtras().containsKey(Constants.EXTRA_TAGGED_IMAGE_DATA)) {
            llContainerTitle_AFI.setVisibility(View.VISIBLE);
            taggedData = (PersonTaggedImages) getIntent().getSerializableExtra(Constants.EXTRA_TAGGED_IMAGE_DATA);
            tvMovie_AFI.setText(taggedData.results.get(currentPosition).media.title);
            vpAdapter = new FullscreenPageAdapter(getSupportFragmentManager(), taggedData);
            tvPageIndicator.setText(currentPosition + 1 + "/" + taggedData.results.size());
        }
        vpFullscreenImage_AFI.setAdapter(vpAdapter);
        vpFullscreenImage_AFI.setCurrentItem(currentPosition);
        vpFullscreenImage_AFI.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(taggedData == null) tvPageIndicator.setText(position+1 + "/" + data.size());
                else {
                    tvPageIndicator.setText(position+1 + "/" + taggedData.results.size());
                    tvMovie_AFI.setText(taggedData.results.get(position).media.title);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
