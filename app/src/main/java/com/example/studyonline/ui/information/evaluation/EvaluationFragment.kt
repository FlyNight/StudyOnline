package com.example.studyonline.ui.information.evaluation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studyonline.R

class EvaluationFragment : Fragment() {

    companion object {
        fun newInstance() = EvaluationFragment()
    }

    private lateinit var viewModel: EvaluationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evaluation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EvaluationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}