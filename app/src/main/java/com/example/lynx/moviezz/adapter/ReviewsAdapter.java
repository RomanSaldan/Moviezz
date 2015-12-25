package com.example.lynx.moviezz.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.global.Logg;
import com.example.lynx.moviezz.model.get_movie_info_by_id.Review;

import java.util.List;

/**
 * Created by Lynx on 21.12.2015.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private List<Review> data;

    public ReviewsAdapter(List<Review> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_review, parent, false);
        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvReviewAuthor_LIR.setText(data.get(position).author);
        holder.tvDemoContent_LIR.setText(data.get(position).content);
        holder.tvReviewContent_LIR.setText(data.get(position).content);
        holder.ivArrow_LIR.setImageResource(holder.tvDemoContent_LIR.getVisibility() == View.GONE ?
                R.drawable.ic_expand_less_black_18dp : R.drawable.ic_expand_more_black_18dp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v.findViewById(R.id.tvDemoContent_LIR);
                if(tv.getLineCount()>3) {
                    if(holder.tvDemoContent_LIR.getVisibility() == View.GONE) {
                        holder.ivArrow_LIR.setImageResource(R.drawable.ic_expand_more_black_18dp);
                        holder.tvDemoContent_LIR.setVisibility(View.VISIBLE);
                        holder.tvReviewContent_LIR.setVisibility(View.GONE);
                    } else {
                        holder.ivArrow_LIR.setImageResource(R.drawable.ic_expand_less_black_18dp);
                        holder.tvDemoContent_LIR.setVisibility(View.GONE);
                        holder.tvReviewContent_LIR.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvReviewAuthor_LIR;
        public TextView tvDemoContent_LIR;
        public TextView tvReviewContent_LIR;
        public ImageView ivArrow_LIR;

        public ViewHolder(final CardView reviewCard) {
            super(reviewCard);
            tvReviewAuthor_LIR = (TextView) reviewCard.findViewById(R.id.tvReviewAuthor_LIR);
            tvDemoContent_LIR = (TextView) reviewCard.findViewById(R.id.tvDemoContent_LIR);
            tvReviewContent_LIR = (TextView) reviewCard.findViewById(R.id.tvReviewContent_LIR);
            ivArrow_LIR = (ImageView) reviewCard.findViewById(R.id.ivArrow_LIR);
        }
    }
}
