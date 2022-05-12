package eps.uam.es.dadm.cards

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="decks_table")
class Deck(
    @ColumnInfo(name = "deck_name")
    var name: String, var description: String,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
){
    fun deckIdToString():String{
        return id.toString()
    }
}


