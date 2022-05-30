package com.example.registrodeasistenciascovid_19.ClasesExternas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.registrodeasistenciascovid_19.R
import com.example.registrodeasistenciascovid_19.entities.Clases
import com.example.registrodeasistenciascovid_19.ui.listaClases.listaClasesFragment

class ClaseAdapter(private val context: listaClasesFragment, private val listener: IClasesRVAdapter):
    RecyclerView.Adapter<ClaseAdapter.ClaseViewHolder>() {

    private val clasesInternas = ArrayList<Clases>()

    inner class ClaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtMateriaEle: TextView = itemView.findViewById(R.id.txtMateriaEle)
        val txtFechaEle: TextView = itemView.findViewById(R.id.txtFechaEle)
        val estado: ImageView = itemView.findViewById(R.id.stateDot)

        //Botones
        val delete: ImageView = itemView.findViewById(R.id.btnDelete)
        val sync: ImageView = itemView.findViewById(R.id.btnSync)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClaseViewHolder {
        val viewHolder = ClaseViewHolder(LayoutInflater.from(context.context).inflate(R.layout.recycler_item, parent, false))
        viewHolder.delete.setOnClickListener{
            listener.onItemClicked(clasesInternas[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return clasesInternas.size
    }

    override fun onBindViewHolder(holder: ClaseViewHolder, position: Int) {
        val current = clasesInternas[position]
        holder.txtMateriaEle.text = current.Cod_Clase //Se tiene que establecer la clase y no el codigo
        holder.txtFechaEle.text = current.inicio
    }

    fun updateList(nuevaLista: List<Clases>){
        clasesInternas.clear()
        clasesInternas.addAll(nuevaLista)
        notifyDataSetChanged()
    }

}

interface IClasesRVAdapter {
    fun onItemClicked(clase: Clases)
}