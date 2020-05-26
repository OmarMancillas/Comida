package com.example.comida

import androidx.lifecycle.LiveData

class UsuariosRepository(private val usuariosDao: UsuariosDAO) {

    val allUsuarios: LiveData<List<Usuarios>> = usuariosDao.getAll()

    suspend fun inser(usuario:Usuarios){
        usuariosDao.insert(usuario)
    }
}