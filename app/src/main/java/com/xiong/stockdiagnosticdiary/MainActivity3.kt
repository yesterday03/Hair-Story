package com.xiong.stockdiagnosticdiary

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.xiong.stockdiagnosticdiary.databinding.ActivityMain3Binding
import com.xuexiang.xui.XUI


class MainActivity3 : AppCompatActivity() {

    private lateinit var binding:ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        XUI.initTheme(this)
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.title="发型师详情"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.fhb)
        binding.textView11.text=intent.getStringExtra("name")+":"
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}