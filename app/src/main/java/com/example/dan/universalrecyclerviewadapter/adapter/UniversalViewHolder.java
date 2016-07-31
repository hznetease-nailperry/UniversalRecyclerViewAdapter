package com.example.dan.universalrecyclerviewadapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dan on 2016/7/31.
 */
public class UniversalViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;

    public UniversalViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
    }

    public <T extends View> T converToViewFromId(int resId) {
        View view = views.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
        }
        views.put(resId, view);
        return (T) view;
    }

    public UniversalViewHolder setText(int resId, String value) {
        TextView itemView = converToViewFromId(resId);
        if (TextUtils.isEmpty(value)) {
            itemView.setText("");
        } else {
            itemView.setText(value);
        }
        return this;
    }

    public UniversalViewHolder setImageResource(int viewId, int imageResId) {
        ImageView view = converToViewFromId(viewId);
        view.setImageResource(imageResId);
        return this;
    }

/*    public UniversalViewHolder setUrlForSimpleDraweeView(int viewId, String url) {
        SimpleDraweeView view = converToViewFromId(viewId);
        view.setImageURI(Uri.parse(url));
        return this;
    }*/

    public UniversalViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = converToViewFromId(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
