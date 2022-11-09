package com.clarkelamothe.tvshowchallenge.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clarkelamothe.tvshowchallenge.R
import com.clarkelamothe.tvshowchallenge.databinding.FragmentShowsDetailsBinding

class ShowsDetailsFragment : Fragment() {

    private var _binding: FragmentShowsDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ShowsDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShowsDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}