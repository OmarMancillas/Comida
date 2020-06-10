package com.example.comida.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuRestaurante(

    @PrimaryKey
    @ColumnInfo(name = "id_restaurante")
    var id_restaurante: Int=0,

    @ColumnInfo(name = "id_producto")
    var id_producto: Int = 0,

    @ColumnInfo(name = "descripcion_producto")
    var descripcion_producto: String?="",

    @ColumnInfo(name = "precio_producto")
    var precio_producto: Double=0.0
)