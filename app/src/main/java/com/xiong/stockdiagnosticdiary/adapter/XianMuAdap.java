package com.xiong.stockdiagnosticdiary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiong.stockdiagnosticdiary.R;
import com.xiong.stockdiagnosticdiary.bean.XianMu;
import com.xiong.stockdiagnosticdiary.databinding.Layout2Binding;

public class XianMuAdap extends BaseQuickAdapter<XianMu, XianMuAdap.XianMuHolder> {

    @Override
    protected void onBindViewHolder(@NonNull XianMuHolder xianMuHolder, int i, @Nullable XianMu xianMu) {
        xianMuHolder.binding.textView3.setText(xianMu.getTitle());
       xianMuHolder.binding.tvZw.setText("商品价格:"+xianMu.getPrice());
       xianMuHolder.binding.tvTj.setText("会员特价:"+xianMu.getMember_price());
       xianMuHolder.binding.tvTj.setTextColor(ContextCompat.getColor(getContext(), R.color.rad));
    }

    @NonNull
    @Override
    protected XianMuHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        Layout2Binding binding=Layout2Binding.inflate(LayoutInflater.from(context));
        return new XianMuHolder(binding);
    }

    class XianMuHolder extends RecyclerView.ViewHolder {
        Layout2Binding binding;
        public XianMuHolder(@NonNull Layout2Binding itemView) {
            super(itemView.getRoot());
            binding=itemView;
        }
    }
}
