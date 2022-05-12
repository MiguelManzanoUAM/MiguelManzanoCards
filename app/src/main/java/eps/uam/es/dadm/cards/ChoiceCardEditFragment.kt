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
import com.google.firebase.database.FirebaseDatabase
import eps.uam.es.dadm.cards.database.CardDatabase
import eps.uam.es.dadm.cards.databinding.FragmentChoiceCardEditBinding
import java.util.concurrent.Executors

class ChoiceCardEditFragment : Fragment() {

    private val executor = Executors.newSingleThreadExecutor()

    lateinit var binding: FragmentChoiceCardEditBinding
    lateinit var card: Card
    lateinit var question: String
    lateinit var answer: String

    private val viewModel by lazy {
        ViewModelProvider(this).get(CardEditViewModel::class.java)
    }

    private var reference = FirebaseDatabase
        .getInstance()
        .getReference("cards")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choice_card_edit, container, false)
        val args = ChoiceCardEditFragmentArgs.fromBundle(requireArguments())
        viewModel.loadCardId(args.cardId)
        viewModel.card.observe(viewLifecycleOwner) {
            card = it
            binding.card = card
            question = card.question
            answer = card.answer
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val questionTextWatcher = object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                card.question = p0.toString()
            }
        }

        val optionATextWatcher = object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                card.optionA = p0.toString()
            }
        }

        val optionBTextWatcher = object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                card.optionB = p0.toString()
            }
        }

        val optionCTextWatcher = object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                card.optionC = p0.toString()
            }
        }

        val answerTextWatcher = object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                card.answer = p0.toString()
            }
        }

        binding.questionEditText.addTextChangedListener(questionTextWatcher)
        binding.option1EditText.addTextChangedListener(optionATextWatcher)
        binding.option2EditText.addTextChangedListener(optionBTextWatcher)
        binding.option3EditText.addTextChangedListener(optionCTextWatcher)
        binding.answerEditText.addTextChangedListener(answerTextWatcher)

        binding.acceptCard.setOnClickListener {
            /*executor.execute{
                context?.let { it1 -> CardDatabase.getInstance(it1).cardDao.updateCard(card) }
            }*/
            it.findNavController().navigate(ChoiceCardEditFragmentDirections.actionChoiceCardEditFragmentToCardListFragment(card.deckId))
            reference.child(card.id).setValue(card)
        }

        binding.cancelCard.setOnClickListener {
            card.question = question
            card.answer = answer

            it.findNavController().navigate(ChoiceCardEditFragmentDirections.actionChoiceCardEditFragmentToCardListFragment(card.deckId))
        }

    }
}