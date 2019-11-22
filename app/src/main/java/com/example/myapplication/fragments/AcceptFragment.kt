package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.interfaces.ItemClickListener

import com.example.myapplication.R
import com.example.myapplication.adapter.SpaceAdapter
import com.example.myapplication.modals.Spaces
import com.example.myapplication.utilities.App
import com.example.myapplication.utilities.request.Request
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_accept.*

class AcceptFragment : Fragment(){
    lateinit var spaceAdapter: SpaceAdapter
    lateinit var spaces:Spaces

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_accept, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Request(requireContext()).get("/spaces").header("authorization", App.loginPrefs.authToken.toString()).then {
            spaces = Gson().fromJson(it,Spaces::class.java)
            spaceAdapter = SpaceAdapter(requireContext(),spaces.data,listener)
            recyclerView1.adapter = spaceAdapter
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView1.layoutManager = layoutManager
        }.catch {

        }
    }
private val listener = object: ItemClickListener {
    override fun skipButtonClicked(id: Int, view: View) {
        Toast.makeText(requireContext(),"sending skip request",Toast.LENGTH_SHORT).show()
    }

    override fun declineButtonClicked(id: Int, view: View) {
        Toast.makeText(requireContext(),"sending decline request",Toast.LENGTH_SHORT).show()
    }

    override fun acceptButtonClicked(id: Int, view: View) {
        Toast.makeText(requireContext(),"sending accept request",Toast.LENGTH_SHORT).show()
    }

}
}
