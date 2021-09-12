package dev.parchukidze.datastoreplayground.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.AndroidEntryPoint
import dev.parchukidze.datastoreplayground.R
import dev.parchukidze.datastoreplayground.databinding.ActivityChapterBinding

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

    private fun subscribeObservers() {
        viewModel.darkThemeState.observe(this) { isNightModeEnabled ->
            val defaultMode = if (isNightModeEnabled) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }

            AppCompatDelegate.setDefaultNightMode(defaultMode)
        }
    }

    private fun setListeners() = with(binding) {
        btnToggle.setOnClickListener {
            viewModel.toggleNightMode()
        }
    }
}
