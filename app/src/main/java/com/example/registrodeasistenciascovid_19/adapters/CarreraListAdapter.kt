package com.example.registrodeasistenciascovid_19.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.registrodeasistenciascovid_19.R
import com.example.registrodeasistenciascovid_19.entities.Carrera
import kotlinx.android.synthetic.main.list_item.view.*

class CarreraListAdapter: RecyclerView.Adapter<CarreraListAdapter.MyViewHolder>() {

    private var listaCarrera = emptyList<Carrera>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return  listaCarrera.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = listaCarrera[position]
        holder.itemView.elementoCbo.text = current.nombreCarrera
    }

    fun setData(carreras: List<Carrera>){
        this.listaCarrera = carreras
        notifyDataSetChanged()
    }
}