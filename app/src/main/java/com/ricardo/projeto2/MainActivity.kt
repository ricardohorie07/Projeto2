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
 * A MainActivity voltou a ser simples: o gráfico agora é responsabilidade do
 * Adapter (é o primeiro item da lista). Aqui só observamos o ViewModel e
 * entregamos os dados ao Adapter.
 */
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvSeries = findViewById<RecyclerView>(R.id.rvSeries)
        rvSeries.layoutManager = LinearLayoutManager(this)

        observarEstado(rvSeries)
    }

    private fun observarEstado(rvSeries: RecyclerView) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { estado ->
                    when (estado) {
                        is SeriesUiState.Loading -> { }
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