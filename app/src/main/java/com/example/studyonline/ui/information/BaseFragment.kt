package com.example.studyonline.ui.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseFragment: Fragment() {

    private lateinit var mRoot: View
    private lateinit var unbinder: Unbinder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mRoot == null) {
            val root: View = inflater.inflate(getLayoutId(), container, false)
            initWeight(root)
            mRoot = root
        } else {
            if (mRoot.parent != null) {
                (mRoot.parent as ViewGroup).removeView(mRoot)
            }
        }
        return mRoot
    }

    abstract fun getLayoutId(): Int

    protected fun initWeight(root: View) {
        unbinder = ButterKnife.bind(root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    protected fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
    }
}