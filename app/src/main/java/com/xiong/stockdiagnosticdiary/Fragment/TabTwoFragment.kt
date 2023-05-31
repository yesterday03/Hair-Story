package com.xiong.stockdiagnosticdiary.Fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xiong.stockdiagnosticdiary.LoginActivity
import com.xiong.stockdiagnosticdiary.LoginActivity.Global.member
import com.xiong.stockdiagnosticdiary.databinding.FragmentTabTwoBinding
import com.xuexiang.xui.widget.dialog.DialogLoader
import com.xuexiang.xutil.data.SPUtils.getSharedPreferences

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabTwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabTwoFragment : Fragment() {
    private  var list:ArrayList<Int> = arrayListOf()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentTabTwoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment15367473302
        binding= FragmentTabTwoBinding.inflate(inflater,container,false)

       binding.tvaaaaaaaa.text=member?.name
       binding.tvb.text=member?.phone
       binding.tvyr.text= "余额:"+member?.balance.toString()
        binding.btnYy.setOnClickListener {
            DialogLoader.getInstance().showConfirmDialog(
                context,
                "确定注销？",
                "确定",
                { dialog: DialogInterface, which: Int ->
                    val sp = getSharedPreferences("config")
                    sp.edit().remove("phone").apply()
                    dialog.dismiss()
                    startActivity(Intent(context, LoginActivity::class.java))
                },
                "取消"
            ) { dialog: DialogInterface, which: Int ->
                dialog.dismiss()
            }
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TabTwoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            TabTwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}