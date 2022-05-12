package eps.uam.es.dadm.cards.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eps.uam.es.dadm.cards.Card
import eps.uam.es.dadm.cards.Deck

@Database(entities = [Card::class, Deck::class], version = 1, exportSchema = false)
abstract class CardDatabase : RoomDatabase() {
    abstract val cardDao: CardDao
    abstract val deckDao: DeckDao

    companion object {
        @Volatile
        private var INSTANCE: CardDatabase? = null

        fun getInstance(context: Context): CardDatabase {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDatabase::class.java,
                    "cards_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }
}