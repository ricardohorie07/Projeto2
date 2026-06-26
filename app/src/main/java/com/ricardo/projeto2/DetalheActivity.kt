package com.ricardo.projeto2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Tela de detalhe. Por enquanto só mostra um título.
 *
 * Ela lê o nome do episódio que foi enviado pela MainActivity através do Intent
 * (a "encomenda" que uma tela manda para a outra). Se nada for enviado,
 * usa "Detalhes" como padrão.
 */
class DetalheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        // Mostra a seta de voltar no topo
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val nome = intent.getStringExtra("nome_serie") ?: "Detalhes"
        val tvTitulo = findViewById<TextView>(R.id.tvTituloDetalhe)
        tvTitulo.text = nome
    }

    // Faz a seta funcionar (voltar pra tela anterior)
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}