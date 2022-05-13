package eps.uam.es.dadm.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import eps.uam.es.dadm.cards.databinding.ListItemDeckBinding

class DeckAdapter : RecyclerView.Adapter<DeckAdapter.DeckHolder>() {

    var data = listOf<Deck>()
    lateinit var binding: ListItemDeckBinding

    inner class DeckHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var local = binding

        fun bind(deck: Deck) {
            local.deck = deck

            local.editItem.setOnClickListener {
                it.findNavController()
                    .navigate(DeckListFragmentDirections.actionDeckListFragmentToDeckEditFragment(deck.id))
            }

            local.deleteDeck?.setOnClickListener {

                it.findNavController()
                    .navigate(DeckListFragmentDirections.actionDeckListFragmentSelf())
            }

            itemView.setOnClickListener {
                it.findNavController()
                    .navigate(DeckListFragmentDirections.actionDeckListFragmentToCardListFragment(deck.id))
                //Toast.makeText(it.context, card.question, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ListItemDeckBinding.inflate(layoutInflater, parent, false)

        return DeckHolder(binding.root)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DeckHolder, position: Int) {
        holder.bind(data[position])
    }
}