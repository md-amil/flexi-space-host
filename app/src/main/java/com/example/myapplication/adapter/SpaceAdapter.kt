package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ItemClickListener
import com.example.myapplication.R
import com.example.myapplication.modals.Space


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SpaceAdapter(
    val context: Context, val spaces:List<Space>,
    val listener:ItemClickListener
): RecyclerView.Adapter<SpaceAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_space,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return spaces.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMessage(context,spaces[position])
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val spacePic: ImageView = itemView.findViewById(R.id.spaceMedia)
        private val title: TextView = itemView.findViewById(R.id.spaceTitle)
        private val openHour:TextView = itemView.findViewById(R.id.spaceOpenHourView)
        private val price:TextView = itemView.findViewById(R.id.sDate)
        private val date:TextView = itemView.findViewById(R.id.spacedateView)
        private val skipButton:Button = itemView.findViewById(R.id.skipButton)
        private val declineButton:Button = itemView.findViewById(R.id.declineButton)
        private val acceptButton:Button = itemView.findViewById(R.id.acceptButton)
        private val acceptLayout = itemView.findViewById<View>(R.id.successLayout)
        private val declineLayout= itemView.findViewById<View>(R.id.declineLayout)

        fun bindMessage(context: Context,space:Space){
//            val resourceId = context.resources.getIdentifier(space.image?.thumb,"drawable",context.packageName)
            spacePic.setImageResource(R.drawable.ic_launcher_background)
            title.text = space.name
            openHour.text = "12:8"
            price.text  = "c 10 credit"
            date.text = "12-5"
            skipButton.setOnClickListener {
                listener.skipButtonClicked(space.id,it)
            }
            acceptButton.setOnClickListener {
                acceptLayout.visibility = View.VISIBLE
                listener.acceptButtonClicked(space.id,it)
            }
            declineButton.setOnClickListener {
                declineLayout.visibility = View.VISIBLE
                listener.declineButtonClicked(space.id,it)
            }
        }


    }
}
