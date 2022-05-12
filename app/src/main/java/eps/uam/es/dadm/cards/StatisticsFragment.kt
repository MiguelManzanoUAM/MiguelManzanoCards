package eps.uam.es.dadm.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.snackbar.Snackbar
import eps.uam.es.dadm.cards.database.CardDatabase
import eps.uam.es.dadm.cards.databinding.FragmentStatisticsBinding
import java.util.concurrent.Executors

class StatisticsFragment: Fragment() {
    lateinit var binding: FragmentStatisticsBinding
    private val executor = Executors.newSingleThreadExecutor()

    private val viewModel: StatisticsViewModel by lazy {
        ViewModelProvider(this).get(StatisticsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics, container, false)

        /*binding.totalCardsNumber.text = CardsApplication.cards.size.toString()*/

        /*viewModel.cards.observe(viewLifecycleOwner){
            binding.totalCardsNumber.text = viewModel.cards.value?.size.toString()

        }*/

        /*executor.execute{
            binding.totalCardsNumber.text = context?.let { CardDatabase.getInstance(it).cardDao.getCardsSize().value.toString() }
        }*/

        /*binding.totalDecksNumber.text = CardsApplication.decks.size.toString()*/
        executor.execute{
            binding.totalDecksNumber.text = context?.let { CardDatabase.getInstance(it).deckDao.getDecksSize().value.toString() }
            binding.totalCardsNumber.text = context?.let { CardDatabase.getInstance(it).cardDao.getCardsSize().value.toString() }

        }

        val chart = binding.chart

        chart.data = setBarChartValues()
        chart.setDescriptionTextSize(10f)
        chart.setBackgroundColor(resources.getColor(R.color.primary_chart))
        chart.animateXY(3000, 3000)
        chart.xAxis.textSize = 16f
        chart.legend.textSize = 16f
        return binding.root
    }

    private fun setBarChartValues(): BarData {
        val xAxis: ArrayList<String> = arrayListOf()

        /*CardsApplication.decks.forEach {
            xAxis.add(it.name)
        }*/

        viewModel.deckWithCards.observe(viewLifecycleOwner){
            for(deck in it){
                xAxis.add(deck.deck.name)
            }
        }

        val barEntries: ArrayList<BarEntry> = arrayListOf()
        var deckCards: Int

        /*CardsApplication.decks.forEach {
            deckCards = 0
            deckCards = CardsApplication.getCardsOfDeck(it.id).size
            barEntries.add(BarEntry(deckCards.toFloat(), it.id.toInt()))
        }*/

        viewModel.deckWithCards.observe(viewLifecycleOwner) {
            for (deck in it) {
                executor.execute {
                    deckCards =
                        context?.let { it1 -> CardDatabase.getInstance(it1).deckDao.getDeckWithCardsSize(deck.deck.id).value }!!
                    barEntries.add(BarEntry(deckCards.toFloat(), deck.deck.id.toInt()))
                }
            }
        }

        val barDataSet = BarDataSet(barEntries, resources.getString(R.string.chart_card_decks))
        barDataSet.color = resources.getColor(R.color.primary_title)

        return BarData(xAxis, barDataSet)
    }

}