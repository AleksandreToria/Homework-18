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

    private val viewModel: ViewModel by viewModels()
    private var adapter = Adapter()

    override fun bindViewActionListener() {
        // Observe the data from the ViewModel and update the adapter
        observeViewModel()
    }

    override fun setUp() {
        // Setup the RecyclerView
        setupRecyclerView()

        // Trigger the data loading
        viewModel.getData()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            // Repeat the block only when the view is started
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Collect the data from the ViewModel's flow
                viewModel.getData().collect { pagingData ->
                    // Submit the new data to the adapter
                    adapter.submitData(pagingData)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }
}
