package com.example.comida

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.google.android.material.snackbar.Snackbar

class BDUsuarios(context : Context?) : SQLiteOpenHelper(context,"BDUsuarios.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("CREATE TABLE","Se ejecuto metodo CREATE TABLE")
        db?.execSQL("CREATE TABLE Usuarios(usuario varchar(50) NOT NULL PRIMARY KEY, password varchar(8) NOT NULL, nombre varchar(30) NOT NULL, telefono varchar(10) NOT NULL)")
//        Snackbar.make(,"Se ejecuto con exito.",Snackbar.LENGTH_LONG).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}