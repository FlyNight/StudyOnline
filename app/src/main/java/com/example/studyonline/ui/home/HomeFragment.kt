package com.example.studyonline.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.studyonline.R
import com.example.studyonline.data.adapter.OptionLessonsAdapter
import com.example.studyonline.data.bean.DataBean
import com.example.studyonline.data.bean.LessonBean
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        initBanner(root)
        initList(root)
        return root
    }

    private fun initBanner(root: View) {
        val banner: Banner<DataBean, BannerImageAdapter<DataBean>> = root.findViewById(R.id.banner)
        banner.setAdapter (object : BannerImageAdapter<DataBean>(DataBean.testData3) {
            override fun onBindView(holder: BannerImageHolder, data: DataBean, position: Int, size: Int) {
                Glide.with(holder.imageView)
                    .load(data.imageUrl)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .into(holder.imageView)
            }
        }).addBannerLifecycleObserver(this).indicator = CircleIndicator(root.context)
    }

    private fun initList(root: View) {
        val listView: ListView = root.findViewById(R.id.news_list)

        val adapter = activity?.let { OptionLessonsAdapter(it, R.layout.optional_lesson_item, LessonBean.testData1) }
        listView.adapter = adapter
    }
}