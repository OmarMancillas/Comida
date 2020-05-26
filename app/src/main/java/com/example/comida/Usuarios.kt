package com.example.comida

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class Usuarios(
    @PrimaryKey @ColumnInfo(name = "usuario") val usuario: String,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "nombre") val nombre: String?,
    @ColumnInfo(name = "telefono") val telefono: String?
)