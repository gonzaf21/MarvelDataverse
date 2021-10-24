package com.naisuapps.marveldataverse.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.naisuapps.marveldataverse.MainActivity
import com.naisuapps.marveldataverse.R
import com.naisuapps.marveldataverse.databinding.FragmentCharactersBinding
import com.naisuapps.marveldataverse.ui.adapters.CharactersAdapter
import com.naisuapps.marveldataverse.ui.viewmodel.CharactersFragmentViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [CharactersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CharactersFragment: Fragment() {
    private lateinit var binding: FragmentCharactersBinding
    private val characterFrgViewModel: CharactersFragmentViewModel by viewModels()
    private val pagingAdapter by lazy { CharactersAdapter()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCharactersBinding.inflate(layoutInflater)

        // Enable app bar layout.
        // Set collapsing title character name.
        //(requireActivity() as MainActivity).setTitleCollapseToolbar(true, "Characters")

        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
        binding.rvCharacters.apply {
            adapter = pagingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Activities can use lifecycleScope directly, but Fragments should instead use
        // viewLifecycleOwner.lifecycleScope.
        try {
            viewLifecycleOwner.lifecycleScope.launch {
                characterFrgViewModel.flowCharacters.collectLatest { pagingData ->
                    pagingAdapter.submitData(pagingData)
                }
            }
        } catch (e: Exception) {
            Logger.e("${e.message}")
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = CharactersFragment()
    }
}