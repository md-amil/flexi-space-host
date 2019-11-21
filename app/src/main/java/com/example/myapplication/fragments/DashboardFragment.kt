package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.adapter.TabAdapter
import com.google.android.material.tabs.TabLayout

class DashboardFragment : Fragment() {
    private lateinit var viewPager:ViewPager
    private lateinit var tabs:TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_dashboard, container, false)
        viewPager = view.findViewById(R.id.view_pager)
        tabs = view.findViewById(R.id.tabs_main)

        val fragmentAdapter = TabAdapter(childFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)
        return view
    }
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException("$context must implement OnFragmentInteractionListener")
//        }
//    }
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }

//    interface OnFragmentInteractionListener {
//        fun onFragmentInteraction(uri: Uri)
//    }

}
