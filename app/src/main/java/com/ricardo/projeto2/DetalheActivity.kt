package com.ricardo.projeto2

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

class DetalheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        // Seta de voltar no topo (barra do app)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val nome = intent.getStringExtra("nome_serie") ?: "Detalhes"
        findViewById<TextView>(R.id.tvTituloDetalhe).text = nome

        val chart = findViewById<CombinedChart>(R.id.combinedChart)
        montarGrafico(chart)
    }

    private fun montarGrafico(chart: CombinedChart) {
        val rotulos = listOf("08:00", "10:00", "12:00", "14:00", "16:00", "18:00")  // 6


        // ---- BARRAS ----
        // >>> AQUI você troca pelos dados reais. <<<
        val barEntries = listOf(
            BarEntry(0f, 12f),
            BarEntry(1f, 27f),
            BarEntry(2f, 18f),
            BarEntry(3f, 25f),
            BarEntry(4f, 30f),
            BarEntry(5f, 41f)

        )
        val barDataSet = BarDataSet(barEntries, "Personagens (barra)")
        barDataSet.color = Color.parseColor("#2196F3")  // azul
        barDataSet.valueTextSize = 12f
        val barData = BarData(barDataSet)
        barData.barWidth = 0.45f                         // largura das barras

        // ---- LINHA ----
        // >>> AQUI você troca pelos dados reais. <<<
        val lineEntries = listOf(
            Entry(0f, 20f),
            Entry(1f, 15f),
            Entry(2f, 25f),
            Entry(3f, 22f),
            Entry(4f, 13f),
            Entry(5f, 07f)
        )
        val lineDataSet = LineDataSet(lineEntries, "Episódios (linha)")
        lineDataSet.color = Color.parseColor("#FFC107") // amarelo
        lineDataSet.lineWidth = 3f
        lineDataSet.circleRadius = 5f
        lineDataSet.setCircleColor(Color.parseColor("#FFC107"))
        lineDataSet.valueTextSize = 12f
        val lineData = LineData(lineDataSet)

        // ---- COMBINA OS DOIS ----
        val combinedData = CombinedData()
        combinedData.setData(barData)
        combinedData.setData(lineData)
        chart.data = combinedData

        // ---- EIXO X ----
        val xAxis = chart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(rotulos)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.axisMinimum = -0.5f                        // alinha as barras com os rótulos
        xAxis.axisMaximum = rotulos.size - 0.5f
        xAxis.setDrawGridLines(false)

        // ---- AJUSTES VISUAIS ----
        chart.axisRight.isEnabled = false
        chart.description.isEnabled = false
        chart.animateY(1000)
        chart.invalidate()                               // redesenha (essencial!)
    }

    // Faz a seta de voltar funcionar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}