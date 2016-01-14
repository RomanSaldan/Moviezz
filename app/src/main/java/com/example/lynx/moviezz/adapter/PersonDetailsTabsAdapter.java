package com.example.lynx.moviezz.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.LinearLayout;

import com.example.lynx.moviezz.fragment.PersonDetailCreditsFragment;
import com.example.lynx.moviezz.fragment.PersonDetailInfoFragment;
import com.example.lynx.moviezz.fragment.PersonDetailProfileImgFragment;
import com.example.lynx.moviezz.fragment.PersonDetailStillsImgFragment;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_person_by_id.ResponsePersonById;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lynx on 28.12.2015.
 */
public class PersonDetailsTabsAdapter extends FragmentPagerAdapter {

    private ResponsePersonById personData;
    private PersonDetailInfoFragment personDetailInfoFragment;
    private PersonDetailCreditsFragment personDetailCreditsFragment;

    private PersonDetailProfileImgFragment profileImgFragment;
    private PersonDetailStillsImgFragment stillsImgFragment;

    private Map<Integer,Fragment> mapTabs;

    public PersonDetailsTabsAdapter(FragmentManager fm, ResponsePersonById data) {
        super(fm);
        personData = data;
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.EXTRA_DATA, data);

        mapTabs = new HashMap<>();

        personDetailInfoFragment = new PersonDetailInfoFragment();
        personDetailInfoFragment.setArguments(bundle);
        mapTabs.put(0, personDetailInfoFragment);

        if(data.movie_credits.cast.size() > 0 || data.movie_credits.crew.size() > 0) {
            personDetailCreditsFragment = new PersonDetailCreditsFragment();
            personDetailCreditsFragment.setArguments(bundle);
            mapTabs.put(mapTabs.size(), personDetailCreditsFragment);
        }
        if(data.images.profiles.size() > 0) {
            profileImgFragment = new PersonDetailProfileImgFragment();
            profileImgFragment.setArguments(bundle);
            mapTabs.put(mapTabs.size(), profileImgFragment);
        }
        if(data.tagged_images.results.size() > 0) {
            stillsImgFragment = new PersonDetailStillsImgFragment();
            stillsImgFragment.setArguments(bundle);
            mapTabs.put(mapTabs.size(), stillsImgFragment);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mapTabs.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Fragment fragment = mapTabs.get(position);
        if(fragment instanceof PersonDetailInfoFragment) return "INFO";
        else if(fragment instanceof PersonDetailCreditsFragment) return "CREDITS";
        else if(fragment instanceof PersonDetailProfileImgFragment) return "PORTRAITS";
        else if(fragment instanceof PersonDetailStillsImgFragment) return "SCREENS";
        else return "";
    }

    @Override
    public int getCount() {
        return mapTabs.size();
    }
}
