package com.example.comida

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.footer_menu_restaurante.view.*
import kotlinx.android.synthetic.main.layout_menu_restaurante.view.*


class MenuRestauranteAdapter(private val longItemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<MenuRestauranteAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvProducto = v.tvProducto
        val tvPrecio = v.tvPrecio
        val btnOrdenar = v.btnOrdenar
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): MenuRestauranteAdapter.ViewHolder {
        val itemView: View
        if (viewType == R.layout.layout_menu_restaurante) {
            itemView = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.layout_menu_restaurante, viewGroup, false);
        } else {
            itemView = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.footer_menu_restaurante, viewGroup, false);
        }
        return ViewHolder(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position === Singleton.datasetMenuRestaurante.size) R.layout.footer_menu_restaurante else R.layout.layout_menu_restaurante
    }

    override fun getItemCount() = Singleton.datasetMenuRestaurante.size + 1

    override fun onBindViewHolder(holder: MenuRestauranteAdapter.ViewHolder, position: Int) {
        if (holder.itemViewType == R.layout.layout_menu_restaurante) {
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, MenuRestauranteActivity::class.java)
                intent.putExtra(
                    "id_restaurante",
                    Singleton.datasetMenuRestaurante.get(position).id_restaurante
                )
                intent.putExtra(
                    "id_producto",
                    Singleton.datasetMenuRestaurante.get(position).id_producto
                )
                intent.putExtra(
                    "direccion",
                    Singleton.datasetMenuRestaurante.get(position).descripcion_producto
                )
                intent.putExtra(
                    "telefono",
                    Singleton.datasetMenuRestaurante.get(position).precio_producto
                )
            }

            holder.itemView.setOnLongClickListener {
                longItemClickListener.invoke(position)
                true
            }

            holder.tvProducto.text =
                Singleton.datasetMenuRestaurante.get(position).descripcion_producto
            holder.tvPrecio.text =
                Singleton.datasetMenuRestaurante.get(position).precio_producto.toString()
        }

        if (position === Singleton.datasetMenuRestaurante.size) {
            holder.btnOrdenar.setOnClickListener(View.OnClickListener {
                AlertDialog.Builder(it.context)
                    .setTitle("")
                    .setMessage(
                        "Confirmar orden?"
                    )
                    .setPositiveButton("Si", { dialogInterface: DialogInterface, i: Int -> })
                    .setNegativeButton("No", { dialogInterface: DialogInterface, i: Int -> })
                    .show()
            })
        }
    }
}