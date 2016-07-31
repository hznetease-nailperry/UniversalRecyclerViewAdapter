package com.example.dan.universalrecyclerviewadapter.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * Created by Dan on 2016/7/31.
 */
public abstract class AdapterDelegateManager {

    private SparseArray<AdapterDelegate> mAdapterDelegates;

    public AdapterDelegateManager() {
        mAdapterDelegates = new SparseArray<>();
    }

    /**
     * 设定ItemType与具体AdapterDelegate的一一对应关系
     * 若有多个ItemType则需要调用多次
     *
     * @param viewType
     * @param adapterDelegate
     */
    public void bindAdapterDelegate(int viewType, AdapterDelegate adapterDelegate) {
        mAdapterDelegates.put(viewType, adapterDelegate);
    }

    public int getItemCount() {
        int count = 0;
        for (int i = 0; i < mAdapterDelegates.size(); i++) {
            count += mAdapterDelegates.valueAt(i).getItemCount();
        }
        return count;
    }

    public UniversalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getDelegateByType(viewType).onCreateViewHolder(parent);
    }

    public void onBindViewHolder(UniversalViewHolder holder, int position) {
        holder.itemView.setTag(position);
        getDelegateByType(getItemViewType(position)).onBindViewHolder(holder, getIndexOfBoundDataSource(position));
    }

    public AdapterDelegate getDelegateByType(int type) {
        AdapterDelegate adapterDelegate = mAdapterDelegates.get(type);
        return adapterDelegate;
    }

    /**
     * @param position 整个列表的position
     * @return 每一种viewType类型对应一个数据源，根据position计算当前位置应该显示的数据在数据源中的索引index
     */
    public abstract int getIndexOfBoundDataSource(int position);

    public abstract int getItemViewType(int position);
}

