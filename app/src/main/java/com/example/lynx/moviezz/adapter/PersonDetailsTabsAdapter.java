package com.example.lynx.moviezz.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lynx.moviezz.fragment.PersonDetailCreditsFragment;
import com.example.lynx.moviezz.fragment.PersonDetailGalleryFragment;
import com.example.lynx.moviezz.fragment.PersonDetailInfoFragment;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_person_by_id.ResponsePersonById;

/**
 * Created by Lynx on 28.12.2015.
 */
public class PersonDetailsTabsAdapter extends FragmentPagerAdapter {

    private ResponsePersonById personData;
    private PersonDetailInfoFragment personDetailInfoFragment;
    private PersonDetailCreditsFragment personDetailCreditsFragment;
    private PersonDetailGalleryFragment personDetailGalleryFragment;

    public PersonDetailsTabsAdapter(FragmentManager fm, ResponsePersonById data) {
        super(fm);
        personData = data;
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.EXTRA_DATA, data);

        personDetailInfoFragment = new PersonDetailInfoFragment();
        personDetailCreditsFragment = new PersonDetailCreditsFragment();
        personDetailGalleryFragment = new PersonDetailGalleryFragment();

        personDetailInfoFragment.setArguments(bundle);
        personDetailCreditsFragment.setArguments(bundle);
        personDetailGalleryFragment.setArguments(bundle);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return personDetailInfoFragment;
            case 1:
                return personDetailCreditsFragment;
            case 2:
                return personDetailGalleryFragment;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "INFO";
            case 1:
                return "CREDITS";
            case 2:
                return "GALLERY";
        }
        return "";
    }

    @Override
    public int getCount() {
        return 3;
    }
}
