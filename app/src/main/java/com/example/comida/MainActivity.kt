package com.example.comida

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun cambiarARegistro(view : View){
        finish()
        startActivity(Intent(this,RegisterActivity::class.java))
    }

    fun onLoginCorrecto(view : View){
        val dbHelper = BDUsuarios(applicationContext)
        val db = dbHelper?.readableDatabase

//        val cursor = db.rawQuery("SELECT * FROM Usuarios ",null)
        val cursor = db.rawQuery("SELECT * FROM Usuarios WHERE usuario = ? AND password=?", arrayOf<String>("${etUsername.text.toString()}","${etLoginPassword.text.toString()}") )
        var usuario:String= etUsername.text.toString()
        var password:String=""
//        with(cursor) {
//            while (moveToNext()) {
//                usuario = cursor.getString(getColumnIndex("usuario"))
//                password = cursor.getString(getColumnIndex("password"))
//            }
//        }
        if(cursor.count>0){
            val intent = Intent(applicationContext, MenuPrincipalActivity::class.java)
            intent.putExtra("USER_SESSION_ID", usuario.toString())
            startActivity(intent)
        }else{
            Toast.makeText(this, "No hay registro con ese usuario/contraseña", Toast.LENGTH_LONG).show()
        }
    }
}
