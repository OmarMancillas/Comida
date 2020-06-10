package com.example.comida.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.comida.entities.Restaurantes
import com.example.comida.entities.Usuarios

@Dao
interface RestaurantesDAO {
    @Query("SELECT * FROM Restaurantes")
    fun getAll(): List<Restaurantes>

    @Query("SELECT * FROM Restaurantes WHERE id_restaurante= :id_restaurante")
    fun findById(id_restaurante : Int) : Restaurantes

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(restaurante : Restaurantes)
}