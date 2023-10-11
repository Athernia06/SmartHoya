package com.example.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.common.Result
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var islandAdapter: IslandAdapter

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

        observer()
        setupRecyclerView()
    }

    private fun observer() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    loginViewModel.getToken.collect {
                        token = it
                    }
                }
                launch {
                    viewModel.listIsland("Bearer $token")
                }
                launch {
                    viewModel.stateIsland.observe(requireActivity()) { result ->
                        when (result) {
                            is Result.Loading -> {
                                binding.shimmerIsland.startShimmer()
                                binding.shimmerIsland.visibility = View.VISIBLE
                            }

                            is Result.Success -> {
                                binding.shimmerIsland.stopShimmer()
                                binding.shimmerIsland.visibility = View.GONE
                                result.data.let {
                                    islandAdapter.submitList(it)
                                }
                            }

                            is Result.Error -> {
                                binding.shimmerIsland.stopShimmer()
                                binding.shimmerIsland.visibility = View.GONE
                                Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.apply {
            islandAdapter = IslandAdapter(
                onItemClicked = {
                    viewModel.listIsland("Bearer $token")
                    islandAdapter.setSelectedItem(it)
                    islandAdapter.notifyDataSetChanged()
                },
                context = requireContext()
            )
            rvIsland.adapter = islandAdapter
            rvIsland.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

//            adapterPayslip = PayrollAdapter(
//                onItemClicked = {
//                    payslipId = it.id
//                    payslipName = it.name
//                    checkWriteStoragePermission()
//                }
//            )
//            recyclerView.adapter = adapterPayslip
//            recyclerView.layoutManager = LinearLayoutManager(this@PayrollListActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}