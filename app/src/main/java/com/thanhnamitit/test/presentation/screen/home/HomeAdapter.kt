package com.thanhnamitit.test.presentation.screen.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thanhnamitit.test.presentation.screen.matches.MatchesFragment
import com.thanhnamitit.test.presentation.screen.teams.TeamsFragment

class HomeAdapter(fm: Fragment) : FragmentStateAdapter(fm) {

    private val fragmentBuilders = listOf<() -> Fragment>(
        { TeamsFragment() },
        { MatchesFragment() },
    )

    override fun getItemCount() = fragmentBuilders.size

    override fun createFragment(position: Int): Fragment {
        return fragmentBuilders[position]()
    }
}