package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.common.Result
import com.example.myapplication.data.remote.response.HoyaResponse
import com.example.myapplication.data.remote.response.IslandResponse
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var islandAdapter: IslandAdapter
    private lateinit var hoyaAdapter: HoyaAdapter
    private var selectedIsland: IslandResponse? = null

    private var token: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    loginViewModel.getToken.collect {
                        token = it
                        viewModel.listIsland("Bearer $token")
                    }
                }
            }
        }
        observer()
        setupRecyclerView()
    }


    private fun observeIsland() {
        viewModel.stateIsland.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { result ->
                when(result) {
                    is Result.Loading -> {
                        binding.shimmerIsland.visibility = View.VISIBLE
                        binding.shimmerIsland.startShimmer()
                    }
                    is Result.Success -> {
                        binding.shimmerIsland.visibility = View.GONE
                        binding.shimmerIsland.startShimmer()
                        handleIsland(result.data)
                    }
                    is Result.Error -> {
                        binding.shimmerIsland.visibility = View.GONE
                        binding.shimmerIsland.startShimmer()
                        Toast.makeText(requireContext(),"${result.message}" ,Toast.LENGTH_SHORT).show()
                    }
                }
            }.launchIn(lifecycleScope)
    }

    private fun observeHoya() {
        viewModel.stateHoya.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { result ->
                when(result) {
                    is Result.Loading -> {
                        binding.shimmerHoya.visibility = View.VISIBLE
                        binding.shimmerHoya.startShimmer()
                    }
                    is Result.Success -> {
                        binding.shimmerHoya.visibility = View.GONE
                        binding.shimmerHoya.startShimmer()
                        handleHoya(result.data)
                    }
                    is Result.Error -> {
                        binding.shimmerHoya.visibility = View.GONE
                        binding.shimmerHoya.startShimmer()
                        Toast.makeText(requireContext(),"${result.message}" ,Toast.LENGTH_SHORT).show()
                    }
                }
            }.launchIn(lifecycleScope)
    }

    private fun observer() {
        observeIsland()
        observeHoya()

    }

    private fun setupRecyclerView() {
        binding.apply {
            islandAdapter = IslandAdapter(
                onItemClicked = {
                    viewModel.listHoya("Bearer $token", it.id.toString())
                    islandAdapter.setSelectedItem(it)
                    islandAdapter.notifyDataSetChanged()
                },
                context = requireContext()
            )
            rvIsland.adapter = islandAdapter
            rvIsland.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            hoyaAdapter = HoyaAdapter()
            rvHoya.adapter = hoyaAdapter
            rvHoya.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleIsland(data: List<IslandResponse>) {
        if (data.isNotEmpty()) {
            binding.rvIsland.visibility = View.VISIBLE
            selectedIsland = data[0]
            viewModel.listHoya("Bearer $token", "${selectedIsland!!.id}")
            islandAdapter.setSelectedItem(selectedIsland!!)
            islandAdapter.submitList(data)
            islandAdapter.notifyDataSetChanged()
        } else {
            binding.rvIsland.visibility = View.GONE
        }
    }

    private fun handleHoya(data: List<HoyaResponse>) {
        if (data.isNotEmpty()) {
            binding.rvHoya.visibility = View.VISIBLE
            hoyaAdapter.submitList(data)
        } else {
            binding.rvHoya.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}