package com.patamar.app.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patamar.app.core.security.EncryptedPrefsManager
import com.patamar.app.data.model.Event
import com.patamar.app.data.model.User
import com.patamar.app.data.repository.EventRepository
import com.patamar.app.data.repository.FilterRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import com.patamar.app.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    eventRepository: EventRepository,
    filterRepository: FilterRepository,
    val prefsManager: EncryptedPrefsManager
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    val isGuest: Boolean get() = prefsManager.isGuestSession()

    // Eventos salvos do usuário logado (o Perfil mostra os primeiros) e nº de categorias que ele curte.
    val savedEvents: StateFlow<List<Event>> = (
        prefsManager.getSessionUserId()?.takeIf { !isGuest }
            ?.let { eventRepository.observeSavedEvents(it) } ?: flowOf(emptyList())
        ).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favoriteCategories: Int = filterRepository.getFilters().categories.size

    init {
        val userId = prefsManager.getSessionUserId()
        if (userId != null && !isGuest) {
            viewModelScope.launch { _user.value = userRepository.findById(userId) }
        }
    }

    fun setNotificationsEnabled(enabled: Boolean) = prefsManager.setNotificationsEnabled(enabled)

    fun areNotificationsEnabled(): Boolean = prefsManager.areNotificationsEnabled()

    fun logout() = prefsManager.clearSession()
}
