package com.ricardo.projeto2

/**
 * Modelo de dados que representa UM item da lista.
 * Como é uma 'data class', o Kotlin já gera getters, equals, hashCode e toString.
 * Os campos batem com o card da imagem: nome, id, data e episódio.
 */
data class Serie(
    val id: Int,
    val nome: String,
    val data: String,
    val episodio: String
)
