package com.ricardo.projeto2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter da lista.
 *
 * Agora ele recebe um segundo parâmetro: 'onItemClick', uma função que será
 * chamada quando o usuário tocar num item. O Adapter não sabe (nem precisa saber)
 * o que acontece no clique — ele só "avisa" quem o criou, passando a Serie clicada.
 * Quem decide o que fazer é a MainActivity.
 */
class SerieAdapter(
    private val lista: List<Serie>,
    private val onItemClick: (Serie) -> Unit
) : RecyclerView.Adapter<SerieAdapter.SerieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_serie, parent, false)
        return SerieViewHolder(view)
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val serie = lista[position]
        holder.tvNome.text = serie.nome
        holder.tvId.text = serie.id.toString()
        holder.tvData.text = serie.data
        holder.tvEpisodio.text = serie.episodio

        // Quando o item inteiro (o card) for clicado, dispara a função recebida,
        // entregando a Serie daquela posição.
        holder.itemView.setOnClickListener {
            onItemClick(serie)
        }
    }

    override fun getItemCount(): Int = lista.size

    class SerieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById(R.id.tvNome)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvData: TextView = itemView.findViewById(R.id.tvData)
        val tvEpisodio: TextView = itemView.findViewById(R.id.tvEpisodio)
    }
}