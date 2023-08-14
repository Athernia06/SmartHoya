package com.example.myapplication.ui.hoya_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.core.data.model.TanamanModel
import com.example.myapplication.databinding.FragmentHoyaSearchPagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoyaSearchPagerFragment : Fragment() {
    private var _binding: FragmentHoyaSearchPagerBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(positionId: Int) = HoyaSearchPagerFragment()
    }

    private val dummyTanamanList = listOf(
        TanamanModel(
            id = "1",
            nama = "Hoya Kerrii",
            gambar = "https://picsum.photos/200",
        ),
        TanamanModel(
            id = "2",
            nama = "Hoya Carnosa",
            gambar = "https://picsum.photos/201",
        ),
        TanamanModel(
            id = "3",
            nama = "Hoya Kerrii",
            gambar = "https://picsum.photos/202",
        ),
        TanamanModel(
            id = "4",
            nama = "Hoya Carnosa",
            gambar = "https://picsum.photos/203",
        ),
        TanamanModel(
            id = "5",
            nama = "Hoya Kerrii",
            gambar = "https://picsum.photos/204",
        ),
        TanamanModel(
            id = "6",
            nama = "Hoya Carnosa",
            gambar = "https://picsum.photos/205",
        ),
        TanamanModel(
            id = "7",
            nama = "Hoya Kerrii",
            gambar = "https://picsum.photos/206",
        ),
    )

    private val adapter = HoyaSearchRecyclerAdapter(dummyTanamanList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoyaSearchPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        with(binding) {
            rvTanaman.adapter = adapter
            rvTanaman.layoutManager = GridLayoutManager(context, 2)
        }
    }
}