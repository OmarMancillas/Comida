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
import com.example.comida.RoomDatabase.AppDatabase
import com.example.comida.entities.Usuarios
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

    fun onRegistrar(view: View) {
        if (etNombre.text.isEmpty() || etTelefono.text.isEmpty() || etEmail.text.isEmpty() || etPassword.text.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle("Datos incompletos")
                .setMessage(
                    "Algunos datos no fueron capturados correctamente. " +
                            "Verifique que se hayan llenado todos los campos."
                )
                .setNeutralButton("OK", { dialogInterface: DialogInterface, i: Int -> })
                .show()
        } else {
            var usuarioExistente: Boolean = false
            var cant: Int = 0
            //Valida
            if (etEmail.text.trim().isNullOrEmpty()) {
                Toast.makeText(this, "Ingrese Correo", Toast.LENGTH_SHORT).show()
                return
            }
            try {
                usuarioExistente = AppDatabase.get(application).getUsuariosDAO()
                    .findByUsuario(etEmail.text.trim().toString())
                    .usuario.isNotEmpty()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (usuarioExistente) {
                Toast.makeText(
                    this,
                    "Ya existe usuario llamado: ${etEmail.text.trim().toString()}",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            var usuarioNuevo = Usuarios()
            usuarioNuevo.usuario = etEmail.text.trim().toString()
            usuarioNuevo.password = etPassword.text.toString()
            usuarioNuevo.nombre = etNombre.text.trim().toString()
            usuarioNuevo.telefono = etTelefono.text.trim().toString()

            val hilo = object :Thread(){
                override fun run() {
                    try {
                        AppDatabase.get(application).getUsuariosDAO().insert(usuarioNuevo)
                        AppDatabase.get(application).getUsuariosDAO().getAll().forEach {
                            Log.d("agregado", "Usuario : ${it.usuario}")
                            Log.d("agregado", "Password : ${it.password}")
                            Log.d("agregado", "Nombre: ${it.nombre}")
                            Log.d("agregado", "Telefono : ${it.telefono}")
                        }
                    } catch (ex: java.lang.Exception) {
                        Log.d("agregado", ex.message)
                    }
                }
            }
            hilo.start()
            hilo.join()


            var info =
                usuarioNuevo.usuario + "\n" + usuarioNuevo.password + "\n" + usuarioNuevo.nombre +
                        "\n" + usuarioNuevo.telefono

            try {
                val archivo = OutputStreamWriter(
                    openFileOutput(
                        "datos-${usuarioNuevo.usuario}.txt",
                        Activity.MODE_PRIVATE
                    )
                )
                archivo.write(info)
                archivo.flush()
                archivo.close()
            } catch (e: IOException) {
            }
            Toast.makeText(this, "Los datos fueron guardados", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(Intent(this, MainActivity::class.java))
            view?.let {
                Snackbar.make(it, "¡Usuario registrado exitosamente!", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun onCancelar(view: View) {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
}
