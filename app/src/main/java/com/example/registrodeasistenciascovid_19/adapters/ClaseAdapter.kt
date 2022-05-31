package com.example.registrodeasistenciascovid_19.ClasesExternas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.registrodeasistenciascovid_19.R
import com.example.registrodeasistenciascovid_19.entities.Clases

class ClaseAdapter(private val onClick: (Clases) -> Unit):
    androidx.recyclerview.widget.ListAdapter<Clases, ClaseAdapter.ClaseViewHolder>(ClasesDiffCallBack){

    class ClaseViewHolder(itemView: View, val onClick: (Clases) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val txtMateriaEle: TextView = itemView.findViewById(R.id.txtMateriaEle)
        val txtFechaEle: TextView = itemView.findViewById(R.id.txtFechaEle)
        val estado: ImageView = itemView.findViewById(R.id.stateDot)
        private var current: Clases? = null

        init {
            itemView.setOnClickListener{
                current?.let{
                    onClick(it)
                }
            }
        }

        fun delete(clase: Clases){
            current = clase
            txtFechaEle.text = clase.inicio
        }

        //Botones

        val delete: ImageView = itemView.findViewById(R.id.btnDelete)
        val sync: ImageView = itemView.findViewById(R.id.btnSync)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClaseViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ClaseViewHolder(viewHolder, onClick)
    }

    override fun onBindViewHolder(holder: ClaseViewHolder, position: Int) {
        val current = getItem(position)
        holder.txtMateriaEle.text = current.Cod_Clase //Se tiene que establecer la clase y no el codigo
        holder.txtFechaEle.text = current.inicio

        holder.delete.setOnClickListener {

        }

    }
    fun updateList(list: List<Clases>){
        this.submitList(list as MutableList<Clases>)
        notifyDataSetChanged()
    }
}

object ClasesDiffCallBack: DiffUtil.ItemCallback<Clases>(){
    override fun areItemsTheSame(oldItem: Clases, newItem: Clases): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Clases, newItem: Clases): Boolean {
        return oldItem.Cod_Clase == newItem.Cod_Clase
    }

}