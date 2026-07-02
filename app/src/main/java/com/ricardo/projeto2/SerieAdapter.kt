package com.ricardo.projeto2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

/**
 * Adapter com DOIS tipos de item:
 *   - TIPO_GRAFICO (posição 0): o card com o gráfico (aparece 1 vez, no topo)
 *   - TIPO_SERIE   (posições 1+): os cards de episódio (a lista de verdade)
 *
 * Como o gráfico ocupa a posição 0, o episódio de índice 'i' da lista fica na
 * posição 'i + 1' da RecyclerView. Por isso, ao pegar o dado, usamos (position - 1).
 */
class SerieAdapter(
    private val lista: List<Serie>,
    private val onItemClick: (Serie) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TIPO_GRAFICO = 0
        private const val TIPO_SERIE = 1
    }

    // Diz qual tipo de item está em cada posição.
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TIPO_GRAFICO else TIPO_SERIE
    }

    // Total = 1 (gráfico) + quantidade de episódios.
    override fun getItemCount(): Int = lista.size + 1

    // Cria o ViewHolder certo dependendo do tipo.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TIPO_GRAFICO) {
            val view = inflater.inflate(R.layout.item_grafico, parent, false)
            GraficoViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_serie, parent, false)
            SerieViewHolder(view)
        }
    }

    // Preenche o ViewHolder certo.
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GraficoViewHolder) {
            holder.montarGrafico()
        } else if (holder is SerieViewHolder) {
            // -1 porque a posição 0 é o gráfico
            val serie = lista[position - 1]
            holder.tvNome.text = serie.nome
            holder.tvId.text = serie.id.toString()
            holder.tvData.text = serie.data
            holder.tvEpisodio.text = serie.episodio
            holder.itemView.setOnClickListener { onItemClick(serie) }
        }
    }

    // ---- ViewHolder do EPISÓDIO (card amarelo) ----
    class SerieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById(R.id.tvNome)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvData: TextView = itemView.findViewById(R.id.tvData)
        val tvEpisodio: TextView = itemView.findViewById(R.id.tvEpisodio)
    }

    // ---- ViewHolder do GRÁFICO ----
    class GraficoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val chart: CombinedChart = itemView.findViewById(R.id.combinedChartItem)

        fun montarGrafico() {
            val rotulos = listOf("08:00", "12:00", "16:00", "20:00")

            // BARRAS
            val barEntries = listOf(
                BarEntry(0f, 12f),
                BarEntry(1f, 27f),
                BarEntry(2f, 18f),
                BarEntry(3f, 30f)
            )
            val barDataSet = BarDataSet(barEntries, "Personagens (barra)")
            barDataSet.color = Color.parseColor("#2196F3")
            barDataSet.valueTextSize = 10f
            val barData = BarData(barDataSet)
            barData.barWidth = 0.45f

            // LINHA
            val lineEntries = listOf(
                Entry(0f, 20f),
                Entry(1f, 15f),
                Entry(2f, 25f),
                Entry(3f, 22f)
            )
            val lineDataSet = LineDataSet(lineEntries, "Episódios (linha)")
            lineDataSet.color = Color.parseColor("#FFC107")
            lineDataSet.lineWidth = 3f
            lineDataSet.circleRadius = 5f
            lineDataSet.setCircleColor(Color.parseColor("#FFC107"))
            lineDataSet.valueTextSize = 10f
            val lineData = LineData(lineDataSet)

            // COMBINA
            val combinedData = CombinedData()
            combinedData.setData(barData)
            combinedData.setData(lineData)
            chart.data = combinedData

            // EIXO X
            val xAxis = chart.xAxis
            xAxis.valueFormatter = IndexAxisValueFormatter(rotulos)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1f
            xAxis.axisMinimum = -0.5f
            xAxis.axisMaximum = rotulos.size - 0.5f
            xAxis.setDrawGridLines(false)

            // VISUAL
            chart.axisRight.isEnabled = false
            chart.description.isEnabled = false
            chart.animateY(1000)
            chart.invalidate()
        }
    }
}