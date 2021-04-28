package com.example.studyonline.ui.gallery

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
import com.example.studyonline.activitys.MainActivity
import com.example.studyonline.R
import com.example.studyonline.data.adapter.LessonsAdapter
import com.example.studyonline.data.bean.LessonBean
import com.example.studyonline.data.holder.OptionalLessonsHolder

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
        return root
    }

    private fun initList(root: View) {
        val listView: ListView = root.findViewById(R.id.lesson_list)
        if (MainActivity.id == -1) {
            Toast.makeText(context,"Please Login First!", Toast.LENGTH_SHORT).show()
            return
        }
        listView.adapter = getAdapter()
    }
    private fun getAdapter(): LessonsAdapter {
        var adapter: LessonsAdapter? = null
        val t1 = Thread {
            adapter = activity?.let {
                context?.let { it1 -> OptionalLessonsHolder.getDataFromDatabase(it1, MainActivity.userId) }
                    ?.let { it2 ->
                        LessonsAdapter(it, R.layout.item_lesson,
                            it2
                        )
                    }
            }
        }
        t1.start()
        t1.join()
        return adapter!!
    }
}