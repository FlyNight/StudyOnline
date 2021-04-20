package com.example.studyonline.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }
    val values: LiveData<Int> = Transformations.map(_index) {
        it
    }
    fun setIndex(index: Int) {
        _index.value = index
    }
}