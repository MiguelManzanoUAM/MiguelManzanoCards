package eps.uam.es.dadm.cards

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import eps.uam.es.dadm.cards.database.CardDatabase

class DeckListViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    val decks: LiveData<List<Deck>> = CardDatabase.getInstance(context).deckDao.getDecks()
}