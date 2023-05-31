package com.xiong.stockdiagnosticdiary

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.jpeng.jptabbar.JPTabBar
import com.jpeng.jptabbar.anno.NorIcons
import com.jpeng.jptabbar.anno.SeleIcons
import com.jpeng.jptabbar.anno.Titles
import com.xiong.stockdiagnosticdiary.Fragment.TabOneFragment
import com.xiong.stockdiagnosticdiary.Fragment.TabTwoFragment
import com.xiong.stockdiagnosticdiary.LoginActivity.Global.GG
import com.xiong.stockdiagnosticdiary.adapter.ViewPagerAdapter
import com.xiong.stockdiagnosticdiary.databinding.ActivityMain2Binding
import com.xuexiang.xui.XUI

class MainActivity2 : AppCompatActivity(), ViewPager.OnPageChangeListener {
    @Titles
    private val mTitles = intArrayOf(R.string.tab1, R.string.tab2)
    private lateinit var mViewPager:ViewPager
    private lateinit var mTabbar: JPTabBar
    private var viewPagerAdapter: ViewPagerAdapter? = null
    @SeleIcons
    private val mSelectIcons = intArrayOf(
        R.drawable.s1,
        R.drawable.w1,
    )
    @NorIcons
    private val mNormalIcons = intArrayOf(
        R.drawable.s2,
        R.drawable.w2,
    )
    private val fragments = arrayOf<Fragment>(
        TabOneFragment.newInstance(),
        TabTwoFragment.newInstance(),

    )

//        private val mPagerAdapter: PagerAdapter = object : PagerAdapter() {
//        override fun isViewFromObject(view: View, `object`: Any): Boolean {
//            return view === `object`
//        }
//
//        override fun getCount(): Int {
//            return mTitles.size
//        }
//
//        override fun instantiateItem(container: ViewGroup, position: Int): Any {
//            val view: View = getPageView(getString(mTitles[position])) as View
//            val params = ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT
//            )
//            container.addView(view, params)
//            return view
//        }
//        private val mPageMap: MutableMap<String, View> = HashMap()
//        private fun getPageView(pageName: String): View? {
//            var view: View? = mPageMap.get(pageName)
//            if (view == null) {
//                val textView = TextView(this@MainActivity2)
////                textView.setTextAppearance(this@MainActivity2, R.style.TextStyle_Content_Match)
//                textView.gravity = Gravity.CENTER
//                textView.text = String.format("这个是%s页面的内容", pageName)
//                view = textView
//                mPageMap.put(pageName, view)
//            }
//            return view
//        }
//        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//            container.removeView(`object` as View)
//        }
//    }
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        mViewPager=binding.viewPager
//        mTabbar=binding.tabbar
        initViews()
        Log.e(TAG, "GG: $GG", )
    }
    protected fun initViews() {
//        mTabbar.setTitles(*mTitles)
//        mTabbar.setNormalIcons(*mNormalIcons)
//        mTabbar.setSelectedIcons(*mSelectIcons)
//        mTabbar.generate()15779116365
//
//        //页面可以滑动
//        mTabbar.setGradientEnable(true)
//        mTabbar.setPageAnimateEnable(true)
//        mTabbar.setTabTypeFace(XUI.getDefaultTypeface())
//        mViewPager.setAdapter(mPagerAdapter)
//        mTabbar.setContainer(mViewPager)
        binding.jpTabBar.setContainer(binding.viewPager)
        //页面可以滑动
        binding.jpTabBar.setGradientEnable(false)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments)
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.offscreenPageLimit = 4
        binding.viewPager.addOnPageChangeListener(this)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }


}