package com.xiong.stockdiagnosticdiary.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiong.stockdiagnosticdiary.bean.Hairstylist;
import com.xiong.stockdiagnosticdiary.databinding.Layout2Binding;

public class RyAd extends BaseQuickAdapter<Hairstylist, RyAd.RyadHolder> {

    @NonNull
    @Override
    protected RyadHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        Layout2Binding binding=Layout2Binding.inflate(LayoutInflater.from(context));
        return new RyadHolder(binding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onBindViewHolder(@NonNull RyadHolder ryadHolder, int i, @Nullable Hairstylist hairstylist) {
        ryadHolder.tv.textView3.setText(hairstylist.getName());
        ryadHolder.tv.tvZw.setText(hairstylist.getHairstylist_garde().getTitle());
    }

    class RyadHolder extends RecyclerView.ViewHolder{
        Layout2Binding tv;
       public RyadHolder(@NonNull Layout2Binding itemView) {
           super(itemView.getRoot());
           tv=itemView;
       }
   }
}
