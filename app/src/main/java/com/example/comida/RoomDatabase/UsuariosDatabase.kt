package com.example.comida.RoomDatabase

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.comida.dao.RestaurantesDAO
import com.example.comida.dao.UsuariosDAO
import com.example.comida.entities.Restaurantes
import com.example.comida.entities.Usuarios

@Database(entities = arrayOf(Usuarios::class, Restaurantes::class), version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        fun get(application: Application): AppDatabase {
            return Room.databaseBuilder(application, AppDatabase::class.java, "Comida.db").build()
        }
    }
    abstract fun getUsuariosDAO(): UsuariosDAO
    abstract fun getRestaurantesDAO(): RestaurantesDAO

}