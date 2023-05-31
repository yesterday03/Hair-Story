package com.xiong.stockdiagnosticdiary

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.xiong.stockdiagnosticdiary.LoginActivity.Global.GG
import com.xiong.stockdiagnosticdiary.bean.GonGao
import com.xiong.stockdiagnosticdiary.bean.Member
import com.xiong.stockdiagnosticdiary.databinding.ActivityLogin2Binding
import com.xiong.stockdiagnosticdiary.wwwwwww.HttpUtils
import com.xuexiang.xui.XUI
import com.xuexiang.xui.widget.edittext.materialedittext.validation.RegexpValidator
import java.io.IOException
import java.util.Random

class LoginActivity : AppCompatActivity() {
    private  var list:ArrayList<GonGao> = arrayListOf()
    private lateinit var binding: ActivityLogin2Binding
    var tipDialog: QMUITipDialog? =null
    lateinit var yzm:String
    override fun onCreate(savedInstanceState: Bundle?) {
        XUI.initTheme(this)
        super.onCreate(savedInstanceState)
        binding= ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        tipDialog=QMUITipDialog.Builder(this)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
            .setTipWord("正在登录")
            .create()

        yzm="-1"
        gonGao()
        binding.btCountdown4.isEnabled=false
        val sp = getSharedPreferences("config", Context.MODE_PRIVATE)
        val value = sp.getString("phone", "")
        if (value?.isNotEmpty() == true){
            val editable = Editable.Factory.getInstance().newEditable(value)
            binding.etAutoCheck.text=editable
            binding.btCountdown4.isEnabled=true
        }
        binding.etAutoCheck.addValidator(RegexpValidator("手机号不正确", "^1[3456789]\\d{9}\$"))
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
        binding.btCountdown4.setOnClickListener {
            yzm=generateCode()
            Toast.makeText(this,yzm,Toast.LENGTH_SHORT).show()
            binding.btCountdown4.clearFocus()
            binding.etCheck.requestFocus()

        }
        binding.btDl.setOnClickListener {
            if (binding.etAutoCheck.validate()){
                val toString = binding.etCheck.text.toString()
                if (toString?.isEmpty() == true){
                    //Toast.makeText(this,"验证码为空",Toast.LENGTH_SHORT).show()
                }
                if (toString==yzm){

                    tipDialog?.show()
                    HttpUtils.login("selectMemberByphone",binding.etAutoCheck.text.toString(),
                        object : HttpUtils.Callback {
                            override fun onResponse(response: String?) {
                                if (response?.isEmpty()==true){
                                    runOnUiThread {
                                        Toast.makeText(this@LoginActivity,"登陆失败",Toast.LENGTH_SHORT).show()
                                        tipDialog?.dismiss()
                                    }
                                }else{
                                    tipDialog?.dismiss()
                                    val sp = getSharedPreferences("config", Context.MODE_PRIVATE)
                                    sp.edit().putString("phone",binding.etAutoCheck.text.toString()).apply()
                                    val gson= Gson()
                                    var member: Member  = gson.fromJson(response, Member::class.java)
                                    Global.member=member
                                    startActivity(Intent(this@LoginActivity,MainActivity2::class.java))
                                    finish()
                                    Log.e(TAG, "onResponse: ${member}")
                                }

                            }

                            override fun onFailure(e: IOException?) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "登陆失败，清检查网络",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    tipDialog?.dismiss()
                                }

                            }

                        })

                }else{
                    Toast.makeText(this,"验证码错误",Toast.LENGTH_SHORT).show()
                }
            }

        }
        binding.superButton.setOnClickListener { startActivity(Intent(this,RegisterActivity::class.java)) }


    }

    object Global {
        var member: Member?=null
        var GG:ArrayList<GonGao>?=null

    }
    private fun gonGao() {
        HttpUtils.post("showNotice", object : HttpUtils.Callback {
            override fun onResponse(response: String?) {
                if (response != "") {
                    val gson=Gson()
                    val fromJson = gson.fromJson(response, JsonArray::class.java)
                    for (i in 0 until fromJson.size()){
                        val content = fromJson[i].asJsonObject.get("content").asString
                        val title = fromJson[i].asJsonObject.get("title").asString
                        list.add(GonGao(title,content))
                    }
                    GG=list
                }

            }
//15779116365
            override fun onFailure(e: IOException?) {

            }

        })
    }
    fun generateCode(): String {
        val rnd = Random()
        val code = StringBuilder()
        for (i in 0 until 4) {
            code.append(rnd.nextInt(10))
        }
        return code.toString()
    }

}