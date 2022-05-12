package eps.uam.es.dadm.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import eps.uam.es.dadm.cards.databinding.ListItemCardBinding

class CardAdapter : RecyclerView.Adapter<CardAdapter.CardHolder>() {

    var data = listOf<Card>()
    lateinit var binding: ListItemCardBinding

    inner class CardHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var local = binding

        fun bind(card: Card) {
            local.card = card

            local.showCard?.setOnClickListener {
                card.aditionalInfo = true
                local.invalidateAll()
            }

            local.hideCard?.setOnClickListener {
                card.aditionalInfo = false
                local.invalidateAll()
            }

            local.deleteCard?.setOnClickListener{
                /*CardsApplication.removeCard(card.id)*/
                it.findNavController()
                    .navigate(CardListFragmentDirections.actionCardListFragmentSelf(card.deckId))
            }

            itemView.setOnClickListener {
                it.findNavController()
                    .navigate(CardListFragmentDirections.actionCardListFragmentToCardEditFragment(card.id, card.deckId))
                //Toast.makeText(it.context, card.question, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ListItemCardBinding.inflate(layoutInflater, parent, false)

        return CardHolder(binding.root)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(data[position])
    }
}
