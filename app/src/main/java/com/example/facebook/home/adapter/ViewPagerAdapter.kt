package com.example.facebook.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.facebook.home.HomeMainFragment


class ViewPagerAdapter(parentFragment: Fragment) :
    FragmentStateAdapter(parentFragment) {

    override fun getItemCount() = 7

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeMainFragment()
            1 -> HomeMainFragment()
            2 -> HomeMainFragment()
            3 -> HomeMainFragment()
            4 -> HomeMainFragment()
            5 -> HomeMainFragment()
            else -> HomeMainFragment()

        }
    }

}