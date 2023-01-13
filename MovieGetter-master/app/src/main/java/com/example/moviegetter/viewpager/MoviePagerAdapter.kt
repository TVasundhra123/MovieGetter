package com.example.moviegetter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val TAB_COUNT = 3
private const val TAG = "TAG"
class MoviePagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return TAB_COUNT
    }

    override fun createFragment(position: Int): Fragment {

      val tag =  when (position) {
            0 -> "Avengers"
           // 1 -> return TransformerFragment()
           // 2 -> return BarbieFragment()
            1 -> "Sample"
            else -> "Luck"
        }
        return AvengersFragment().apply {
            arguments = Bundle().apply {
                putString(TAG, tag)
            }
        }
    }
}