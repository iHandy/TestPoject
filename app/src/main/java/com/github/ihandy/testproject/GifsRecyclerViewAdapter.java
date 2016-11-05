package com.github.ihandy.testproject;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.ihandy.testproject.model.GifItem;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;

import java.util.List;

/**
 * Created by soloviev on 05.11.2016.
 */

public class GifsRecyclerViewAdapter extends RecyclerView.Adapter<GifsRecyclerViewAdapter.GifItemViewHolder> {

    private List<GifItem> mGifItems;

    public GifsRecyclerViewAdapter(@Nullable List<GifItem> gifItems) {
        this.mGifItems = gifItems;
    }

    @Override
    public GifItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gif_item_layout, parent, false);
        return new GifItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GifItemViewHolder holder, int position) {
        GifItem gifItem = mGifItems.get(position);
        Ion.with(holder.gifImageView)
                .animateGif(AnimateGifMode.ANIMATE)
                .placeholder(R.drawable.dancing_abe)
                .load(gifItem.getUrl());
    }

    @Override
    public int getItemCount() {
        return mGifItems != null ? mGifItems.size() : 0;
    }

    @Override
    public void onViewAttachedToWindow(GifItemViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(GifItemViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    public void setData(@Nullable List<GifItem> data) {
        this.mGifItems = data;
        notifyDataSetChanged();
    }

    public static class GifItemViewHolder extends RecyclerView.ViewHolder {
        ImageView gifImageView;

        GifItemViewHolder(View itemView) {
            super(itemView);
            gifImageView = (ImageView) itemView.findViewById(R.id.gifImageView);
        }
    }

}
