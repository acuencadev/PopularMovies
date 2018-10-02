package com.example.android.popularmovies.ui.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.network.models.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewListViewHolder> {

    private final Context mContext;

    private List<Review> mReviews;
    private OnItemClickListener mListener;

    public ReviewsAdapter(Context mContext, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ReviewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list_item,
                parent, false);
        return new ReviewListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewListViewHolder holder, int position) {
        holder.bind(mReviews.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        if (mReviews == null) {
            return 0;
        }

        return mReviews.size();
    }

    public void swapTrailers(final List<Review> reviewList) {
        if (mReviews == null) {
            mReviews = reviewList;
            notifyDataSetChanged();
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mReviews.size();
                }

                @Override
                public int getNewListSize() {
                    return reviewList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mReviews.get(oldItemPosition).getId() == reviewList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Review oldReview = mReviews.get(oldItemPosition);
                    Review newReview = reviewList.get(newItemPosition);

                    return oldReview.getId().equals(newReview.getId())
                            && oldReview.getAuthor().equals(newReview.getAuthor())
                            && oldReview.getContent().equals(newReview.getContent())
                            && oldReview.getUrl().equals(newReview.getUrl());
                }
            });

            mReviews = reviewList;
            result.dispatchUpdatesTo(this);
        }
    }

    interface OnItemClickListener {
        void onItemClick(Review review);
    }

    class ReviewListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.review_list_item_content_textView)
        TextView contentTextView;

        @BindView(R.id.review_list_item_author_textView)
        TextView authorTextView;

        public ReviewListViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(final Review review, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(review);
                }
            });

            contentTextView.setText(review.getContent());
            authorTextView.setText(review.getAuthor());
        }
    }
}
