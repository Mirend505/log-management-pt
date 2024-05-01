package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class AdapterPersonnelle(var dataliste:ArrayList<Classe_personelle>, val itemClickListener:View.OnClickListener) : RecyclerView.Adapter<AdapterPersonnelle.ViewHolder>()

//,var itemListener: View.OnClickListener

{
    //recuperation de tout les donner
    class ViewHolder(itemVew:View):RecyclerView.ViewHolder(itemVew) {

        var codePersonnelle = itemVew.findViewById<TextView>(R.id.code)
        var nomPersonnelle = itemVew.findViewById<TextView>(R.id.nomp)
        var satutPersonnelle = itemVew.findViewById<TextView>(R.id.statut)
        var imageDelete = itemVew.findViewById<ImageView>(R.id.imagedelete)
        var imageEdit = itemVew.findViewById<ImageView>(R.id.imageedit)
        var cardview = itemVew.findViewById<CardView>(R.id.cardviewPerso)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var itemView = inflater.inflate(R.layout.layout_liste_personnelle,parent,false)
        return ViewHolder(itemView)
    }



    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curentItem = dataliste[position]
         holder.cardview.setOnClickListener(itemClickListener)
        holder.codePersonnelle.text = curentItem.codeP
        holder.nomPersonnelle.text = curentItem.nomP
        holder.satutPersonnelle.text = curentItem.status
        holder.imageDelete.setImageResource(R.drawable.baseline_delete_forever_24)
        holder.cardview.tag = position
        holder.imageEdit.setImageResource(R.drawable.baseline_edit_24)

        if(position % 2 ==0)
        {
            holder.cardview.setBackgroundColor(R.color.lightGray)
        }
//        holder.cardView.tag= position
//        holder.cardView.setOnClickListener(itemListener)

    }
    override fun getItemCount(): Int {

        return dataliste.size
    }
}