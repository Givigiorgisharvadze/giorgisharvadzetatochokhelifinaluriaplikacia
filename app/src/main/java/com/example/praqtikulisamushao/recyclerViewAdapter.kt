package com.example.praqtikulisamushao

import android.content.SharedPreferences
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class recyclerViewAdapter(val valStarWars : List<starWars>, val myfun : (starWars) -> Unit, var sharedPreferences: SharedPreferences ) : RecyclerView.Adapter<recyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        var varStarWars : starWars? = null
        init {
            itemView.setOnClickListener {

                myfun(varStarWars!!)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout2,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val valStarWars1 = valStarWars[position]
        holder.varStarWars = valStarWars1
        val text = holder.itemView.findViewById<TextView>(R.id.text)
        val image = holder.itemView.findViewById<ImageView>(R.id.image)
        val view = holder.itemView.findViewById<View>(R.id.view)
        Picasso.get().load(valStarWars1.characterPhoto).into(image)
        text.text=valStarWars1.characterName
        if(sharedPreferences.contains(valStarWars1.characterName)){
            view.setBackgroundColor(Color.GREEN)
        }


    }

    override fun getItemCount(): Int {
        return valStarWars.size
    }


}