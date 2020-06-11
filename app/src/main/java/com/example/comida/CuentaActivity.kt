package com.example.comida

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import kotlinx.android.synthetic.main.activity_cuenta.*
import java.io.*


class CuentaActivity : AppCompatActivity() {
    val PICK_IMAGE = 100;
    var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta)
        var usuario = intent.getStringExtra("USER_SESSION_ID")

        if (fileList().contains("datos-${usuario}.txt")) {
            try {
                val archivo = InputStreamReader(openFileInput("datos-${usuario}.txt"))
                val br = BufferedReader(archivo)
                var linea = br.readLine()
                val todo = StringBuilder()
                while (linea != null) {
                    todo.append(linea + "\n")
                    linea = br.readLine()
                }
                br.close()
                archivo.close()
                tvMostrarDatos.text = todo
            } catch (e: IOException) {
            }
        }

        btnCambiarFoto.setOnClickListener(
            View.OnClickListener {
                AbrirGaleria()
            }
        )

        btnGuardarCambios.setOnClickListener(View.OnClickListener {
            saveExternalFile()
        })
    }

    private fun saveExternalFile() {
        var usuario = intent.getStringExtra("USER_SESSION_ID")
        var photo: Bitmap = ivFoto.drawable.toBitmap()

        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED != state) {
            return
        }
        val file = File(getExternalFilesDir(null), "$usuario.png")
        if(file.exists()) file.delete()

        var outputStream: FileOutputStream? = null
        try {
            file.createNewFile()
            outputStream = FileOutputStream(file, true)
            photo.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun AbrirGaleria() {
        startActivityForResult(
            Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            ), PICK_IMAGE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data?.data
            ivFoto.setImageURI(imageUri)
        }
    }
}
