package com.example.studyonline.ui.gallery

import android.app.Activity
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.studyonline.R
import com.example.studyonline.adapter.LessonsAdapter
import com.example.studyonline.data.bean.LessonBean

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProvider(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        initList(root)
        activity?.startService(Intent(context, TimeService::class.java))
        return root
    }
    inner class TimeChangedReceiver(): BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val listView: ListView? = view?.findViewById(R.id.lesson_list)
            val adapter = activity?.let { LessonsAdapter(it, R.layout.lesson_item, LessonBean.testData1) }
            if (listView != null) {
                listView.adapter = adapter
            }
            Toast.makeText(context,"Time Changed!", Toast.LENGTH_LONG).show()
        }

    }

    inner class TimeService: Service() {
        override fun onBind(intent: Intent?): IBinder? {
            return null
        }
        override fun onCreate() {
            super.onCreate()
            val receiver = TimeChangedReceiver()
            registerReceiver(receiver, IntentFilter(Intent.ACTION_TIME_TICK))
        }
    }

    private fun initList(root: View) {
        val listView: ListView = root.findViewById(R.id.lesson_list)
        val adapter = activity?.let { LessonsAdapter(it, R.layout.lesson_item, LessonBean.testData1) }
        listView.adapter = adapter
    }
}