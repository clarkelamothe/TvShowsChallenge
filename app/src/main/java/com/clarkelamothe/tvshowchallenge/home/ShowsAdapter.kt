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
    val shows: List<ShowModel>,
    val listener: HomeFragment
): RecyclerView.Adapter<ShowsVH>() {

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

    init {
        itemView.setOnClickListener {
            listener.onShowClicked(adapterPosition)
        }
    }

    fun bind(show: ShowModel) {
        binding.apply {

            show.apply {
                showTitle.text = name
                showDescription.text = summary

                Glide.with(showImage.context)
                    .load(image.medium)
                    .centerInside()
                    .into(showImage)
            }
        }
    }
}
