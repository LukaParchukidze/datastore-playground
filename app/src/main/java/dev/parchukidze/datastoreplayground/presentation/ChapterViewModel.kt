package dev.parchukidze.datastoreplayground.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.parchukidze.datastoreplayground.data.repository.ChapterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChapterViewModel @Inject constructor(private val chapterRepository: ChapterRepository) :
    ViewModel() {

    private val _darkThemeState = MutableLiveData<Boolean>().apply {
        value = chapterRepository.isDarkThemeEnabled()
    }
    val darkThemeState: LiveData<Boolean> = _darkThemeState

    fun toggleNightMode() {
        viewModelScope.launch {
            val enableDarkTheme = _darkThemeState.value!!

            chapterRepository.toggleDarkTheme(!enableDarkTheme)
            _darkThemeState.value = !enableDarkTheme
        }
    }
}
