package com.example.lynx.moviezz.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.activity.FullscreenPosterActivity;
import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_person_by_id.ResponsePersonById;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lynx on 28.12.2015.
 */
public class PersonDetailInfoFragment extends Fragment {

    private ResponsePersonById data;

    //region ButterKnife binds
    @Bind(R.id.tvPersonName_FPDI)
    protected TextView tvPersonName_FPDI;

    @Bind(R.id.tvPersonKnowAs_FPDI)
    protected TextView tvPersonKnowAs_FPDI;

    @Bind(R.id.tvPersonBirthday_FPDI)
    protected TextView tvPersonBirthday_FPDI;

    @Bind(R.id.tvPersonBirthplace_FPDI)
    protected TextView tvPersonBirthplace_FPDI;

    @Bind(R.id.tvDeathdayTitle_FPDI)
    protected TextView tvDethdayTitle_FPDI;

    @Bind(R.id.tvDeathday_FPDI)
    protected TextView tvDathday_FPDI;

    @Bind(R.id.ivPerson_FPDI)
    protected ImageView ivPerson_FPDI;

    @Bind(R.id.cvBiography_FPDI)
    protected CardView cvBiography_FPDI;

    @Bind(R.id.tvBiography_FPDI)
    protected TextView tvBiography_FPDI;

    @Bind(R.id.btnIMDB_FPDI)
    protected Button btnIMDB_FPDI;

    @Bind(R.id.btnWeb_FPDI)
    protected Button btnWeb_FPDI;

    @OnClick(R.id.btnIMDB_FPDI)
    protected void clickPersonIMDB(View v) {
        String url = String.format("http://www.imdb.com/name/%s/", data.imdb_id).toString();
        Intent imdbIntent = new Intent();
        imdbIntent.setData(Uri.parse(url));
        startActivity(imdbIntent);
    }

    @OnClick(R.id.btnWeb_FPDI)
    protected void clickPersonWeb(View v) {
        String url = data.homepage;
        Intent webIntent = new Intent();
        webIntent.setData(Uri.parse(url));
        startActivity(webIntent);
    }

    @OnClick(R.id.ivPerson_FPDI)
    protected void clickPersonImage(View v) {
        Intent intent = new Intent(getActivity(), FullscreenPosterActivity.class);
        intent.putExtra(Constants.EXTRA_FULLSCREEN_IMAGE_PATH, data.profile_path);
        startActivity(intent);
    }
    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        data = (ResponsePersonById) getArguments().getSerializable(Constants.EXTRA_DATA);
        View rooView = inflater.inflate(R.layout.fragment_person_detail_info, container, false);
        ButterKnife.bind(this, rooView);

        //init view with data
        tvPersonName_FPDI.setText(data.name);
        if(data.also_known_as.length>0)
            tvPersonKnowAs_FPDI.setText(getKnowAsStr());
        if(data.birthday != null && !data.birthday.equalsIgnoreCase(""))
            tvPersonBirthday_FPDI.setText(String.format("%s (%d years old)", data.birthday.replace("-", "."), getPersonAge()));
        if(data.place_of_birth != null && !data.place_of_birth.equalsIgnoreCase(""))
            tvPersonBirthplace_FPDI.setText(data.place_of_birth);
        if(data.deathday != null && !data.deathday.equalsIgnoreCase("")) {
            tvDethdayTitle_FPDI.setVisibility(View.VISIBLE);
            tvDathday_FPDI.setVisibility(View.VISIBLE);
            tvDathday_FPDI.setText(data.deathday.replace("-", "."));
        }
        Glide.with(getActivity()).load(Constants.BASE_SMALL_IMAGE_URL + data.profile_path).placeholder(R.drawable.placeholder_portrait).into(ivPerson_FPDI);
        if(data.biography != null && !data.biography.equalsIgnoreCase("")) {
            cvBiography_FPDI.setVisibility(View.VISIBLE);
            tvBiography_FPDI.setText(data.biography);
        }
        if(data.imdb_id != null && !data.imdb_id.equalsIgnoreCase("")) btnIMDB_FPDI.setVisibility(View.VISIBLE);
        if(data.homepage != null && !data.homepage.equalsIgnoreCase("")) btnWeb_FPDI.setVisibility(View.VISIBLE);
        return rooView;
    }

    private String getKnowAsStr() {
        StringBuilder sb = new StringBuilder();
        for(String line : data.also_known_as) sb.append(line + "\n");
        return sb.toString();
    }

    private int getPersonAge() {
        String[] arr = data.birthday.split("-");  // arr0 - year, arr1 - month, arr2 - day
        int bYear = Integer.valueOf(arr[0]);
        int bMonth = Integer.valueOf(arr[1]);
        int bDay = Integer.valueOf(arr[2]);
        LocalDate birthdate = new LocalDate(bYear, bMonth, bDay);
        if(data.deathday != null && !data.deathday.equalsIgnoreCase("")) {
            String[] deathArr = data.deathday.split("-");
            int dYear = Integer.valueOf(deathArr[0]);
            int dMonth = Integer.valueOf(deathArr[1]);
            int dDay = Integer.valueOf(deathArr[2]);
            LocalDate deathDate = new LocalDate(dYear, dMonth, dDay);
            return Years.yearsBetween(birthdate, deathDate).getYears();
        } else {
            LocalDate now = new LocalDate();
            return Years.yearsBetween(birthdate, now).getYears();
        }
    }
}
