package com.github.ihandy.testproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

import java.util.List;

/**
 * Created by soloviev on 05.11.2016.
 */

public class GifsRecyclerViewAdapter extends RecyclerView.Adapter<GifsRecyclerViewAdapter.GifItemViewHolder> {

    private List<GifItem> gifItems;

    public GifsRecyclerViewAdapter(List<GifItem> gifItems) {
        this.gifItems = gifItems;
    }

    @Override
    public GifItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gif_item_layout, parent, false);
        return new GifItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GifItemViewHolder holder, int position) {
        Ion.with(holder.gifImageView).load(gifItems.get(position).getUrl());
    }


    @Override
    public int getItemCount() {
        return gifItems != null ? gifItems.size() : 0;
    }

    @Override
    public void onViewAttachedToWindow(GifItemViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(GifItemViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    public void setData(List<GifItem> data) {
        this.gifItems = data;
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
