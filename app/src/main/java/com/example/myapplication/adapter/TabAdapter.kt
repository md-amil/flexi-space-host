package com.example.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.fragments.AcceptFragment
import com.example.myapplication.fragments.DeclineFragment
import java.nio.file.Files.size



class TabAdapter(fm:FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if(position == 0) {
            AcceptFragment()
        }else{
            DeclineFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) {
            "accept"
        } else {
            "decline"
        }
    }
    override fun getCount(): Int {
        return 2
    }

}