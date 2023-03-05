package com.coderipper.hsma.usecases.account.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsAdapter(private val fragments: List<Fragment>, hostFragment: Fragment) : FragmentStateAdapter(hostFragment) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        return fragments[position]
    }
}