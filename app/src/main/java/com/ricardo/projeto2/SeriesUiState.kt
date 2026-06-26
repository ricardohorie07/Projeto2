package com.ricardo.projeto2

/**
 * Representa os possíveis ESTADOS da tela, de forma fechada (sealed).
 *
 * Em vez da Activity adivinhar "será que carregou? deu erro?", o ViewModel
 * diz explicitamente em qual estado a tela está. A View só reage a cada caso.
 *
 * - Loading: buscando os dados (poderia mostrar um spinner).
 * - Success: deu certo, traz a lista pronta.
 * - Error:   falhou, traz a mensagem do erro.
 */
sealed interface SeriesUiState {

    object Loading : SeriesUiState

    data class Success(val series: List<Serie>) : SeriesUiState

    data class Error(val mensagem: String) : SeriesUiState
}