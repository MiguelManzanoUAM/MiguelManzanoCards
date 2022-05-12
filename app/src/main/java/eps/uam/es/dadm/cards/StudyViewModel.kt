package eps.uam.es.dadm.cards

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import eps.uam.es.dadm.cards.database.CardDatabase
import java.time.LocalDateTime
import java.util.concurrent.Executors

class StudyViewModel(application: Application) : AndroidViewModel(application) {

    private val executor = Executors.newSingleThreadExecutor()
    private val context = getApplication<Application>().applicationContext

    var card: Card? = null
    var cards: LiveData<List<Card>> = CardDatabase.getInstance(context).cardDao.getCards()
    var dueCard: LiveData<Card?> = Transformations.map(cards) {
        try {
            it.filter {
                it.isDue(LocalDateTime.now())
            }.random()
        } catch (e: Exception) {
            null
        }
    }
    var nDueCards: LiveData<Int> = Transformations.map(cards) {
        it.filter {
            it.isDue(LocalDateTime.now())
        }.size
    }

    fun update(quality: Int) {
        card?.quality = quality
        card?.update(LocalDateTime.now())
        executor.execute {
            CardDatabase.getInstance(context).cardDao.updateCard(card!!)
        }
    }
}