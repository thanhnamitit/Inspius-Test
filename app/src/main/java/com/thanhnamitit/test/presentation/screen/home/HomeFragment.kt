package com.thanhnamitit.test.presentation.screen.home

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.thanhnamitit.test.R
import com.thanhnamitit.test.core.base.BaseFragment
import com.thanhnamitit.test.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val onPageChangeCallBack = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            fun checkId(id: Int) {
                binding.navigation.menu.findItem(id).isChecked = true
            }
            when (position) {
                0 -> checkId(R.id.menu_team)
                1 -> checkId(R.id.menu_match)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = HomeAdapter(this@HomeFragment)
        binding.viewPager.registerOnPageChangeCallback(onPageChangeCallBack)
        binding.navigation.setOnItemSelectedListener {
            val position = when (it.itemId) {
                R.id.menu_team -> 0
                else -> 1
            }
            binding.viewPager.currentItem = position
            true
        }
    }

    override fun onDestroyView() {
        binding.viewPager.unregisterOnPageChangeCallback(onPageChangeCallBack)
        super.onDestroyView()
    }
}