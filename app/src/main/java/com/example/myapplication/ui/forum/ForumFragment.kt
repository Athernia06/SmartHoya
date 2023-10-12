package com.example.myapplication.ui.forum

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.common.Result
import com.example.myapplication.data.remote.response.ForumResponse
import com.example.myapplication.databinding.FragmentForumBinding
import com.example.myapplication.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForumFragment : Fragment() {

    private var _binding: FragmentForumBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModels()
    private val viewModel: ForumViewModel by viewModels()
    private lateinit var adapter: ForumAdapter
    private var token: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentForumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    loginViewModel.getToken.collect {
                        token = it
                        viewModel.listForum("Bearer $token")
                    }
                }
            }
        }

        setupRecyclerView()
        observer()

    }

    private fun observer() {
        viewModel.stateForum.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { result ->
                when(result) {
                    is Result.Loading -> {
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressbar.visibility = View.GONE
                        handleForum(result.data)
                    }
                    is Result.Error -> {
                        binding.progressbar.visibility = View.GONE
                        Toast.makeText(requireContext(),"${result.message}" , Toast.LENGTH_SHORT).show()
                    }
                }
            }.launchIn(lifecycleScope)
    }

    private fun handleForum(data: List<ForumResponse>) {
        if (data.isNotEmpty()) {
            binding.rvForum.visibility = View.VISIBLE
            adapter.submitList(data)
        } else {
            binding.rvForum.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() {
        binding.apply {
            adapter = ForumAdapter()
            rvForum.adapter = adapter
            rvForum.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}