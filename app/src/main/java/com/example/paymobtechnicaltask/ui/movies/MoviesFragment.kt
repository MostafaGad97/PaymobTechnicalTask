package com.example.paymobtechnicaltask.ui.movies

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paymobtechnicaltask.R
import com.example.paymobtechnicaltask.databinding.FragmentMoviesBinding
import com.example.paymobtechnicaltask.ui.base.BaseFragment

class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val viewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}