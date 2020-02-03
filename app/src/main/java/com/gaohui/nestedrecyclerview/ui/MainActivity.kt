package com.gaohui.nestedrecyclerview.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.gaohui.nestedrecyclerview.R
import com.gaohui.nestedrecyclerview.adapter.MultiTypeAdapter
import com.gaohui.nestedrecyclerview.bean.CategoryBean
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private val mDataList = ArrayList<Any>()

    private val strArray = arrayOf("关注", "推荐", "视频", "直播", "图片", "段子", "精华", "热门")
//    private val strArray = arrayOf("关注")

    var lastBackPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parentRecyclerView.initLayoutManager()

        initData()
    }

    private fun initData() {
        val multiTypeAdapter = MultiTypeAdapter(mDataList)
        for (i in 0..8) {
            mDataList.add("parent item text $i")
        }
        val categoryBean = CategoryBean()
        categoryBean.tabTitleList.clear()
        categoryBean.tabTitleList.addAll(strArray.asList())
        mDataList.add(categoryBean)
        parentRecyclerView.adapter = multiTypeAdapter
        val lp =appBarLayout.layoutParams as CoordinatorLayout.LayoutParams
        parentRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //如果父RecyclerView fling过程中已经到底部，需要让子RecyclerView滑动神域的fling

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(parentRecyclerView.canScrollVertically(-1)){
                  coordinatorLayout.shouldIntercept=false
                }else{
                  coordinatorLayout.shouldIntercept=true
                }
            }
        })
        multiTypeAdapter.notifyDataSetChanged()
        tabLayout.addTab(tabLayout.newTab().setText(R.string.app_name))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.app_name))
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastBackPressedTime < 2000) {
            super.onBackPressed()
        } else {
            parentRecyclerView.scrollToPosition(0)
            Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show()
            lastBackPressedTime = System.currentTimeMillis()
        }
    }
}
