package com.example.comida

import android.app.Activity
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.etPassword
import java.io.IOException
import java.io.OutputStreamWriter

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun onRegistrar(view : View){
        if(etNombre.text.isEmpty() || etTelefono.text.isEmpty() || etEmail.text.isEmpty() || etPassword.text.isEmpty()){
            AlertDialog.Builder(this)
                .setTitle("Datos incompletos")
                .setMessage("Algunos datos no fueron capturados correctamente. " +
                        "Verifique que se hayan llenado todos los campos.")
                .setNeutralButton("OK", { dialogInterface: DialogInterface, i: Int -> })
                .show()
        }else{
            val dbHelper = BDUsuarios(applicationContext)
            val db = dbHelper?.writableDatabase

            val values = ContentValues().apply {
                Log.i("REGISTRAR", "Content values")
                put("usuario",etEmail.text.toString())
                put("nombre",etNombre.text.toString())
                put("password",etPassword.text.toString())
                put("telefono",etTelefono.text.toString())
            }
            val newRowId = db?.insert("Usuarios", null, values)

            try {
                val archivo = OutputStreamWriter(openFileOutput("datos.txt", Activity.MODE_PRIVATE))
                archivo.write(etNombre.text.toString()+"\n"+etTelefono.text.toString()+"\n"+etEmail.text.toString()+"\n"+etPassword.text.toString())
                archivo.flush()
                archivo.close()
            } catch (e: IOException) {
            }
            Toast.makeText(this, "Los datos fueron guardados",Toast.LENGTH_SHORT).show()
            view?. let {
                Log.i("REGISTRAR", "Usuario registrado")
                Snackbar.make(it, "¡Usuario registrado exitosamente!", Snackbar.LENGTH_LONG).show() }
            finish()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    fun onCancelar(view : View){
        finish()
        startActivity(Intent(this,MainActivity::class.java))
    }
}
