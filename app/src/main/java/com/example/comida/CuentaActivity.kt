package com.example.comida

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cuenta.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class CuentaActivity : AppCompatActivity() {
    val PICK_IMAGE = 100;
    var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta)
        var usuario = intent.getStringExtra("USER_SESSION_ID")

        if(fileList().contains("datos.txt")) {
            try {
                val archivo = InputStreamReader(openFileInput("datos.txt"))
                val br = BufferedReader(archivo)
                var linea = br.readLine()
                val todo = StringBuilder()
                while (linea != null) {
                    todo.append(linea + "\n")
                    linea = br.readLine()
                }
                br.close()
                archivo.close()

            } catch (e: IOException) {
            }
        }

        btnCambiarFoto.setOnClickListener(
            View.OnClickListener {
                AbrirGaleria()
            }
        )

//        btnGuardarCambios.setOnClickListener(View.OnClickListener {
//            if(fileList().contains("datos.txt")){
//                try {
//                    val archivo = InputStreamReader(openFileInput("datos.txt"))
//                    val br = BufferedReader(archivo)
//                    var linea = br.readLine()
//                    val to do = StringBuilder()
//                    while (linea != null) {
//                        tod o.append(linea + "\n")
//                        linea = br.readLine()
//                    }
//                    br.close()
//                    archivo.close()
//                    AlertDialog.Builder(this)
//                        .setTitle("Cuenta.")
//                        .setMessage(to do)
//                        .setNeutralButton("OK", { dialogInterface: DialogInterface, i: Int -> })
//                        .show();
//                } catch (e: IOException) {
//                }
//            }else{
//                AlertDialog.Builder(this)
//                    .setTitle("Cuenta.")
//                    .setMessage("No se encontro el archivo")
//                    .setNeutralButton("OK", { dialogInterface: DialogInterface, i: Int -> })
//                    .show();
//            }
//        })

//        val dbHelper = BDUsuarios(applicationContext)
//        val db = dbHelper?.readableDatabase
//        val cursor = db.rawQuery("SELECT * FROM Usuarios WHERE usuario = ?", arrayOf<String>(usuario))
//        var nombre:String=""
//        var telefono:String=""
//        with(cursor) {
//            while (moveToNext()) {
//                nombre = cursor.getString(getColumnIndex("nombre"))
//                telefono = cursor.getString(getColumnIndex("telefono"))
//            }
//        }

//        var user = db.usuariosDao().findByUsuario(usuario)
//        Log.i("Usuario: ",user.nombre)
//        tvNombre.text = user.nombre
//        tvTelefono.text = user.telefono
    }

    private fun AbrirGaleria(){
        startActivityForResult(Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI),PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data?.data
            ivFoto.setImageURI(imageUri)
        }
    }
}
