package com.example.comida

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.comida.RoomDatabase.AppDatabase
import com.example.comida.entities.Usuarios
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
        var correcto:Boolean = false
        var existe:Boolean = false
        var usuarioLogin:Usuarios? = Usuarios()
        if (etUsername!!.text.trim().toString().isNullOrEmpty()){
            Toast.makeText(applicationContext,"Ingrese Correo", Toast.LENGTH_SHORT).show()
            return
        }
        if(etLoginPassword!!.text.trim().toString().isNullOrEmpty()){
            Toast.makeText(applicationContext,"Ingresar Contraseña", Toast.LENGTH_SHORT).show()
            return
        }
        var username : String   = etUsername!!.text.trim().toString()
        var password : String = etLoginPassword!!.text.trim().toString()
        Log.i("agregado","${username}")
        val HiloLogin = object: Thread(){
            override fun run(){
                try {
                    usuarioLogin = AppDatabase.get(application).getUsuariosDAO().findByUsuario(username)
                }catch(e: Exception){
                    e.printStackTrace()
                }
            }
        }
        HiloLogin.start()
        HiloLogin.join()
        if( usuarioLogin == null){
            Toast.makeText(applicationContext,"Este correo no se ha registrado", Toast.LENGTH_SHORT).show()
            return
        }
        if(usuarioLogin!!.password != password)
        {
            Toast.makeText(applicationContext,"Contraseña Incorrecta!", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(applicationContext,"Login Correcto!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        intent.putExtra("USER_SESSION_ID", usuarioLogin!!.usuario)
        startActivity(intent)
    }
}
