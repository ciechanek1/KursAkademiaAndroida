package com.ciechu.kursakademiaandroida.features.episodes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciechu.kursakademiaandroida.R
import com.ciechu.kursakademiaandroida.features.episodes.presentation.model.EpisodeDisplayable
import kotlinx.android.synthetic.main.item_episode.view.*

class EpisodeAdapter : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>(){

    private val episodes: MutableList<EpisodeDisplayable> by lazy {
        mutableListOf<EpisodeDisplayable>()
    }

    fun setEpisodes(_episodes: List<EpisodeDisplayable>){
        if (episodes.isNotEmpty()) episodes.clear()
        episodes.addAll(_episodes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_episode, parent, false)
        return EpisodeViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = episodes.size

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    class EpisodeViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(episodeDisplayable: EpisodeDisplayable){
            with(itemView){
                episode_name.text = episodeDisplayable.name
                episode_airDate.text = episodeDisplayable.airDate
                episode_url.text = episodeDisplayable.url
            }
        }
    }
}