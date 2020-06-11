package com.example.comida

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.*
import java.sql.SQLException


class BDUsuarios(context : Context?) : SQLiteOpenHelper(context,"BDUsuarios.db",null,1) {

    private val DB_PATH = "/data/data/com.example.comida/databases/"
    private val DB_NAME = "BDUsuarios.db"

    fun copyDataBaseFromAssets(context: Context) {
        var myInput: InputStream? = null
        var myOutput: OutputStream? = null
        try {
            val folder = context.getDatabasePath("databases")

            if (!folder.exists())
                if (folder.mkdirs()) folder.delete()

            myInput = context.assets.open("$DB_NAME")

            val outFileName = DB_PATH + DB_NAME

            val f = File(outFileName)

            if (f.exists())
                return

            myOutput = FileOutputStream(outFileName)

            val buffer = ByteArray(1024)
            var length: Int = myInput.read(buffer)

            while (length > 0) {
                myOutput!!.write(buffer, 0, length)
                length = myInput.read(buffer)
            }

            myOutput!!.flush()
            myOutput.close()
            myInput.close()


        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}