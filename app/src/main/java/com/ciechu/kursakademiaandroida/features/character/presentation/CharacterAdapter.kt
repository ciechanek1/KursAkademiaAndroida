package com.ciechu.kursakademiaandroida.features.character.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ciechu.kursakademiaandroida.R
import com.ciechu.kursakademiaandroida.features.character.presentation.model.CharacterDisplayable
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val characters: MutableList<CharacterDisplayable> by lazy {
        mutableListOf<CharacterDisplayable>()
    }

    fun setCharacters(_characters: List<CharacterDisplayable>){
        if (characters.isNotEmpty()) characters.clear()
        characters.addAll(_characters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(characterDisplayable: CharacterDisplayable){
            with(itemView) {
                character_name.text = characterDisplayable.name
                character_species.text = characterDisplayable.species
                character_status.text = characterDisplayable.status
                Glide.with(this)
                    .load(characterDisplayable.image)
                    .into(this.character_image)
            }
        }
    }
}

