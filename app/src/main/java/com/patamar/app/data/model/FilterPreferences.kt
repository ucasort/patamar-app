package com.patamar.app.data.model

data class FilterPreferences(
    val categories: Set<EventCategory> = emptySet(),
    val radiusMeters: Int = 3000,
    // "Com que frequência você costuma sair?" (tela de preferências pós-login)
    val outingFrequency: String? = null
)
