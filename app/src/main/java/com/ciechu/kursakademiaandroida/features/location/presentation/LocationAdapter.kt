package com.ciechu.kursakademiaandroida.features.location.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciechu.kursakademiaandroida.R
import com.ciechu.kursakademiaandroida.features.location.presentation.model.LocationDisplayable
import kotlinx.android.synthetic.main.item_location.view.*

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>(){

    private val locations: MutableList<LocationDisplayable> by lazy {
        mutableListOf<LocationDisplayable>()
    }

    fun setLocation(_location: List<LocationDisplayable>){
        if (locations.isNotEmpty()) locations.clear()
        locations.addAll(_location)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(view)
    }

    override fun getItemCount(): Int = locations.size


    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(locations[position])
    }

    class LocationViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(locationDisplayable: LocationDisplayable){
            with(itemView){
                location_name.text = locationDisplayable.name
                location_dimension.text = locationDisplayable.dimension
                location_type.text = locationDisplayable.type
            }
        }
    }

}