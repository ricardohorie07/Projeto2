package com.ricardo.projeto2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter: faz a ponte entre a lista de dados (List<Serie>) e a RecyclerView.
 * Responsabilidades:
 *   1) criar a "casca" visual de cada item   -> onCreateViewHolder
 *   2) preencher essa casca com os dados      -> onBindViewHolder
 *   3) informar quantos itens existem          -> getItemCount
 */
class SerieAdapter(
    private val lista: List<Serie>
) : RecyclerView.Adapter<SerieAdapter.SerieViewHolder>() {

    // 1) Cria a View de um item inflando o item_serie.xml.
    //    A RecyclerView reaproveita ("recicla") as Views, então isso roda poucas vezes.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_serie, parent, false)
        return SerieViewHolder(view)
    }

    // 2) Preenche os TextViews do item da posição "position" com os dados.
    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val serie = lista[position]
        holder.tvNome.text = serie.nome
        holder.tvId.text = serie.id.toString()
        holder.tvData.text = serie.data
        holder.tvEpisodio.text = serie.episodio
    }

    // 3) Quantidade de itens da lista.
    override fun getItemCount(): Int = lista.size

    /**
     * ViewHolder: guarda as referências dos TextViews de um item, para não
     * precisar chamar findViewById() a cada rolagem (mantém a lista fluida).
     */
    class SerieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById(R.id.tvNome)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvData: TextView = itemView.findViewById(R.id.tvData)
        val tvEpisodio: TextView = itemView.findViewById(R.id.tvEpisodio)
    }
}
