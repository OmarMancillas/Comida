package com.example.comida.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class Usuarios(
    @PrimaryKey @ColumnInfo(name = "usuario") var usuario: String="",
    @ColumnInfo(name = "password") var password: String?="",
    @ColumnInfo(name = "nombre") var nombre: String?="",
    @ColumnInfo(name = "telefono") var telefono: String?=""
)