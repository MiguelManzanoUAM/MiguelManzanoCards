package eps.uam.es.dadm.cards

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import eps.uam.es.dadm.cards.database.CardDatabase

class CardListViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    val cards: LiveData<List<Card>> = CardDatabase.getInstance(context).cardDao.getCards()
}