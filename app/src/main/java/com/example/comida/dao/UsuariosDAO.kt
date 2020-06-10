package com.example.comida.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.comida.entities.Usuarios

@Dao
interface UsuariosDAO {
    @Query("SELECT * FROM Usuarios")
    fun getAll(): List<Usuarios>

    @Query("SELECT * FROM Usuarios WHERE usuario IN (:usuarios)")
    fun loadAllByIds(usuarios: Array<String?>): List<Usuarios>

    @Query("SELECT * FROM Usuarios WHERE usuario LIKE :usuario LIMIT 1")
    fun findByUsuario(usuario:String): Usuarios

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(usuario: Usuarios)

    @Query("DELETE FROM Usuarios")
    suspend fun deleteAll()
}