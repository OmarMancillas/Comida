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
        try{
            val cursor = db.rawQuery("SELECT * FROM Restaurantes",arrayOf<String>())
            with(cursor) {
                while (moveToNext()) {
                    Singleton.dataset.add(
                        Restaurantes(
                            cursor.getInt((getColumnIndex("id_restaurante"))),
                            cursor.getString(getColumnIndex("nombre_restaurante")),
                            cursor.getString(getColumnIndex("direccion")),
                            cursor.getString(getColumnIndex("telefono")),
                            cursor.getInt(getColumnIndex("tipo_comida"))
                        )
                    )
                }
            }
        }catch (ex : Exception){
        }
    }

    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }
}