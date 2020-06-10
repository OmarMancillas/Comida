package com.example.comida

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comida.RoomDatabase.AppDatabase
import com.example.comida.entities.MenuRestaurante
import com.example.comida.entities.Restaurantes
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu_restaurante.*

class MenuRestauranteActivity : AppCompatActivity() {

    val onLongItemClickListener: (Int) -> Unit = { position->
        var descripcion = "${Singleton.datasetMenuRestaurante.get(position).descripcion_producto}"
        Toast.makeText(this, "Producto: ${descripcion}", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_restaurante)

        Log.d("Prueba","Llega al create")
        loadData()

        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=MenuRestauranteAdapter(onLongItemClickListener)
    }

    private fun loadData(){
        Singleton.datasetMenuRestaurante.clear()
        val id_restaurante =intent.getIntExtra("id_restaurante",0)
        Log.d("Prueba","${id_restaurante}")

        val dbHelper = BDUsuarios(applicationContext)
        val db = dbHelper?.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM Comida_restaurante WHERE id_restaurante = ?",arrayOf<String>("${id_restaurante}"))
        with(cursor) {
            while (moveToNext()) {
                Log.d("Prueba","${cursor.getInt(getColumnIndex("id_restaurante"))}")
                Singleton.datasetMenuRestaurante.add(
                    MenuRestaurante(
                        cursor.getInt((getColumnIndex("id_restaurante"))),
                        cursor.getInt(getColumnIndex("id_producto")),
                        cursor.getString(getColumnIndex("descripcion_producto")),
                        cursor.getDouble(getColumnIndex("precio_producto"))
                    )
                )
//                descripcion_producto = cursor.getString(getColumnIndex("descripcion_producto"))
//                listItems = listItems + descripcion_producto + "\n"
//                Log.i("Prueba",descripcion_producto)
            }
        }
        var restaurante : String=""
        val cursor2 = db.rawQuery("SELECT * FROM Restaurantes WHERE id_restaurante = ?",arrayOf<String>("${id_restaurante}"))
        with(cursor2){
            while (moveToNext()){
                restaurante = cursor2.getString(getColumnIndex("nombre_restaurante"))
            }
        }

        Snackbar.make(findViewById(R.id.layout_menu_restaurante), restaurante, Snackbar.LENGTH_LONG).show()
    }

//    private fun LoadData(){
//        for(i in 0..20){
//            var tipoComida = rand(1,6)
//            Log.i("tipoComida","${tipoComida}")
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
//                        Log.d("agregado", ex.message)
//                    }
//                }
//            }
//            hilo.start()
//            hilo.join()
//        }

//        val hilo2 = object :Thread(){
//            override fun run() {
//                try {
//                    AppDatabase.get(application).getRestaurantesDAO().getAll().forEach {
//                        Singleton.datasetMenuRestaurante.add(
//                            MenuRestaurante(
//                                it.id_restaurante,
//                                it.
//                            )
//                        )
//                    }
//                } catch (ex: java.lang.Exception) {
//                    Log.d("agregado", ex.message)
//                }
//            }
//        }
//        hilo2.start()
//        hilo2.join()
//    }
}
