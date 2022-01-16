package com.alexsp0.dailyapp.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alexsp0.dailyapp.presenters.DatePresenterImpl
import com.alexsp0.dailyapp.ui.mainViewPager.DateFragment
import com.alexsp0.dailyapp.ui.mainViewPager.TodayFragment

private const val TODAY_FRAGMENT = 0
private const val DATE_FRAGMENT = 1

class MainFragmentPagerAdapter(private val fragmentManager: FragmentManager, context : Context) :
    FragmentStatePagerAdapter(fragmentManager){

    private val fragments = arrayOf(TodayFragment(DatePresenterImpl()), DateFragment(DatePresenterImpl()))
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments[TODAY_FRAGMENT] as Fragment
            1 -> fragments[DATE_FRAGMENT] as Fragment
            else -> fragments[TODAY_FRAGMENT] as Fragment
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Today"
            1 -> "By date"
            else -> "Today"
        }
    }


}