package com.ricardo.projeto2

/**
 * Camada de dados (Repository).
 *
 * Serve de "porta única" para os dados. Hoje ela só repassa a chamada ao
 * RetrofitClient, mas é AQUI que entraria, no futuro, a lógica de:
 *   - cache em banco local (Room),
 *   - escolher entre buscar da rede ou do cache,
 *   - combinar várias fontes de dados.
 *
 * O ViewModel não precisa saber DE ONDE o dado vem — ele só pede ao Repository.
 * Isso é o que mantém o ViewModel limpo e fácil de testar.
 */
class SerieRepository {

    suspend fun buscarEpisodios(ids: String): List<Serie> {
        return RetrofitClient.api.getEpisodios(ids)
    }
}