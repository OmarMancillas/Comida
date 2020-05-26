package com.example.comida

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsuariosDAO {
    @Query("SELECT * FROM Usuarios")
    fun getAll(): LiveData<List<Usuarios>>

    @Query("SELECT * FROM Usuarios WHERE usuario IN (:usuarios)")
    fun loadAllByIds(usuarios: Array<String?>): List<Usuarios>

    @Query("SELECT * FROM Usuarios WHERE usuario LIKE :usuario LIMIT 1")
    fun findByUsuario(usuario:String): Usuarios

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuario: Usuarios)

    @Query("DELETE FROM Usuarios")
    suspend fun deleteAll()
}