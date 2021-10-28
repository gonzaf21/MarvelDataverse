package com.naisuapps.marveldataverse.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.naisuapps.marveldataverse.MainActivity
import com.naisuapps.marveldataverse.R
import com.naisuapps.marveldataverse.data.model.ResourceState
import com.naisuapps.marveldataverse.data.model.characters.Character
import com.naisuapps.marveldataverse.databinding.FragmentCharactersBinding
import com.naisuapps.marveldataverse.ui.adapters.CharactersAdapter
import com.naisuapps.marveldataverse.ui.viewmodel.CharactersFragmentViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [CharactersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private val characterFrgViewModel: CharactersFragmentViewModel by viewModels()
    private val pagingAdapter by lazy { CharactersAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCharactersBinding.inflate(layoutInflater)

        // Enable app bar layout.
        // Set collapsing title character name.
        //(requireActivity() as MainActivity).setTitleCollapseToolbar(true, "Characters")

        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
        /*
 * Progress Updater
 * */
        pagingAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
            // Show ProgressBar
                Logger.i("LOADING")
            else {
                // Hide ProgressBar
                Logger.i("NOT LOADING")
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.rvCharacters.apply {
            adapter = pagingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        // Call to viewModel method.
        characterFrgViewModel.getCharactersData()

        // Activities can use lifecycleScope directly, but Fragments should instead use
        // viewLifecycleOwner.lifecycleScope.
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            characterFrgViewModel.charactersState.collect { pagingData ->
                when (pagingData) {
                    is ResourceState.Success -> {
                        Logger.i("SUCCESS STATEFLOW -> ${pagingData.data}")
                        pagingAdapter.submitData(pagingData.data)
                    }
                    is ResourceState.Error -> {
                        Logger.e("ERROR STATEFLOW -> ${pagingData.msg}")
                    }

                    is ResourceState.Loading -> {
                        Logger.i("LOADING STATEFLOW")
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharactersFragment()
    }
}