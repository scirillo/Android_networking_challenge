package com.example.android.networkconnect.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.networkconnect.api.domain.Charter
import com.example.android.networkconnect.databinding.CharacterListItemBinding

class CharacterListAdapter :
    ListAdapter<Charter, CharacterListAdapter.CharacterViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Charter>() {
        override fun areItemsTheSame(oldItem: Charter, newItem: Charter): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Charter, newItem: Charter): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private var onItemClickListener: ((Charter) -> Unit)? = null
    fun setOnItemClickListener(onItemClickListener: (Charter) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterListItemBinding.inflate(LayoutInflater.from(parent.context))
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(characterViewHolder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        characterViewHolder.bind(character)
    }

    inner class CharacterViewHolder(private val binding: CharacterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Charter) {
            binding.characterName.text = character.name
            binding.characterImage.load(character.imageUrl)
            binding.characterSpecie.text = character.species
            binding.characterType.text = character.type
        }
    }
}