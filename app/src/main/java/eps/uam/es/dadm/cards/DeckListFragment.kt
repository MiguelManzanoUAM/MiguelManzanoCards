package eps.uam.es.dadm.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import eps.uam.es.dadm.cards.database.CardDatabase
import eps.uam.es.dadm.cards.databinding.FragmentDeckListBinding
import java.util.concurrent.Executors

class DeckListFragment: Fragment() {
    private lateinit var adapter: DeckAdapter

    private val executor = Executors.newSingleThreadExecutor()

    private val deckListViewModel by lazy {
        ViewModelProvider(this).get(DeckListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentDeckListBinding>(
            inflater,
            R.layout.fragment_deck_list,
            container,
            false
        )

        adapter = DeckAdapter()
        adapter.data = emptyList()
        //binding.deckListRecyclerView?.adapter = adapter
        binding.deckListRecyclerView.adapter = adapter

        binding.newDeckFab.setOnClickListener {
            val deck = Deck("","")

            executor.execute{
                context?.let { it1 -> CardDatabase.getInstance(it1).deckDao.addDeck(deck) }
            }

            it.findNavController().navigate(DeckListFragmentDirections.actionDeckListFragmentToDeckEditFragment(deck.id))
        }

        deckListViewModel.decks.observe(viewLifecycleOwner) {
            adapter.data = it
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }
}