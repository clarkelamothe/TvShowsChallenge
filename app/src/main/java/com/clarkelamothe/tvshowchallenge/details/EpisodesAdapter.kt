package com.clarkelamothe.tvshowchallenge.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clarkelamothe.tvshowchallenge.R
import com.clarkelamothe.tvshowchallenge.data.models.EpisodeModel
import com.clarkelamothe.tvshowchallenge.databinding.EpisodeItemBinding

class EpisodesAdapter(
    private val episodes: List<EpisodeModel>,
) : RecyclerView.Adapter<EpisodesVH>() {

    override fun getItemCount(): Int = episodes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent, false)
        return EpisodesVH(view)
    }

    override fun onBindViewHolder(holder: EpisodesVH, position: Int) {
        holder.bind(episodes[position])
    }

}

class EpisodesVH(
    view: View,
) : RecyclerView.ViewHolder(view) {

    private val binding = EpisodeItemBinding.bind(view)

    fun bind(show: EpisodeModel) {
        binding.apply {

            show.apply {
                episodeName.text = name

                Glide.with(episodeImage.context)
                    .load(image.medium)
                    .centerInside()
                    .into(episodeImage)
            }
        }
    }
}
