package com.example.comida

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.google.android.material.snackbar.Snackbar

class BDUsuarios(context : Context?) : SQLiteOpenHelper(context,"BDUsuarios.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}