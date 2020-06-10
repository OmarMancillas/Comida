package com.example.comida.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurantes (
    @PrimaryKey @ColumnInfo(name = "id_restaurante") var id_restaurante: Int=0,
    @ColumnInfo(name = "nombre_restaurante") var nombre_restaurante: String?="",
    @ColumnInfo(name = "direccion") var direccion: String?="",
    @ColumnInfo(name = "telefono") var telefono: String?="",
    @ColumnInfo(name = "tipo_comida") var tipo_comida: Int=0
)