package com.example.comida

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comida.RoomDatabase.AppDatabase
import com.example.comida.entities.Restaurantes
import com.example.comida.entities.Usuarios
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.Exception

class RecyclerViewActivity : AppCompatActivity() {

    val onLongItemClickListener: (Int) -> Unit = { position->
        var telefono = "${Singleton.dataset.get(position).telefono}"
        Toast.makeText(this, "Telefono: ${telefono}", Toast.LENGTH_LONG).show()
        startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telefono, null)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        LoadData()

        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=RestauranteAdapter(onLongItemClickListener)
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun LoadData(){
        Singleton.dataset.clear()

        val dbHelper = BDUsuarios(applicationContext)
        val db = dbHelper?.readableDatabase
        Log.d("Prueba","Entra al loadData")
        try{
            val cursor = db.rawQuery("SELECT * FROM Restaurantes",arrayOf<String>())
            Log.d("Prueba",cursor.count.toString())
            with(cursor) {
                while (moveToNext()) {
                    Log.d("Prueba","Entra al cursor")
                    Singleton.dataset.add(
                        Restaurantes(
                            cursor.getInt((getColumnIndex("id_restaurante"))),
                            cursor.getString(getColumnIndex("nombre_restaurante")),
                            cursor.getString(getColumnIndex("direccion")),
                            cursor.getString(getColumnIndex("telefono")),
                            cursor.getInt(getColumnIndex("tipo_comida"))
                        )
                    )
//                descripcion_producto = cursor.getString(getColumnIndex("descripcion_producto"))
//                listItems = listItems + descripcion_producto + "\n"
//                Log.i("Prueba",descripcion_producto)
                }
            }
        }catch (ex : Exception){
            Log.d("Prueba",ex.message)
        }
    }
//        Toast.makeText(this,"Usuario Registrado", Toast.LENGTH_SHORT).show()
//        for(i in 1..6){
//            var tipoComida = rand(1,6)
//            var restauranteNuevo = Restaurantes()
//            restauranteNuevo.id_restaurante = i
//            restauranteNuevo.nombre_restaurante = "Restaurante ${i}"
//            restauranteNuevo.direccion = "Direccion ${i}"
//            if(i<10)
//                restauranteNuevo.telefono = "867727090${i}"
//            else
//                restauranteNuevo.telefono = "86772709${i}"
//            restauranteNuevo.tipo_comida = tipoComida
//
//            val hilo = object :Thread(){
//                override fun run() {
//                    try {
//                        AppDatabase.get(application).getRestaurantesDAO().insert(restauranteNuevo)
//                    } catch (ex: java.lang.Exception) {
//                    }
//                }
//            }
//            hilo.start()
//            hilo.join()
//        }
//
//        val hilo2 = object :Thread(){
//            override fun run() {
//                try {
//                    AppDatabase.get(application).getRestaurantesDAO().getAll().forEach {
//                        Singleton.dataset.add(
//                Restaurantes(
//                    it.id_restaurante,
//                    it.nombre_restaurante,
//                    it.direccion,
//                    it.telefono,
//                    it.tipo_comida
//                )
//                        )
//                    }
//                } catch (ex: java.lang.Exception) {
//                }
//            }
//        }
//        hilo2.start()
//        hilo2.join()
//    }

    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }
}