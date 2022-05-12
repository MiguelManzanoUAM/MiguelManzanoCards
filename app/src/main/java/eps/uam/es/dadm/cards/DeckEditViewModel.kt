package eps.uam.es.dadm.cards

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import eps.uam.es.dadm.cards.database.CardDatabase

class DeckEditViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val deckId = MutableLiveData<Long>()

    val deck: LiveData<Deck> = Transformations.switchMap(deckId) {
        CardDatabase.getInstance(context).deckDao.getDeck(it)
    }

    fun loadDeckId(id: Long) {
        deckId.value = id
    }
}