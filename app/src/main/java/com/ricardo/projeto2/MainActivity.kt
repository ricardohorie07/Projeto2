package com.ricardo.projeto2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

/**
 * View (MVVM): a Activity ficou "burra" de propósito.
 * Ela NÃO busca dados nem trata erro — só:
 *   1) pega o ViewModel
 *   2) observa o estado (uiState)
 *   3) reage a cada estado (mostra lista, mostra erro, etc.)
 *   4) navega quando um item é clicado
 */
class MainActivity : AppCompatActivity() {

    // Cria/recupera o ViewModel. O 'by viewModels()' garante que, ao girar a
    // tela, é o MESMO ViewModel (com os dados já carregados) que volta.
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvSeries = findViewById<RecyclerView>(R.id.rvSeries)
        rvSeries.layoutManager = LinearLayoutManager(this)

        observarEstado(rvSeries)
    }

    private fun observarEstado(rvSeries: RecyclerView) {
        // repeatOnLifecycle: só observa enquanto a tela está visível (boa prática,
        // evita trabalho e vazamento quando o app vai pro background).
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { estado ->
                    when (estado) {
                        is SeriesUiState.Loading -> {
                            // Aqui poderia aparecer um spinner de carregamento.
                        }
                        is SeriesUiState.Success -> {
                            rvSeries.adapter = SerieAdapter(estado.series) { serie ->
                                abrirDetalhe(serie)
                            }
                        }
                        is SeriesUiState.Error -> {
                            Toast.makeText(
                                this@MainActivity,
                                "Erro: ${estado.mensagem}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun abrirDetalhe(serie: Serie) {
        val intent = Intent(this, DetalheActivity::class.java)
        intent.putExtra("nome_serie", serie.nome)
        startActivity(intent)
    }
}