package com.example.myapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.interfaces.ItemClickListener
import com.example.myapplication.R
import com.example.myapplication.modals.Space
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SpaceAdapter(
    val context: Context, val spaces:List<Space>,
    val listener: ItemClickListener
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
        private val title: TextView = itemView.findViewById(R.id.search_space_title)
        private val openHour:TextView = itemView.findViewById(R.id.spaceOpenHourView)
        private val price:TextView = itemView.findViewById(R.id.sDate)
        private val date:TextView = itemView.findViewById(R.id.search_space_address)
        private val skipButton:Button = itemView.findViewById(R.id.contact_button)
        private val declineButton:Button = itemView.findViewById(R.id.declineButton)
        private val acceptButton:Button = itemView.findViewById(R.id.request_button)
        private val acceptLayout = itemView.findViewById<View>(R.id.successLayout)
        private val declineLayout= itemView.findViewById<View>(R.id.declineLayout)

        fun bindMessage(context: Context,space:Space){
//            val resourceId = context.resources.getIdentifier(space.image?.thumb,"drawable",context.packageName)
//            spacePic.setImageResource(R.drawable.ic_launcher_background)
                Log.d("adapter",space.image.toString())
                val imageUrl = "https://pixabay.com/illustrations/head-the-dummy-avatar-man-tie-659652/"
                Picasso.with(context).load(imageUrl)
                    .placeholder(R.drawable.ph).into(spacePic,imageCallback)

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
    val imageCallback:Callback = object :Callback{
        override fun onSuccess() {
            Log.d("image","success")

        }

        override fun onError() {
            Log.d("image","success")
        }

    }
}
