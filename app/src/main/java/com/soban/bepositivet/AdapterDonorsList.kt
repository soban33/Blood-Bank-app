package com.soban.bepositivet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterDonorsList(var donorlist: ArrayList<ModelDonorsList>, val clickListener: (ModelDonorsList) -> Unit): RecyclerView.Adapter<AdapterDonorsList.DonorListViewHolder>() {
    class DonorListViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        fun bind(modelDonorsList: ModelDonorsList,clickListener: (ModelDonorsList) -> Unit){
            val txt_donorname = itemView.findViewById<TextView>(R.id.txt_donorname)
            val txt_mobilenum = itemView.findViewById<TextView>(R.id.txt_mobilenum)
            //val txt_bloodGroup = itemView.findViewById<TextView>(R.id.txt_bloodGroup)
            val txt_city = itemView.findViewById<TextView>(R.id.txt_city)

            //now binding
            txt_donorname.text = modelDonorsList.name
            //txt_bloodGroup.text = modelDonorsList.group
            txt_mobilenum.text = modelDonorsList.mobilenumber
            txt_city.text = modelDonorsList.city
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonorListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.singleitem_donorslist,parent,false)
        return DonorListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonorListViewHolder, position: Int) {
        holder.bind(donorlist[position],clickListener)
    }

    override fun getItemCount(): Int {
        return donorlist.size
    }
}