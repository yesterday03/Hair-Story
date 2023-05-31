package com.xiong.stockdiagnosticdiary

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xiong.stockdiagnosticdiary.databinding.ActivityRegisterBinding
import com.xiong.stockdiagnosticdiary.wwwwwww.HttpUtils
import com.xuexiang.xui.XUI
import com.xuexiang.xutil.data.DateUtils
import com.xuexiang.xui.utils.XToastUtils
import com.xuexiang.xui.widget.edittext.materialedittext.validation.RegexpValidator
import com.xuexiang.xui.widget.picker.widget.TimePickerView
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder
import java.io.IOException
import java.util.Date
import java.util.Random

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var yzm:String
    var register=false
    private var mDatePicker: TimePickerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        XUI.initTheme(this)
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        yzm="-1"
        supportActionBar!!.apply {
            title="注册"
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.fhb)
        }
        binding.btCountdown4.setOnClickListener {
            yzm=generateCode()
            Toast.makeText(this,yzm, Toast.LENGTH_SHORT).show()
        }
        binding.btnDateBottom.setOnClickListener { showDatePicker() }
        binding.etAutoCheck.addValidator(RegexpValidator("手机号不正确", "^1[3456789]\\d{9}\$"))
//        binding.etCheck.addValidator(RegexpValidator("验证码不正确", "^.{1,4}\$"))
        binding.etName.addValidator(RegexpValidator("名称不正确", "^.{1,10}\$"))
        binding.superButton.setOnClickListener {
            if (binding.etAutoCheck.validate()&&binding.etCheck.validate()&&binding.etName.validate()){
                if (!register){
                    Toast.makeText(this@RegisterActivity,"请选择出生日期",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (binding.etCheck.text.toString()!=yzm){
                     Toast.makeText(this@RegisterActivity,"验证码不正确",Toast.LENGTH_SHORT).show()
                }
                val toString = binding.btnDateBottom.text.toString()
                val formattedDateStr = toString.replace("-", "/")
                HttpUtils.register(binding.etAutoCheck.text.toString(),
                    binding.etName.text.toString(),
                    formattedDateStr,
                    object : HttpUtils.Callback {
                        override fun onResponse(response: String?) {
                            Log.e(TAG, "onResponse: +++++${response.toString()}", )
                            runOnUiThread{
                                if (response.toString() == "添加成功！！请进行查看"){
                                    finish()
                                    Toast.makeText(this@RegisterActivity,"注册成功",Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(this@RegisterActivity,"注册失败",Toast.LENGTH_SHORT).show()
                                }
                            }
                            
                        }
//                        18166325743
                        override fun onFailure(e: IOException?) {
                            runOnUiThread {
                                Toast.makeText(this@RegisterActivity,"注册失败,请检查网络",Toast.LENGTH_SHORT).show()
                            }

                        }

                    }
                )
            }
        }
        binding.btCountdown4.isEnabled=false
        binding.etAutoCheck.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                binding.btCountdown4.isEnabled=binding.etAutoCheck.validate()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // do nothing
            }
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
    fun generateCode(): String {
        val rnd = Random()
        val code = StringBuilder()
        for (i in 0 until 4) {
            code.append(rnd.nextInt(10))
        }
        return code.toString()
    }
    private fun showDatePicker() {
        if (mDatePicker == null) {
            mDatePicker = TimePickerBuilder(
                this
            ) { date: Date?, v: View? ->
                val date2String = DateUtils.date2String(date, DateUtils.yyyyMMdd.get())
                Log.e(TAG, "showDatePicker: $date2String", )
//                XToastUtils.toast(date2String)
                binding.btnDateBottom.text=date2String
                register=true
            }
                .setTimeSelectChangeListener { date: Date? ->
                    Log.i(
                        "pvTime",
                        "onTimeSelectChanged"
                    )
                }
                .setTitleText("日期选择")
                .build()
            //            // 这样设置日月年显示
//            mDatePicker.getWheelTime().getView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        mDatePicker?.show()
    }
}