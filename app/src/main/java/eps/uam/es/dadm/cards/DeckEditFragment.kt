package eps.uam.es.dadm.cards

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import eps.uam.es.dadm.cards.database.CardDatabase
import eps.uam.es.dadm.cards.databinding.FragmentDeckEditBinding
import java.util.concurrent.Executors

class DeckEditFragment : Fragment() {

    private val executor = Executors.newSingleThreadExecutor()

    lateinit var binding: FragmentDeckEditBinding
    lateinit var deck: Deck
    lateinit var name: String
    lateinit var description: String
    var id: Long = 0

    private val viewModel by lazy {
        ViewModelProvider(this).get(DeckEditViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_deck_edit, container, false)
        val args = DeckEditFragmentArgs.fromBundle(requireArguments())
        viewModel.loadDeckId(args.deckId)
        viewModel.deck.observe(viewLifecycleOwner) {
            deck = it
            binding.deck = deck
            name = deck.name
            description = deck.description
            id = deck.id
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val nameTextWatcher = object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                deck.name = p0.toString()
            }
        }

        val descriptionTextWatcher = object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                deck.description = p0.toString()
            }
        }

        binding.nameEditText.addTextChangedListener(nameTextWatcher)
        binding.descriptionEditText.addTextChangedListener(descriptionTextWatcher)

        binding.acceptDeck.setOnClickListener {
            executor.execute{
                /*CardDatabase.getInstance(context).cardDao.update(card) */
                context?.let { it1 -> CardDatabase.getInstance(it1).deckDao.updateDeck(deck) }
            }
            it.findNavController().navigate(R.id.action_deckEditFragment_to_deckListFragment)
        }

        binding.cancelDeck.setOnClickListener {
            deck.name = name
            deck.description = description
            deck.id = id

            it.findNavController().navigate(R.id.action_deckEditFragment_to_deckListFragment)
        }

    }


}