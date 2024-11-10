package com.amineaytac.englishdictionary.ui.home

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amineaytac.englishdictionary.core.data.model.Phonetic
import com.amineaytac.englishdictionary.databinding.ItemPhoneticsBinding

class PhoneticsAdapter : ListAdapter<Phonetic, PhoneticsAdapter.ViewHolder>(DiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemPhoneticsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var mediaPlayer: MediaPlayer? = null

        fun bind(item: Phonetic) = with(binding) {
            phoneticText.text = item.text

            if (item.audio.isEmpty()) {
                phoneticAudio.visibility = View.GONE
            } else {
                phoneticAudio.visibility = View.VISIBLE
            }

            phoneticAudio.setOnClickListener {
                if (item.audio.isNotEmpty()) {
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer().apply {
                        setDataSource(item.audio)
                        prepareAsync()
                        setOnPreparedListener { start() }
                        setOnCompletionListener { release() }
                        setOnErrorListener { _, _, _ -> true }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhoneticsBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallBack : DiffUtil.ItemCallback<Phonetic>() {
        override fun areItemsTheSame(oldItem: Phonetic, newItem: Phonetic): Boolean {
            return oldItem.text == newItem.text
        }

        override fun areContentsTheSame(oldItem: Phonetic, newItem: Phonetic): Boolean {
            return oldItem == newItem
        }
    }
}