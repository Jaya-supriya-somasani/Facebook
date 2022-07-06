package com.example.facebook.home

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.facebook.R
import com.example.facebook.databinding.FragmentHomeBinding
import com.example.facebook.home.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        binding.viewPager.adapter =
            ViewPagerAdapter(this)
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_baseline_home_24)
                1 -> tab.setIcon(R.drawable.ic_people)
                2 -> tab.setIcon(R.drawable.ic_videos)
                3 -> tab.setIcon(R.drawable.ic_store)
                4 ->
                    tab.setIcon(R.drawable.ic_notifications)
                5 ->
                    tab.setIcon(R.drawable.ic_menu)

            }
        }.attach()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.toolbar.inflateMenu(R.menu.facebook_main_menu)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.facebook_main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search ->
                // search
                return false
            R.id.messenger ->
                // for messenger application
                return true
            else -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}