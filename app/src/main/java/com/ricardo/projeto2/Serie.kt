package com.ricardo.projeto2

import com.google.gson.annotations.SerializedName

/**
 * Modelo de um episódio vindo da API.
 *
 * O JSON da API usa nomes em inglês (name, air_date...). Como nossos campos
 * estão em português, usamos @SerializedName pra "traduzir": ele diz ao Gson
 * de qual campo do JSON cada propriedade deve ser preenchida.
 *
 * Exemplo de JSON:
 * {
 *   "id": 4,
 *   "name": "M. Night Shaym-Aliens!",
 *   "air_date": "December 13, 2013",
 *   "episode": "S01E04"
 * }
 */
data class Serie(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val nome: String,

    @SerializedName("air_date")
    val data: String,

    @SerializedName("episode")
    val episodio: String
)
