package com.xiong.stockdiagnosticdiary

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.xiong.stockdiagnosticdiary.bean.Hairstylist
import com.xiong.stockdiagnosticdiary.bean.XianMu
import com.xiong.stockdiagnosticdiary.databinding.ActivityMainBinding
import com.xiong.stockdiagnosticdiary.db.LearningDatabase
import com.xiong.stockdiagnosticdiary.wwwwwww.HttpUtils
import com.xuexiang.xui.XUI
import com.xuexiang.xui.widget.progress.HorizontalProgressView
import okio.IOException

class MainActivity : AppCompatActivity(), HorizontalProgressView.HorizontalProgressUpdateListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        XUI.initTheme(this)
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.hpvLanguage.setProgressViewUpdateListener(this)
        binding.hpvLanguage.startProgressAnimation()
        supportActionBar?.hide()
        //startActivity(Intent(this,MainActivity2::class.java))
    }

    private fun wwwwwwwwww(){
        HttpUtils.post("xianMu",
            object :HttpUtils.Callback {
                override fun onResponse(response: String?) {
                    Log.e(TAG, "onResponse: $response")
                    val gson= Gson()
                    val itemType = object : TypeToken<List<XianMu>>() {}.type
                    val fromJson = gson.fromJson(response, JsonArray::class.java)
                    val itemList: List<XianMu> = gson.fromJson(fromJson, itemType)

//
                        runOnUiThread {
                            val xianMuDao = LearningDatabase.getLearningDatabase(this@MainActivity).xianMuDao()
                            val selectItemAll = xianMuDao.selectItemAll()
                            if (selectItemAll.size==0||selectItemAll[selectItemAll.size-1].id!=itemList[itemList.size-1].id){
                                xianMuDao.insertItemAll(itemList)
                            }

                        }
//
//
//                    }
                }

                override fun onFailure(e: IOException?) {

                }

            })
//        HttpUtils.register("18363225743","ik----un","2023/05/10", object : HttpUtils.Callback {
//            override fun onResponse(response: String?) {
//                Log.e(TAG, "onResponse---------: $response", )
//            }
//
//            override fun onFailure(e: java.io.IOException?) {
//
//            }
//
//        })

    }
    override fun onHorizontalProgressStart(view: View?) {
        wwwwwwwwww()
        HttpUtils.post("jiShi",
            object :HttpUtils.Callback {
                override fun onResponse(response: String?) {
                    Log.e(TAG, "onResponse: $response")
                    val gson= Gson()
                    val itemType = object : TypeToken<List<Hairstylist>>() {}.type
                    val fromJson = gson.fromJson(response, JsonArray::class.java)
                    val itemList: List<Hairstylist> = gson.fromJson(fromJson, itemType)

                    runOnUiThread {
                        val hairstylistDao = LearningDatabase.getLearningDatabase(this@MainActivity).hairstylistDao()
                        val selectHairstylistAll = hairstylistDao.selectHairstylistAll()
                        if (selectHairstylistAll.size==0||selectHairstylistAll[selectHairstylistAll.size-1]?.id!=itemList[itemList.size-1].id)
                        hairstylistDao.insertHairstylistAll(itemList)
                    }

                }

                override fun onFailure(e: IOException?) {

                }

            })
    }

    @SuppressLint("SetTextI18n")
    override fun onHorizontalProgressUpdate(view: View?, progress: Float) {
        val progressInt = progress.toInt()
        binding.progressTextLanguage.text = "$progressInt%"
    }


    override fun onDestroy() {
        super.onDestroy()
        binding.hpvLanguage.setProgressViewUpdateListener(null)
        binding.hpvLanguage.stopProgressAnimation()
    }
    override fun onHorizontalProgressFinished(view: View?) {
        finish()
        startActivity(Intent(this,LoginActivity::class.java))
//        startActivity(Intent(this,MainActivity2::class.java))
        //startActivity(Intent(this,LoginActivity::class.java))
        val xianMuDao = LearningDatabase.getLearningDatabase(this@MainActivity).xianMuDao()
        val hairstylistDao = LearningDatabase.getLearningDatabase(this@MainActivity).hairstylistDao()
        Log.e(TAG, "onHorizontalProgressFinished: ${hairstylistDao.selectHairstylistAll()}", )
        Log.e(TAG, "onHorizontalProgressFinished: ${xianMuDao.selectItemAll()}", )
        Log.e(TAG, "onHorizontalProgressFinished: ${hairstylistDao.allShopNames}", )
    }
}