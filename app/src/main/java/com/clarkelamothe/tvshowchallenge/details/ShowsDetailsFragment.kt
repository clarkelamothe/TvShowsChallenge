package com.clarkelamothe.tvshowchallenge.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.clarkelamothe.tvshowchallenge.databinding.FragmentShowsDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowsDetailsFragment : Fragment() {

    private var _binding: FragmentShowsDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShowsDetailsViewModel by viewModels ()
    private lateinit var adapter: EpisodesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
        setupObservers()
        arguments?.getInt("id")?.let { getEpisodesByShowId(it) }
    }

    private fun setData() {
        arguments?.apply {
            binding.name.text = getString("name")
            binding.description.text = getString("desc")

            Glide.with(binding.image.context)
                .load(getString("image"))
                .centerInside()
                .into(binding.image)
        }
    }

    private fun setupObservers() {
        viewModel.episodesState.observe(viewLifecycleOwner) {
            when (it) {
                is EpisodesState.Error -> {
                    Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                }
                is EpisodesState.Loading -> Toast.makeText(
                    this.context,
                    "Loading",
                    Toast.LENGTH_SHORT
                ).show()

                is EpisodesState.Success -> {
                    adapter = EpisodesAdapter(it.episodes)
                    binding.rvEpisodes.adapter = adapter
                }
            }
        }
    }

    private fun getEpisodesByShowId(showId: Int) {
        viewModel.getEpisodesByShowId(showId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}