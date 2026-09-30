package com.patamar.app.core.utils

import androidx.annotation.DrawableRes
import com.patamar.app.R
import com.patamar.app.data.model.EventCategory

// Ícone de linha de cada categoria (usado nos cards, chips e na tela de preferências).
@DrawableRes
fun EventCategory.iconRes(): Int = when (this) {
    EventCategory.SHOW -> R.drawable.ic_cat_music
    EventCategory.FESTA -> R.drawable.ic_cat_party
    EventCategory.TEATRO -> R.drawable.ic_cat_theater
    EventCategory.ARTE -> R.drawable.ic_cat_art
    EventCategory.ESPORTES -> R.drawable.ic_cat_sport
    EventCategory.GASTRONOMIA -> R.drawable.ic_cat_food
    EventCategory.FEIRA -> R.drawable.ic_cat_fair
    EventCategory.GRATUITO -> R.drawable.ic_cat_free
}
