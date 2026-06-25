package com.ricardo.projeto2

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface que descreve os endpoints da API.
 * O Retrofit lê essas annotations e gera a implementação automaticamente —
 * você nunca escreve o "corpo" desses métodos.
 *
 * @GET("episode/{ids}") -> faz uma requisição GET para
 *   https://rickandmortyapi.com/api/episode/4,7,13,21
 *
 * O {ids} no caminho é preenchido pelo parâmetro marcado com @Path("ids").
 *
 * 'suspend' significa que é uma função de coroutine: ela pode "pausar"
 * enquanto espera a rede, sem travar a tela.
 */
interface RickMortyApi {

    @GET("episode/{ids}")
    suspend fun getEpisodios(@Path("ids") ids: String): List<Serie>
}
