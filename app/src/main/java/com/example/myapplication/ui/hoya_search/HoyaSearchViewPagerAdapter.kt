package com.example.myapplication.ui.hoya_search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HoyaSearchViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 8
    }

    override fun createFragment(position: Int): Fragment {
        return if (position < 8) {
            HoyaSearchPagerFragment.newInstance(position+1)
        } else {
            throw IllegalArgumentException("Invalid position $position")
        }
    }
}