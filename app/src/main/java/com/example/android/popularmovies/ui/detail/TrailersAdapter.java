package com.example.android.popularmovies.ui.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.network.models.Trailer;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerListViewHolder> {

    private static final String YOUTUBE_VIDEO_THUMB_URL = "https://img.youtube.com/vi/%s/default.jpg";

    private final Context mContext;

    private List<Trailer> mTrailers;
    private OnItemClickListener mListener;

    public TrailersAdapter(Context context, OnItemClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public TrailerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_list_item,
                parent, false);
        return new TrailerListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerListViewHolder holder, int position) {
        holder.bind(mTrailers.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        if (mTrailers == null) {
            return 0;
        }

        return mTrailers.size();
    }

    public void swapTrailers(final List<Trailer> trailersList) {
        if (mTrailers == null) {
            mTrailers = trailersList;
            notifyDataSetChanged();
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mTrailers.size();
                }

                @Override
                public int getNewListSize() {
                    return trailersList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mTrailers.get(oldItemPosition).getKey() == trailersList.get(newItemPosition).getKey();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Trailer oldTrailer = mTrailers.get(oldItemPosition);
                    Trailer newTrailer = trailersList.get(newItemPosition);

                    return oldTrailer.getKey() == newTrailer.getKey()
                            && oldTrailer.getName() == newTrailer.getName();
                }
            });

            mTrailers = trailersList;
            result.dispatchUpdatesTo(this);
        }
    }


    interface OnItemClickListener {
        void onItemClick(Trailer trailer);
    }

    class TrailerListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.trailer_list_item_thumb_imageView)
        ImageView thumbImageView;

        @BindView(R.id.trailer_list_item_title_textView)
        TextView titleTextView;

        @BindView(R.id.trailer_list_item_type_textView)
        TextView typeTextView;

        public TrailerListViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(final Trailer trailer, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(trailer);
                }
            });

            titleTextView.setText(trailer.getName());

            //TODO: Set the trailer type:
            //typeTextView.setText(trailer.getType());

            Picasso.get()
                    .load(String.format(YOUTUBE_VIDEO_THUMB_URL, trailer.getKey()))
                    .into(thumbImageView);
        }
    }
}
