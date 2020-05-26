package com.example.comida

import android.content.ContentValues
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Usuarios::class), version = 1,exportSchema = false)
abstract class UsuariosDatabase : RoomDatabase() {
    abstract fun usuariosDao(): UsuariosDAO

    companion object{
        @Volatile
        private var INSTANCE: UsuariosDatabase?=null
        fun getDatabase(context:Context):UsuariosDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance =  Room.databaseBuilder(
                    context.applicationContext,
                    UsuariosDatabase::class.java,
                    "BDUsuarios").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}