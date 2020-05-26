package com.example.comida

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsuariosViewModel(application:Application) : AndroidViewModel(application) {
    private val repository : UsuariosRepository

    val allUsuarios : LiveData<List<Usuarios>>

    init{
        val usuariosDao = UsuariosDatabase.getDatabase(application).usuariosDao()
        repository = UsuariosRepository(usuariosDao)
        allUsuarios = repository.allUsuarios
    }

    fun inser(usuario:Usuarios) = viewModelScope.launch(Dispatchers.IO){
        repository.inser(usuario)
    }
}