package com.example.studyonline.data.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.studyonline.R
import com.example.studyonline.data.bean.NewsBean

class NewsAdapter(activity: Activity, private val resourceId: Int, data: List<NewsBean>): ArrayAdapter<NewsBean>(activity, resourceId, data) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val newsImage: ImageView = view.findViewById(R.id.image)
        val newsTitle: TextView = view.findViewById(R.id.title)
        val newsText: TextView = view.findViewById(R.id.text)
        val news = getItem(position)
        if(news!=null) {
            Glide.with(context)
                .load(news.imageUri)
                .into(newsImage)
            newsTitle.text = news.title
            newsText.text = news.text
        }
        return view
    }

}