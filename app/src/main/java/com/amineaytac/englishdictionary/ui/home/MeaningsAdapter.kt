package com.amineaytac.englishdictionary.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amineaytac.englishdictionary.core.data.model.Meaning
import com.amineaytac.englishdictionary.databinding.ItemMeaningsBinding

class MeaningsAdapter : ListAdapter<Meaning, MeaningsAdapter.ViewHolder>(DiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemMeaningsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Meaning) = with(binding) {

            partOfSpeechText.text = item.partOfSpeech

            if (item.definitions.isEmpty()) {
                definitionText.visibility = View.GONE
                textView2.visibility = View.GONE
            } else {
                definitionText.text = item.definitions.mapIndexed { index, definition ->
                    "${index + 1}. ${definition.definition}"
                }.joinToString("\n")
                definitionText.visibility = View.VISIBLE
                textView2.visibility = View.VISIBLE
            }

            if (item.synonyms.isEmpty()) {
                synonymsTextHeader.visibility = View.GONE
                synonymsText.visibility = View.GONE
            } else {
                synonymsTextHeader.visibility = View.VISIBLE
                synonymsText.visibility = View.VISIBLE
                synonymsText.text = item.synonyms.joinToString(", ")
            }

            if (item.antonyms.isEmpty()) {
                antonymsTextHeader.visibility = View.GONE
                antonymsText.visibility = View.GONE
            } else {
                antonymsTextHeader.visibility = View.VISIBLE
                antonymsText.visibility = View.VISIBLE
                antonymsText.text = item.antonyms.joinToString(", ")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMeaningsBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallBack : DiffUtil.ItemCallback<Meaning>() {
        override fun areItemsTheSame(oldItem: Meaning, newItem: Meaning): Boolean {
            return oldItem.partOfSpeech == newItem.partOfSpeech
        }

        override fun areContentsTheSame(oldItem: Meaning, newItem: Meaning): Boolean {
            return oldItem == newItem
        }
    }
}