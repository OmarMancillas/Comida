package com.example.comida

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_menu_principal.*

class MenuPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        Log.i("Sesion de usuario",intent.getStringExtra("USER_SESSION_ID"))
        btnCuenta.setOnClickListener(){
            val intent = Intent(applicationContext, CuentaActivity::class.java)
            intent.putExtra("USER_SESSION_ID", intent.getStringExtra("USER_SESSION_ID"))
            startActivity(intent)
        }
    }

    fun mostrarRestaurantesDisponibles(view : View){
        startActivity(Intent(this, RecyclerViewActivity::class.java))
    }
}
