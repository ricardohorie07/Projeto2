package com.ricardo.projeto2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Activity principal. Aqui a gente:
 *   1) pega a RecyclerView do XML
 *   2) define o LayoutManager (lista vertical)
 *   3) cria os dados (List<Serie>)
 *   4) cria o Adapter e liga na RecyclerView
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1) Referência da RecyclerView declarada no activity_main.xml
        val rvSeries = findViewById<RecyclerView>(R.id.rvSeries)

        // 2) Lista vertical (um item embaixo do outro)
        rvSeries.layoutManager = LinearLayoutManager(this)

        // 3) Dados de exemplo (depois troca por dados reais / API)
        val series = listOf(
            Serie(1, "Breaking Bad", "20/01/2008", "Episódio 1"),
            Serie(2, "Dark", "01/12/2017", "Episódio 1"),
            Serie(3, "The Office", "24/03/2005", "Episódio 1"),
            Serie(4, "Mr. Robot", "24/06/2015", "Episódio 1")
        )

        // 4) Adapter ligado na RecyclerView
        rvSeries.adapter = SerieAdapter(series)
    }
}
