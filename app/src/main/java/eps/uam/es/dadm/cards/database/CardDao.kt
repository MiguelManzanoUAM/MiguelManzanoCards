package eps.uam.es.dadm.cards.database

import androidx.lifecycle.LiveData
import androidx.room.*
import eps.uam.es.dadm.cards.Card

@Dao
interface CardDao {

    /* ------------------------------------------------------ */
    /* Card database methods */
    /* ------------------------------------------------------ */

    @Query("SELECT * FROM cards_table")
    fun getCards(): LiveData<List<Card>>

    @Query("SELECT COUNT(*) FROM cards_table")
    fun getCardsSize(): LiveData<Int>

    @Query("SELECT * FROM cards_table WHERE id = :id")
    fun getCard(id: String): LiveData<Card?>

    @Insert
    fun addCard(card: Card)

    @Update
    fun updateCard(card: Card)

    @Query("SELECT * FROM cards_table WHERE deckId = :id")
    fun getCardsOfDeck(id: String): LiveData<List<Card>>

    @Query("DELETE FROM cards_table")
    fun deleteCards()

    @Delete
    fun deleteCard(card: Card)

    @Query("DELETE FROM cards_table WHERE deckId = :id")
    fun deleteCardsOfDeck(id: Long)
}