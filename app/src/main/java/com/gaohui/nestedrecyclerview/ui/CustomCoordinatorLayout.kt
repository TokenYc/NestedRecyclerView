package com.gaohui.nestedrecyclerview.ui

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomCoordinatorLayout@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    CoordinatorLayout(context, attrs, defStyleAttr)  {

    var shouldIntercept = true





    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        if (shouldIntercept) {
            return super.onStartNestedScroll(child, target, axes, type)
        }else{
            return false
        }
    }
}