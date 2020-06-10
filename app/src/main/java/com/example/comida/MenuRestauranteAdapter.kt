package com.example.comida

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_menu_restaurante.view.*

class MenuRestauranteAdapter(private val longItemClickListener: (Int) -> Unit) : RecyclerView.Adapter<MenuRestauranteAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvProducto = v.tvProducto
        val tvPrecio = v.tvPrecio
        val ivFoto = v.ivFoto
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): MenuRestauranteAdapter.ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_menu_restaurante, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount() = Singleton.datasetMenuRestaurante.size

    override fun onBindViewHolder(holder: MenuRestauranteAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,MenuRestauranteActivity::class.java)
            intent.putExtra("id_restaurante",Singleton.datasetMenuRestaurante.get(position).id_restaurante)
            intent.putExtra("id_producto",Singleton.datasetMenuRestaurante.get(position).id_producto)
            intent.putExtra("direccion",Singleton.datasetMenuRestaurante.get(position).descripcion_producto)
            intent.putExtra("telefono",Singleton.datasetMenuRestaurante.get(position).precio_producto)
//            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {
            longItemClickListener.invoke(position)
            true
        }

        holder.tvProducto.text = Singleton.datasetMenuRestaurante.get(position).descripcion_producto
        holder.tvPrecio.text = Singleton.datasetMenuRestaurante.get(position).precio_producto.toString()
    }
}