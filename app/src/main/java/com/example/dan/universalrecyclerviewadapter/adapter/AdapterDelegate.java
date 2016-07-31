package com.example.dan.universalrecyclerviewadapter.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Dan on 2016/7/31.
 */
public abstract class AdapterDelegate<T> implements View.OnClickListener{
    protected List<T> mData;
    protected int mLayoutResId;
    protected Context mContext;

    public AdapterDelegate(Context context, int layoutResId, List<T> data) {
        mContext = context;
        mLayoutResId = layoutResId;
        mData = data;
    }

    public UniversalViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutResId, parent, false);
        return new UniversalViewHolder(view);
    }

    /**
     * realPosition为mData的实际position
     *
     * @param holder
     * @param realPosition
     */
    public void onBindViewHolder(UniversalViewHolder holder, int realPosition) {
        Log.e("AdapterDelegate","realPosition="+realPosition);
        displayContents(holder, getItemData(realPosition));
    }

    @Override
    public void onClick(View view) {

    }

    public T getItemData(int position) {
        return mData.get(position);
    }

    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public abstract void displayContents(UniversalViewHolder holder, T itemData);
}
