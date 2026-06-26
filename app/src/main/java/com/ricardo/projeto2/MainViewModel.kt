package com.ricardo.projeto2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * O "cérebro" da tela (ViewModel).
 *
 * Toda a lógica que ANTES estava na MainActivity veio pra cá:
 *   - buscar os episódios na rede
 *   - tratar erro
 *   - guardar o estado atual da tela
 *
 * Vantagens:
 *   - sobrevive à rotação de tela (não refaz a busca à toa)
 *   - a Activity fica "burra", só exibindo o que o ViewModel manda
 *   - dá pra testar essa lógica sem subir nenhuma tela
 */
class MainViewModel : ViewModel() {

    private val repository = SerieRepository()

    // _uiState: versão "editável", só o ViewModel mexe (privada).
    private val _uiState = MutableStateFlow<SeriesUiState>(SeriesUiState.Loading)

    // uiState: versão "somente leitura" que a Activity observa (pública).
    val uiState: StateFlow<SeriesUiState> = _uiState.asStateFlow()

    // init: roda quando o ViewModel é criado -> já dispara a busca.
    init {
        buscarEpisodios()
    }

    fun buscarEpisodios() {
        // viewModelScope: coroutine atrelada ao ViewModel (cancelada junto com ele).
        viewModelScope.launch {
            _uiState.value = SeriesUiState.Loading
            try {
                val episodios = repository.buscarEpisodios("4,7,13,21")
                _uiState.value = SeriesUiState.Success(episodios)
            } catch (e: Exception) {
                _uiState.value = SeriesUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}