package com.example.dan.universalrecyclerviewadapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.example.dan.universalrecyclerviewadapter.adapter.AdapterDelegate;
import com.example.dan.universalrecyclerviewadapter.adapter.AdapterDelegateManager;
import com.example.dan.universalrecyclerviewadapter.adapter.UniversalRecyclerAdapter;
import com.example.dan.universalrecyclerviewadapter.adapter.UniversalViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerview;
    private Context mContext;
    private List<String> mLeftList, mRightList;// 每个item type对应一个数据源
    private int[] mTypeArr;// 约定每个position应该展示的item type
    private SparseArray<Integer> mIndexArr;// 储存每个position的数据在各自数据源中的index，本例只涉及两个数据源

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        initData();
        initViews();

    }

    private void initData() {
        mLeftList = new ArrayList<>();
        mLeftList.add("left_1");
        mLeftList.add("left_2");
        mLeftList.add("left_3");
        mLeftList.add("left_4");
        mLeftList.add("left_5");
        mRightList = new ArrayList<>();
        mRightList.add("right_1");
        mRightList.add("right_2");
        mRightList.add("right_3");
        mRightList.add("right_4");
        mRightList.add("right_5");
        mRightList.add("right_6");
        mIndexArr = new SparseArray<>();
        mTypeArr = new int[11];
        for (int i = 0;i<5;i++){
            mTypeArr[2*i] = 0;// 0 2 4 6 8
            mIndexArr.put(2*i,i);
            mTypeArr[2*i+1] = 1;// 1 3 5 7 9
            mIndexArr.put(2*i+1,i);
        }
        mTypeArr[10] = 1;// 10
        mIndexArr.put(10,5);
    }

    private void initViews() {
        mRecyclerview = (RecyclerView) this.findViewById(R.id.recyclerview);
        // 创建Adapter管理器，指定每个position应该展示的item type
        AdapterDelegateManager manager = new AdapterDelegateManager() {
            /**
             * 约定每个position应该展示的item type如下：
             * 偶数:item_type_0 对应数据源 mLeftList
             * 寄数:item_type_1 对应数据源 mRightList
             * @param position 整个列表的position
             * @return
             */
            @Override
            public int getIndexOfBoundDataSource(int position) {
                return mIndexArr.get(position);
            }

            @Override
            public int getItemViewType(int position) {
                return mTypeArr[position];
            }
        };
        AdapterDelegate delegateL = new AdapterDelegate<String>(mContext, R.layout.item_chat_left, mLeftList) {
            @Override
            public void displayContents(UniversalViewHolder holder, String itemData) {
                holder.setText(R.id.text_left, itemData);
            }
        };
        AdapterDelegate delegateR = new AdapterDelegate<String>(mContext, R.layout.item_chat_right, mRightList) {
            @Override
            public void displayContents(UniversalViewHolder holder, String itemData) {
                holder.setText(R.id.text_right, itemData);
            }
        };
        manager.bindAdapterDelegate(0, delegateL);
        manager.bindAdapterDelegate(1, delegateR);
        mRecyclerview.setAdapter(new UniversalRecyclerAdapter(manager));
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
    }
}
