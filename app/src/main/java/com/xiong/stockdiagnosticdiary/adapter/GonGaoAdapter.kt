package com.xiong.stockdiagnosticdiary.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xiong.stockdiagnosticdiary.bean.GonGao
import com.xiong.stockdiagnosticdiary.databinding.Layout3Binding
import com.youth.banner.adapter.BannerAdapter

class GonGaoAdapter(mDatas: List<GonGao>,context: Context) : BannerAdapter<GonGao, GonGaoAdapter.BannerViewHolder>(mDatas) {
    val context2=context
    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = Layout3Binding.inflate(LayoutInflater.from(context2))
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
//        binding.root.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(binding)
    }

    override fun onBindView(holder: BannerViewHolder?, data: GonGao?, position: Int, size: Int) {
        holder?.title?.text=data?.title
        holder?.content?.text=data?.content
        Log.e("Banner", "Current page: $position")
    }



    inner class BannerViewHolder(view: Layout3Binding) : RecyclerView.ViewHolder(view.root) {
        var title:TextView = view.textView9
        var content:TextView = view.textView10
    }
}
