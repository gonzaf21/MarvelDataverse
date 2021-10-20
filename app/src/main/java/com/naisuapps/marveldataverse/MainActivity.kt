package com.naisuapps.marveldataverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import coil.Coil
import coil.ImageLoader
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.google.android.material.appbar.AppBarLayout
import com.naisuapps.marveldataverse.databinding.ActivityMainBinding
import com.naisuapps.marveldataverse.ui.viewmodel.CharacterViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load characters list
        characterViewModel.onCreate()

        // Update of view elements with var of viewModel observed in order to change its value in view after hearing.
        characterViewModel.characterModel.observe(this, Observer { currentCharacter ->
            binding.tvMain.text = currentCharacter.toString()
            binding.ivCtlMain.load(currentCharacter.thumbnail.toString()) {
                crossfade(true)
            }
            setTitleCollapseToolbar(currentCharacter.name)
            Logger.i("Current character -> $currentCharacter")

            /*when(binding.switchMain.isChecked) {
                true -> binding.ctlMain.contentScrim = this@MainActivity.imageLoader.execute(ImageRequest.Builder(this@MainActivity)
                    .data("https://www.example.com/image.jpg")
                    .build()).drawable
            }*/
        })

        // Loading observable.
        characterViewModel.isLoading.observe(this, {
            binding.pbMain.isVisible = it
        })

        // Change character on click container.
        binding.frameMainContainer.setOnClickListener {
            characterViewModel.randomCharacter()
        }
    }

    override fun onResume() {
        super.onResume()
        //setTitleAndNavigationToolbar("jebueno", false)
    }

    private fun setTitleCollapseToolbar(title: String) {
        binding.ctlMain.title = title
    }

    private fun setTitleAndNavigationToolbar(title: String, isBackButtonEnabled: Boolean = false) {
        setSupportActionBar(binding.toolbarMain)
        setTitleCollapseToolbar(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(isBackButtonEnabled)
        supportActionBar?.setDisplayShowHomeEnabled(isBackButtonEnabled)

        Logger.d("Title toolbar set to: $title | isBackButtonEnabled: $isBackButtonEnabled")
    }
}