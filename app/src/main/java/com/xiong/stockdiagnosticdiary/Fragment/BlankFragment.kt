package com  .xiong.stockdiagnosticdiary.Fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import com.xiong.stockdiagnosticdiary.adapter.ExpandableListAdapter
import com.xiong.stockdiagnosticdiary.adapter.GonGaoAdapter
import com.xiong.stockdiagnosticdiary.LoginActivity.Global.GG
import com.xiong.stockdiagnosticdiary.MainActivity3
import com.xiong.stockdiagnosticdiary.R
import com.xiong.stockdiagnosticdiary.adapter.XianMuAdap
import com.xiong.stockdiagnosticdiary.YuYueActivity
import com.xiong.stockdiagnosticdiary.bean.GonGao
import com.xiong.stockdiagnosticdiary.bean.Hairstylist
import com.xiong.stockdiagnosticdiary.bean.XianMu
import com.xiong.stockdiagnosticdiary.databinding.FragmentBlankBinding
import com.xiong.stockdiagnosticdiary.db.LearningDatabase
import com.xiong.stockdiagnosticdiary.wwwwwww.HttpUtils
import com.xuexiang.xui.utils.WidgetUtils
import com.xuexiang.xui.widget.dialog.DialogLoader
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.util.BannerUtils
import java.io.IOException
import java.io.Serializable


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

@Suppress("UNUSED_EXPRESSION")
class BlankFragment : Fragment(), BaseQuickAdapter.OnItemClickListener<XianMu> {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    var adapterGG: GonGaoAdapter? = GG?.let { context?.let { it1 -> GonGaoAdapter(it, it1) } }
    private  var list:ArrayList<GonGao> = arrayListOf()
    var expandableListAdapter: ExpandableListAdapter?=null
    val adapter= XianMuAdap()
    private lateinit var binding:FragmentBlankBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { param1 = it.getString(ARG_PARAM1) }
        val tabLayout = requireActivity().findViewById<TabLayout>(R.id.tab_layout1)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Do something when a tab is selected
                if(tab?.text=="发型师"){
                    binding.banner.isVisible=false
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Do something when a tab is unselected
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Do something when a tab is reselected
            }
        })
    }
    private fun initViews() {
        val swipeRefreshLayout: SwipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            // 执行下拉刷新操作
            // 刷新完成后，调用 setRefreshing(false) 停止刷新动画
            HttpUtils.post("jiShi",
                object : HttpUtils.Callback {
                    override fun onResponse(response: String?) {
                        Log.e(TAG, "onResponse: $response")
                        val gson= Gson()
                        val itemType = object : TypeToken<List<Hairstylist>>() {}.type
                        val fromJson = gson.fromJson(response, JsonArray::class.java)
                        val itemList: List<Hairstylist> = gson.fromJson(fromJson, itemType)

                       activity?.runOnUiThread {
                            val hairstylistDao = LearningDatabase.getLearningDatabase(context).hairstylistDao()
                                hairstylistDao.deleteAll()
                                hairstylistDao.insertHairstylistAll(itemList)
                                val allShopNames = hairstylistDao.allShopNames
                                val title = expandableListAdapter?.title
                           if (title != null) {
                               if (allShopNames[allShopNames.size-1]!= title[title.size-1] &&allShopNames.size>title.size)
                                   expandableListAdapter?.add(allShopNames[allShopNames.size-1])
                           }
                                expandableListAdapter?.notifyDataSetChanged()
                        }

                    }

                    override fun onFailure(e: IOException?) {

                    }

                })

            HttpUtils.post("xianMu",
                object :HttpUtils.Callback {
                    override fun onResponse(response: String?) {
                        Log.e(TAG, "onResponse: $response")
                        val gson= Gson()
                        val itemType = object : TypeToken<List<XianMu>>() {}.type
                        val fromJson = gson.fromJson(response, JsonArray::class.java)
                        val itemList: List<XianMu> = gson.fromJson(fromJson, itemType)

//
                        activity?.runOnUiThread {
                            val xianMuDao = LearningDatabase.getLearningDatabase(context).xianMuDao()
                                xianMuDao.deleteAll()
                                xianMuDao.insertItemAll(itemList)
                            val selectItemAll = xianMuDao.selectItemAll()

                            adapter.submitList(selectItemAll)
                            adapter?.notifyDataSetChanged()

                        }
//
//
//                    }
                    }

                    override fun onFailure(e: IOException?) {

                    }

                })

            HttpUtils.post("showNotice", object : HttpUtils.Callback {
                override fun onResponse(response: String?) {
                    if (response !=""&&arguments?.getString(ARG_PARAM1)=="项目") {
                        val gson=Gson()
                        val fromJson = gson.fromJson(response, JsonArray::class.java)
                        val listBarnn:ArrayList<GonGao> = arrayListOf()
                        for (i in 0 until fromJson.size()){
                            val content = fromJson[i].asJsonObject.get("content").asString
                            val title = fromJson[i].asJsonObject.get("title").asString
                            listBarnn.add(GonGao(title,content))
                        }
                        activity?.runOnUiThread {
                            binding.banner.isVisible=true
                            adapterGG?.setDatas(listBarnn)
                            adapterGG?.notifyDataSetChanged()
                            
                        }
                    }else{
                        binding.banner.isVisible=false

                    }


                }

                override fun onFailure(e: IOException?) {

                }

            })
            swipeRefreshLayout.isRefreshing = false
        }


        val string = arguments?.getString(ARG_PARAM1)
        Log.e(TAG, "initViews: $string")
        when(string){
            "项目"->{
                try {
//                    adapterGG=context?.let { GG?.let { it1 -> GonGaoAdapter(it1, it) } }
                    adapterGG = GG?.let { context?.let { it1 -> GonGaoAdapter(it, it1) } }
                    if (adapterGG!=null){
                        binding.banner.isVisible=true
                        binding.banner.setAdapter(adapterGG).indicator = CircleIndicator(context)
//                    binding.indicator.isVisible=true
//                    binding.banner.setIndicator(binding.indicator,false)
                        binding.banner.setIndicatorSelectedWidth(BannerUtils.dp2px(15f))
                        adapterGG?.notifyDataSetChanged()
                    }else {
                        binding.banner.isVisible=false
                    }

                }catch (e:Error){
                    Log.e(TAG, "initViewErr: $e")
                }
//                binding.banner.setIndicator(BaseIndicator(context))
//                binding.banner.indicator = Indicator()
                val xianMuDao = LearningDatabase.getLearningDatabase(context).xianMuDao()
                val selectItemAll = xianMuDao.selectItemAll()
                binding.recyclerView.layoutManager= object : GridLayoutManager(context,1) {}
                binding.recyclerView.adapter=adapter
                adapter.addAll(selectItemAll)
                adapter.setOnItemClickListener(this)
            }
            "发型师"->{
                binding.banner.isVisible=false
                val hairstylistDao = LearningDatabase.getLearningDatabase(context).hairstylistDao()
                val allShopNames = hairstylistDao.allShopNames
//                var title= arrayListOf<String>()
//                for (i in 0 until allShopNames.size){
//                    title.add(allShopNames[i])
//                }
                WidgetUtils.initRecyclerView(binding.recyclerView)

                 expandableListAdapter =
                     ExpandableListAdapter(
                         binding.recyclerView,
                         allShopNames
                     ) { name ->
                         val intent = Intent(context, MainActivity3::class.java)
                         intent.putExtra("name", name)
                         startActivity(intent)
                     }
                binding.recyclerView.adapter=expandableListAdapter
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding= FragmentBlankBinding.inflate(inflater, container, false)
        initViews()

//        gonGao()
        binding.banner.setOnBannerListener { data, position ->
            Log.e(TAG, "onCr------eateView: ${data as GonGao}", )
            val gg=data
            DialogLoader.getInstance().showTipDialog(
                context,
                gg.title,
                gg.content,
                "确定"
            )
        }
        return binding.root

    }

    private fun gonGao() {
        HttpUtils.post("showNotice", object : HttpUtils.Callback {
            override fun onResponse(response: String?) {
                val gson=Gson()
                val fromJson = gson.fromJson(response, JsonArray::class.java)
                for (i in 0 until fromJson.size()){
                    val content = fromJson[i].asJsonObject.get("content").asString
                    val title = fromJson[i].asJsonObject.get("title").asString
                    list.add(GonGao(title,content))
                }
            }

            override fun onFailure(e: IOException?) {

            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun onClick(adapter: BaseQuickAdapter<XianMu, *>, view: View, position: Int) {
        var intent=Intent(context, YuYueActivity::class.java)
        val xianMuDao = LearningDatabase.getLearningDatabase(context).xianMuDao()
        val selectItemAll = xianMuDao.selectItemAll()
        Log.e(TAG, "onClick: ${selectItemAll[position]}")
        intent.putExtra("xianMuId",selectItemAll[position] as Serializable)
        startActivity(intent)
    }
}