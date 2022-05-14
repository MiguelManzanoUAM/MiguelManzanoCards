package eps.uam.es.dadm.cards

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import eps.uam.es.dadm.cards.database.CardDatabase
import eps.uam.es.dadm.cards.databinding.FragmentCardListBinding
import java.util.concurrent.Executors

class CardListFragment: Fragment() {
    private lateinit var adapter: CardAdapter
    private val executor = Executors.newSingleThreadExecutor()

    private val cardListViewModel by lazy {
        ViewModelProvider(this).get(CardListViewModel::class.java)
    }

    private var reference = FirebaseDatabase
        .getInstance()
        .getReference("cards")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentCardListBinding>(
            inflater,
            R.layout.fragment_card_list,
            container,
            false
        )

        /* Preferencia Autenticación */
        SettingsActivity.setLoggedIn(requireContext(), true)

        val args = CardListFragmentArgs.fromBundle(requireArguments())
        val local = args.deckId
        //adapter.data = CardsApplication.getCardsOfDeck(local) ?: throw Exception("Wrong id")
        adapter = CardAdapter()
        adapter.data = emptyList()
        binding.cardListRecyclerView.adapter = adapter


        binding.newCardFab.setOnClickListener {
            val card = Card("","", args.deckId)
            executor.execute{
                context?.let { it1 -> CardDatabase.getInstance(it1).cardDao.addCard(card) }
            }
            it.findNavController().navigate(CardListFragmentDirections.actionCardListFragmentToCardEditFragment(card.id, local))
            /*reference.child(card.id).setValue(card)*/
        }

        binding.newClozeFab?.setOnClickListener {
            val cloze = Cloze("","", args.deckId)
            executor.execute{
                context?.let { it1 -> CardDatabase.getInstance(it1).cardDao.addCard(cloze) }
            }
            it.findNavController().navigate(CardListFragmentDirections.actionCardListFragmentToClozeEditFragment(cloze.id, local))
            /*reference.child(cloze.id).setValue(cloze)*/
        }

        binding.newChoiceFab?.setOnClickListener {
            val choice = ChoiceCard("","", args.deckId)
            executor.execute{
                context?.let { it1 -> CardDatabase.getInstance(it1).cardDao.addCard(choice) }
            }
            it.findNavController().navigate(CardListFragmentDirections.actionCardListFragmentToChoiceCardEditFragment(choice.id, local))
            /*reference.child(choice.id).setValue(choice)*/
        }

        cardListViewModel.cards.observe(viewLifecycleOwner) {
            var cardsOfDeck = mutableListOf<Card>()
            for(card in it) {
                if (card.deckId == args.deckId) {
                    cardsOfDeck.add(card)
                }
            }

            adapter.data = cardsOfDeck
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_card_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                startActivity(Intent(requireContext(), SettingsActivity::class.java))
            }
        }
        return true
    }
}