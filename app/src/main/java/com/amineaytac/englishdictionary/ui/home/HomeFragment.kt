package com.amineaytac.englishdictionary.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amineaytac.englishdictionary.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val meaningsAdapter by lazy { MeaningsAdapter() }
    private val phoneticsAdapter by lazy { PhoneticsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindPhoneticsAdapter()
        bindMeaningsAdapter()
        observeUi()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.takeIf { it.isNotBlank() }?.let {
                    viewModel.setSearchQuery(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    viewModel.setSearchQuery("")
                }
                return true
            }
        })
    }

    private fun bindMeaningsAdapter() = with(binding) {

        rvMeanings.layoutManager = LinearLayoutManager(requireContext())
        rvMeanings.adapter = meaningsAdapter
    }

    private fun bindPhoneticsAdapter() = with(binding) {

        rvPhonetics.layoutManager =
            LinearLayoutManager(requireContext())
        rvPhonetics.adapter = phoneticsAdapter
    }

    private fun observeUi() = with(binding) {
        lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle).collect { uiState ->
                when {
                    uiState.isError -> {
                        textView3.visibility = View.GONE
                    }

                    uiState.isLoading -> {
                        phoneticProgress.visibility = View.VISIBLE
                        textView3.visibility = View.GONE
                    }

                    else -> {
                        phoneticProgress.visibility = View.GONE
                        meaningsAdapter.submitList(uiState.words.flatMap { it.meanings })
                        phoneticsAdapter.submitList(
                            uiState.words
                                .flatMap { it.phonetics }
                                .distinctBy { it.text }
                        )
                        textView3.visibility =
                            if (uiState.words.isNotEmpty()) View.VISIBLE else View.GONE
                    }
                }
            }
        }
    }
}