package com.example.comida

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuRestauranteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_restaurante)

        val nombre=intent.extras?.getString("nombre","NA")
        val telefono=intent.extras?.getString("telefono","NA")
        val direccion=intent.extras?.getString("direccion","NA")
        val logo=intent.extras?.getString("direccion","NA")
        Toast.makeText(this,"Mostrandose menu de: ${nombre}", Toast.LENGTH_LONG).show()
    }


}
