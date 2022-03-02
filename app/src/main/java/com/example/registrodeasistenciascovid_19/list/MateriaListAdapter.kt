package com.example.registrodeasistenciascovid_19.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.registrodeasistenciascovid_19.R
import com.example.registrodeasistenciascovid_19.entities.Carrera
import com.example.registrodeasistenciascovid_19.entities.Materia
import kotlinx.android.synthetic.main.list_item.view.*

class MateriaListAdapter: RecyclerView.Adapter<MateriaListAdapter.MyViewHolder>() {

    private var listaMaterias = emptyList<Materia>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listaMaterias.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = listaMaterias[position]
        holder.itemView.elementoCbo.text = current.nombreMateria
    }

    fun setData(materias: List<Materia>){
        this.listaMaterias = materias
        notifyDataSetChanged()
    }
}