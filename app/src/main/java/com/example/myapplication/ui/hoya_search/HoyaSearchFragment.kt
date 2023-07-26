package com.example.myapplication.ui.hoya_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentHoyaSearchBinding
import com.google.android.material.tabs.TabLayoutMediator

class HoyaSearchFragment : Fragment() {
    private var _binding: FragmentHoyaSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HoyaSearchViewPagerAdapter
    private val tabNameList = listOf(
        "Sumatera",
        "Kalimantan",
        "Sulawesi",
        "Papua",
        "Jawa",
        "Bali",
        "NTB",
        "NTT",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HoyaSearchViewPagerAdapter(childFragmentManager, lifecycle)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoyaSearchBinding.inflate(inflater, container, false)
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
            viewPager.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabNameList[position]
            }.attach()
        }
    }

}