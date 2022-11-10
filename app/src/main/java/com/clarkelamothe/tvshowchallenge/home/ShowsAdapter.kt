package com.clarkelamothe.tvshowchallenge.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clarkelamothe.tvshowchallenge.R
import com.clarkelamothe.tvshowchallenge.data.models.ShowModel
import com.clarkelamothe.tvshowchallenge.databinding.ShowItemBinding

class ShowsAdapter(
    private val shows: List<ShowModel>,
    val listener: HomeFragment
): RecyclerView.Adapter<ShowsVH>() {

    interface OnShowListener {
        fun onShowClicked(position: Int)
    }

    override fun getItemCount(): Int = shows.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)
        return ShowsVH(view, listener)
    }

    override fun onBindViewHolder(holder: ShowsVH, position: Int) {
        holder.bind(shows[position])
    }

}

class ShowsVH(
    view: View,
    private val listener: HomeFragment
): RecyclerView.ViewHolder(view){

    private val binding = ShowItemBinding.bind(view)

    fun bind(show: ShowModel) {
        binding.apply {
            showTitle.text = show.name
            showDescription.text = show.summary

            Glide.with(showImage.context)
                .load(show.image.medium)
                .centerInside()
                .into(showImage)

        }
    }
}
