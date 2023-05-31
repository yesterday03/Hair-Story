/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xiong.stockdiagnosticdiary.adapter;

import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiong.stockdiagnosticdiary.OnClickListener;
import com.xiong.stockdiagnosticdiary.R;
import com.xiong.stockdiagnosticdiary.bean.Hairstylist;
import com.xiong.stockdiagnosticdiary.db.LearningDatabase;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.layout.ExpandableLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 可伸缩布局适配器
 *
 * @author xuexiang
 * @since 2019-11-22 15:38
 */
public class ExpandableListAdapter extends BaseRecyclerAdapter<String> implements  BaseQuickAdapter.OnItemClickListener<Hairstylist> {

    private RecyclerView mRecyclerView;
    OnClickListener onClickListen;
//    List<String> title;
    List<Hairstylist> shopName;
    public ExpandableListAdapter(RecyclerView recyclerView, Collection<String> data, OnClickListener onClickListener) {
        super(data);
        mRecyclerView = recyclerView;
        this.onClickListen=onClickListener;
//        this.title=title;

    }
    public List<String>getTitle(){
        return mData;
    }
    /**
     * 适配的布局
     *
     * @param viewType
     * @return
     */
    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_expandable_list_item;
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position 索引
     * @param item     列表项
     */
    @Override
    protected void bindData(@NonNull RecyclerViewHolder holder, int position, String item) {
        Log.e("TAG", "bindData: "+item );
        ExpandableLayout expandableLayout = holder.findViewById(R.id.expandable_layout);
        AppCompatImageView ivIndicator = holder.findViewById(R.id.iv_indicator);
        expandableLayout.setInterpolator(new OvershootInterpolator());
        expandableLayout.setOnExpansionChangedListener((expansion, state) -> {
            if (mRecyclerView != null && state == ExpandableLayout.State.EXPANDING) {
                mRecyclerView.smoothScrollToPosition(position);
            }
            if (ivIndicator != null) {
                ivIndicator.setRotation(expansion * 90);
            }
        });

        boolean isSelected = position == mSelectPosition;
        expandableLayout.setExpanded(isSelected, false);

        holder.select(R.id.fl_title, isSelected);
        holder.text(R.id.tv_title, mData.get(position));
        RecyclerView ry = holder.itemView.findViewById(R.id.ry);
        ry.setLayoutManager(new GridLayoutManager(holder.getContext(), 1));
        RyAd ad=new RyAd();
        ry.setAdapter(ad);
        ad.setOnItemClickListener(this);
         shopName = LearningDatabase.getLearningDatabase(ad.getContext()).hairstylistDao().findShopName(item);
        ad.addAll(shopName);
        holder.click(R.id.fl_title, new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                onClickItem(v, expandableLayout, position);
            }
        });
    }


    private void onClickItem(View view, final ExpandableLayout expandableLayout, final int position) {
        RecyclerViewHolder holder = (RecyclerViewHolder) mRecyclerView.findViewHolderForAdapterPosition(mSelectPosition);
        if (holder != null) {
            holder.select(R.id.fl_title, false);
            ((ExpandableLayout) holder.findViewById(R.id.expandable_layout)).collapse();
        }

        if (position == mSelectPosition) {
            mSelectPosition = -1;
        } else {
            view.setSelected(true);
            expandableLayout.expand();
            mSelectPosition = position;
        }
    }


    @Override
    public void onClick(@NonNull BaseQuickAdapter<Hairstylist, ?> baseQuickAdapter, @NonNull View view, int i) {
        onClickListen.onClick(baseQuickAdapter.getItem(i).getName());
    }
}
