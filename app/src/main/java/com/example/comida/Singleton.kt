package com.example.comida

import com.example.comida.entities.MenuRestaurante
import com.example.comida.entities.Restaurantes

object Singleton {
    var dataset = arrayListOf<Restaurantes>()

    var datasetMenuRestaurante = arrayListOf<MenuRestaurante>()
}

