package com.example.comida

import retrofit2.Call
import retrofit2.http.GET

interface TipoCambio {
    @GET("tipocambio/")
    fun getTCReal(): Call<String>
}