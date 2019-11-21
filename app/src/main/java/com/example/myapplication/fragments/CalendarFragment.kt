package com.example.myapplication.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myapplication.R


class CalendarFragment:Fragment() {
//    private var param1: String? = null
//    private var param2: String? = null
////    private var listener: OnFragmentInteractionListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clender, container, false)
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
