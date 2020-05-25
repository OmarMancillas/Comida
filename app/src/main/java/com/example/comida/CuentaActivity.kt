package com.example.comida

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cuenta.*

class CuentaActivity : AppCompatActivity() {
    val PICK_IMAGE = 100;
    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta)
//        Log.i("Cuenta",intent.getStringExtra("USER_INFO"))
        //NUEVO CAMBIO

        btnCambiarFoto.setOnClickListener(
            View.OnClickListener {
                AbrirGaleria()
            }
        )

        val dbHelper = BDUsuarios(applicationContext)
        val db = dbHelper?.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Usuarios WHERE usuario = ?", arrayOf<String>("omi") )
        var nombre:String=""
        var telefono:String=""
        with(cursor) {
            while (moveToNext()) {
                nombre = cursor.getString(getColumnIndex("nombre"))
                telefono = cursor.getString(getColumnIndex("telefono"))
            }
        }
        tvNombre.text = nombre
        tvTelefono.text = telefono
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
