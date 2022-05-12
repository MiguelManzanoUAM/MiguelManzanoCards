package eps.uam.es.dadm.cards

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import eps.uam.es.dadm.cards.database.CardDatabase

class StatisticsViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    val decks: LiveData<List<Deck>> = CardDatabase.getInstance(context).deckDao.getDecks()
    val cards: LiveData<List<Card>> = CardDatabase.getInstance(context).cardDao.getCards()

    private val deckId = MutableLiveData<Long>()
    val deckWithCards: LiveData<List<DeckWithCards>> = Transformations.switchMap(deckId) {
        CardDatabase.getInstance(context).deckDao.getDeckWithCards(it)
    }

    fun loadDeckId(id: Long) {
        deckId.value = id
    }

}