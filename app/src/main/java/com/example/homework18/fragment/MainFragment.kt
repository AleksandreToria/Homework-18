package com.example.homework18.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework18.adapter.Adapter
import com.example.homework18.databinding.FragmentMainBinding
import com.example.homework18.viewmodel.ViewModel
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val viewModel: ViewModel by viewModels {
        ViewModel.Factory
    }

    private var adapter = Adapter()

    override fun bindViewActionListener() {
        observeViewModel()
    }

    override fun setUp() {
        setupRecyclerView()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getData().collect { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
        }
    }
}

