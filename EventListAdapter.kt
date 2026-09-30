package com.patamar.app.ui.explore.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.patamar.app.core.extensions.GrayscaleTransformation
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.patamar.app.data.model.Event
import com.patamar.app.databinding.ItemEventCompactBinding

class EventListAdapter(
    private val onClick: (Event) -> Unit
) : ListAdapter<Event, EventListAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(val binding: ItemEventCompactBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEventCompactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = getItem(position)
        holder.binding.apply {
            tvName.text = event.name
            tvCategory.text = event.category.label
            tvDate.text = DATE_FORMAT.format(event.date)
            tvDistance.text = formatDistance(event.distanceMeters)
            val neutral = ColorDrawable(Color.parseColor("#E4E4E7"))
            ivThumbnail.load(event.imageUrl) {
                crossfade(true)
                placeholder(neutral)
                error(neutral)
                transformations(GrayscaleTransformation(), CircleCropTransformation())
            }
            rowContent.setOnClickListener { onClick(event) }
        }
    }

    private fun formatDistance(meters: Int): String =
        if (meters >= 1000) "%.1f km".format(meters / 1000.0) else "$meters m"

    companion object {
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("d 'de' MMM", Locale("pt", "BR"))

        val DIFF = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
        }
    }
}
