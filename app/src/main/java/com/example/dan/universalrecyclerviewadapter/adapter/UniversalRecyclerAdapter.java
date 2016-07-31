package com.example.dan.universalrecyclerviewadapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Dan on 2016/7/31.
 */
public class UniversalRecyclerAdapter extends RecyclerView.Adapter<UniversalViewHolder> {

    private AdapterDelegateManager mAdapterDelegateManager;

    public UniversalRecyclerAdapter(AdapterDelegateManager adapterDelegateManager) {
        mAdapterDelegateManager = adapterDelegateManager;
    }

    /**
     * 当 RecyclerView 依据给出的类型需要一个新的 ViewHolder 去展示一个 item 时，该方法将会被调用
     * 这个给出的类型是在 getItemViewType返回的,默认返回 0
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public UniversalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mAdapterDelegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return mAdapterDelegateManager.getItemViewType(position);
    }

    /**
     * RecyclerView 将要在特殊的位置上显示数据时，该方法将被调用。该方法将会在固定的位置上
     * 把ViewHolder里的itemView数据映射在item中
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(UniversalViewHolder holder, int position) {
        mAdapterDelegateManager.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mAdapterDelegateManager.getItemCount();
    }

}

