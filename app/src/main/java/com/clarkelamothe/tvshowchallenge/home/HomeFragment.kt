package com.clarkelamothe.tvshowchallenge.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.clarkelamothe.tvshowchallenge.R
import com.clarkelamothe.tvshowchallenge.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: ShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getShows()

        setupObservers()
        setupSearchQuery()
    }

    private fun setupObservers() {
        viewModel.showsState.observe(viewLifecycleOwner) {
            when(it) {
                is ShowsState.Error -> {
                    Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                }
                is ShowsState.Loading -> Toast.makeText(this.context, "Loading", Toast.LENGTH_SHORT).show()

                is ShowsState.Success -> {
                    adapter = ShowsAdapter(it.shows, this)
                    binding.rvShowsList.adapter = adapter
                }
            }
        }
    }

    fun onShowClicked(position: Int) {
        val clickedItem = adapter.shows[position]
        val bundle = bundleOf(
            "name" to clickedItem.name,
            "desc" to clickedItem.summary,
            "id" to clickedItem.id,
            "image" to clickedItem.image.medium
        )
        findNavController().navigate(R.id.goToDetailsScreen, bundle)
    }

    private fun setupSearchQuery() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.getShowsByQuery(it)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}