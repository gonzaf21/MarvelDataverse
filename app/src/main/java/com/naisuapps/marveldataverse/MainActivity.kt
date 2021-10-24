package com.naisuapps.marveldataverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import coil.Coil
import coil.ImageLoader
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.google.android.material.appbar.AppBarLayout
import com.naisuapps.marveldataverse.databinding.ActivityMainBinding
import com.naisuapps.marveldataverse.ui.adapters.ComicsAdapter
import com.naisuapps.marveldataverse.ui.view.CharactersFragment
import com.naisuapps.marveldataverse.ui.viewmodel.CharacterViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add fragment instance to container.
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_main, CharactersFragment.newInstance())
            .commit()


        // Load characters list
        /*characterViewModel.onCreate()
        setUpRecyclerView(binding.switchToggleSpanCount.isChecked, true)

        // Update of view elements with var of viewModel observed in order to change its value in view after hearing.
        characterViewModel.characterModel.observe(this, Observer { currentCharacter ->
            // Enable app bar layout.
            binding.appBarLayout.isVisible = true
            // Set collapsing title character name.
            setTitleCollapseToolbar(currentCharacter.name)
            // Load collapsing bar image.
            binding.ivCtlMain.load(currentCharacter.thumbnail.toString()) { crossfade(true) }
            // Enable card view container.
            binding.cardContainerMain.isVisible = true
            // Set description on card.
            binding.tvMain.text =
                if (currentCharacter.description.isNotEmpty()) currentCharacter.description
                else getString(R.string.character_description_empty)

            Logger.i("Current character -> $currentCharacter")
        })

        // Update on dynamic span count.
        characterViewModel.dynamicSpanCount.observe(this, Observer { dynamicSpanCount ->
            setUpRecyclerView(dynamicSpanCount)
        })

        // Update comics list value.
        characterViewModel.comicsModel.observe(this, Observer { comicsList ->
            binding.linearComicsTitleSwitch.isVisible = !comicsList.isNullOrEmpty()

            (binding.rvComics.adapter as ComicsAdapter).updateComicsList(comicsList)
        })

        // Loading observable.
        characterViewModel.isLoading.observe(this, {
            binding.pbMain.isVisible = it
        })

        // Change character on click container.
        binding.cardContainerMain.setOnClickListener {
            characterViewModel.randomCharacter()
        }

        // Change dynamic span count.
        binding.switchToggleSpanCount.setOnClickListener {
            characterViewModel.changeDynamicSpanCount()
        }*/
    }

    private fun setUpRecyclerView(dynamicSpanCount: Boolean, isFirstTime: Boolean = false) {
        /*val dynamicSpanCountInt = when (dynamicSpanCount) {
            true -> 3
            false -> 2
        }
        // Recyclerview comics.
        // Initialize staggered grid layout manager
        StaggeredGridLayoutManager(
            dynamicSpanCountInt, // span count
            StaggeredGridLayoutManager.VERTICAL // orientation
        ).apply {
            // Specify the layout manager for recycler view
            binding.rvComics.layoutManager = this
        }

        // Finally, data bind the recycler view with adapter
        if (isFirstTime) {
            binding.rvComics.adapter = ComicsAdapter(emptyList())
        }*/
    }

    override fun onResume() {
        super.onResume()
        //setTitleAndNavigationToolbar("jebueno", false)
    }

    fun setTitleCollapseToolbar(isAppBarLayoutVisible: Boolean = true, title: String) {
        binding.appBarLayout.isVisible = isAppBarLayoutVisible
        binding.ctlMain.title = title
    }

    private fun setTitleAndNavigationToolbar(title: String, isBackButtonEnabled: Boolean = false) {
        setSupportActionBar(binding.toolbarMain)
        setTitleCollapseToolbar(true, title)
        supportActionBar?.setDisplayHomeAsUpEnabled(isBackButtonEnabled)
        supportActionBar?.setDisplayShowHomeEnabled(isBackButtonEnabled)

        Logger.d("Title toolbar set to: $title | isBackButtonEnabled: $isBackButtonEnabled")
    }
}