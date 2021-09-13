package dev.parchukidze.datastoreplayground.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.AndroidEntryPoint
import dev.parchukidze.datastoreplayground.R
import dev.parchukidze.datastoreplayground.databinding.ActivityChapterBinding
import dev.parchukidze.datastoreplayground.util.clear
import dev.parchukidze.datastoreplayground.util.hideKeyboard

@AndroidEntryPoint
class ChapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChapterBinding

    private val viewModel by viewModels<ChapterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.ChapterTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityChapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeObservers()

        setListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun subscribeObservers() = with(binding) {
        viewModel.darkThemeState.observe(this@ChapterActivity) { isNightModeEnabled ->
            val defaultMode = if (isNightModeEnabled) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }

            AppCompatDelegate.setDefaultNightMode(defaultMode)
        }

        viewModel.chapterState.observe(this@ChapterActivity) { chapter ->
            tvChapterName.text = "Chapter Name: ${chapter.name}"
            tvDevelopers.text = "Developers: ${chapter.developers}"
            tvSubChapters.text = "Sub-Chapters: ${chapter.subChapters}"
        }
    }

    private fun setListeners() = with(binding) {
        btnToggle.setOnClickListener {
            viewModel.toggleNightMode()
        }

        btnUpdateChapter.setOnClickListener {
            hideKeyboard()

            viewModel.updateChapter(
                name = etChapterName.text.toString(),
                developers = etDevelopers.text.toString().toInt(),
                subChapters = etSubChapters.text.toString().toInt()
            )

            etChapterName.clear()
            etDevelopers.clear()
            etSubChapters.clear()
        }

        btnUpdateDevelopers.setOnClickListener {
            hideKeyboard()

            viewModel.updateDevelopers(
                developers = etDevelopers.text.toString().toInt()
            )

            etDevelopers.clear()
        }
    }
}
