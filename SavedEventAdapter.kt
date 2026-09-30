package com.patamar.app.ui.explore.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.patamar.app.core.extensions.GrayscaleTransformation
import com.patamar.app.data.model.Event
import com.patamar.app.databinding.ItemEventSavedBinding
import java.time.format.DateTimeFormatter
import java.util.Locale

// Linha de evento salvo (foto P&B, nome e data): usada na aba Salvos e no Perfil.
class SavedEventAdapter(
    private val onClick: (Event) -> Unit
) : ListAdapter<Event, SavedEventAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(val binding: ItemEventSavedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemEventSavedBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = getItem(position)
        holder.binding.apply {
            tvName.text = event.name
            tvDate.text = "${DATE_FORMAT.format(event.date)}, ${event.time.hour}h${"%02d".format(event.time.minute)}"
            val neutral = ColorDrawable(Color.parseColor("#E4E4E7"))
            ivThumb.load(event.imageUrl) {
                crossfade(true)
                placeholder(neutral)
                error(neutral)
                transformations(GrayscaleTransformation())
            }
            rowContent.setOnClickListener { onClick(event) }
        }
    }

    companion object {
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("d 'de' MMMM", Locale("pt", "BR"))

        val DIFF = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
        }
    }
}
