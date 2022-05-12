package eps.uam.es.dadm.cards.database

import androidx.lifecycle.LiveData
import androidx.room.*
import eps.uam.es.dadm.cards.Deck
import eps.uam.es.dadm.cards.DeckWithCards

@Dao
interface DeckDao {
    /* ------------------------------------------------------ */
    /* Deck database methods */
    /* ------------------------------------------------------ */

    @Query("SELECT * FROM decks_table")
    fun getDecks(): LiveData<List<Deck>>

    @Query("SELECT COUNT(*) FROM decks_table")
    fun getDecksSize(): LiveData<Int>

    @Query("SELECT * FROM decks_table WHERE id = :id")
    fun getDeck(id: Long): LiveData<Deck?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDeck(deck: Deck)

    @Update
    fun updateDeck(deck: Deck)

    @Transaction
    @Query("SELECT * FROM decks_table")
    fun getDecksWithCards(): LiveData<List<DeckWithCards>>

    @Transaction
    @Query("SELECT * FROM decks_table WHERE id = :id")
    fun getDeckWithCards(id: Long): LiveData<List<DeckWithCards>>

    @Transaction
    @Query("SELECT COUNT(*) FROM decks_table WHERE id = :id")
    fun getDeckWithCardsSize(id: Long): LiveData<Int>

    @Query("DELETE FROM decks_table")
    fun deleteDecks()

}