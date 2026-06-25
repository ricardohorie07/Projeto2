package com.ricardo.projeto2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

/**
 * Agora a MainActivity busca os dados da API em vez de usar uma lista fixa.
 *
 * Fluxo:
 *   1) monta a RecyclerView (igual antes)
 *   2) dentro de lifecycleScope.launch { } chama a API (em background)
 *   3) quando os dados chegam, joga no mesmo SerieAdapter de sempre
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvSeries = findViewById<RecyclerView>(R.id.rvSeries)
        rvSeries.layoutManager = LinearLayoutManager(this)

        // Busca os episódios e atualiza a tela
        buscarEpisodios(rvSeries)
    }

    private fun buscarEpisodios(rvSeries: RecyclerView) {
        // lifecycleScope: a coroutine vive junto com a Activity (some se a tela fechar).
        lifecycleScope.launch {
            try {
                // Chamada de rede. Como é 'suspend', ela espera sem travar a tela.
                val episodios = RetrofitClient.api.getEpisodios("4,7,13,21")

                // Chegou a resposta -> liga no Adapter
                rvSeries.adapter = SerieAdapter(episodios)

            } catch (e: Exception) {
                // Deu erro (sem internet, API fora do ar, etc.)
                Log.e("MainActivity", "Erro ao buscar episódios", e)
                Toast.makeText(
                    this@MainActivity,
                    "Erro ao carregar dados: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
