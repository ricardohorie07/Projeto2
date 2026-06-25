package com.ricardo.projeto2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto único (singleton) que configura e cria o Retrofit.
 *
 * 'object' em Kotlin = uma instância única pra todo o app, então a gente
 * monta o Retrofit uma vez só e reaproveita.
 *
 * - baseUrl: a parte fixa da URL (precisa terminar com "/").
 * - GsonConverterFactory: ensina o Retrofit a converter JSON em objetos Kotlin.
 */
object RetrofitClient {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    val api: RickMortyApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickMortyApi::class.java)
    }
}
