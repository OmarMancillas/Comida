package com.example.comida

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_restaurante_recycler.view.*

class RestauranteAdapter(private val longItemClickListener:(Int) -> Unit): RecyclerView.Adapter<RestauranteAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvNombre=v.tvNombre
        val tvDireccion=v.tvDireccion
        val ivLogo = v.ivLogo
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RestauranteAdapter.ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_restaurante_recycler, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount() = Singleton.dataset.size

    override fun onBindViewHolder(viewHolder: RestauranteAdapter.ViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener{
            val intent= Intent(viewHolder.itemView.context,MenuRestauranteActivity::class.java)
            intent.putExtra("id_restaurante",Singleton.dataset.get(position).id_restaurante)
            intent.putExtra("nombre_restaurante",Singleton.dataset.get(position).nombre_restaurante)
            intent.putExtra("direccion",Singleton.dataset.get(position).direccion)
            intent.putExtra("telefono",Singleton.dataset.get(position).telefono)
            intent.putExtra("tipo_comida",Singleton.dataset.get(position).tipo_comida)
            viewHolder.itemView.context.startActivity(intent)
        }

        viewHolder.itemView.setOnLongClickListener {
            longItemClickListener.invoke(position)
            true
        }

        viewHolder.tvNombre.text = Singleton.dataset.get(position).nombre_restaurante
        viewHolder.tvDireccion.text = Singleton.dataset.get(position).direccion
    }
}