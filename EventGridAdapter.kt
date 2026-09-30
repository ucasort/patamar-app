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
import com.patamar.app.core.utils.iconRes
import com.patamar.app.data.model.Event
import com.patamar.app.databinding.ItemEventGridBinding
import java.time.format.DateTimeFormatter
import java.util.Locale

// Card do Explorar (grade de 2 colunas): foto P&B, categoria, título em serifa, data e local.
class EventGridAdapter(
    private val onClick: (Event) -> Unit
) : ListAdapter<Event, EventGridAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(val binding: ItemEventGridBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemEventGridBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = getItem(position)
        holder.binding.apply {
            tvName.text = event.name
            tvCategory.text = event.category.label
            ivCategory.setImageResource(event.category.iconRes())
            tvDate.text = "${DATE_FORMAT.format(event.date)}, ${event.time.hour}h${"%02d".format(event.time.minute)}"
            tvPlace.text = event.address
            ivCover.load(event.imageUrl) {
                crossfade(true)
                placeholder(ColorDrawable(Color.parseColor("#E4E4E7")))
                error(ColorDrawable(Color.parseColor("#E4E4E7")))
                transformations(GrayscaleTransformation())
            }
            root.setOnClickListener { onClick(event) }
        }
    }

    companion object {
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("d 'de' MMM", Locale("pt", "BR"))

        val DIFF = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
        }
    }
}
