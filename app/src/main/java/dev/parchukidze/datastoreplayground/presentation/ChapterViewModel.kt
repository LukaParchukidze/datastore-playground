package dev.parchukidze.datastoreplayground.presentation

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.parchukidze.datastoreplayground.Chapter
import dev.parchukidze.datastoreplayground.data.repository.ChapterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChapterViewModel @Inject constructor(private val chapterRepository: ChapterRepository) :
    ViewModel() {

    val darkThemeState: LiveData<Boolean> = chapterRepository.isDarkThemeEnabled().asLiveData()

    val chapterState: LiveData<Chapter> = chapterRepository.getChapterFlow().asLiveData()

    fun toggleNightMode() {
        viewModelScope.launch {
            chapterRepository.toggleDarkTheme()
        }
    }

    fun updateChapter(name: String, developers: Int, subChapters: Int) {
        viewModelScope.launch {
            chapterRepository.updateChapter(name, developers, subChapters)
        }
    }

    fun updateDevelopers(developers: Int){
        viewModelScope.launch {
            chapterRepository.updateDevelopers(developers)
        }
    }
}
