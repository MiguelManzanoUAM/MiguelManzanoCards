package eps.uam.es.dadm.cards

import android.app.Application
import eps.uam.es.dadm.cards.database.CardDatabase
import timber.log.Timber
import java.time.LocalDateTime
import java.util.concurrent.Executors

class CardsApplication : Application(){

    private val executor = Executors.newSingleThreadExecutor()

    /*init{
        decks.add(Deck("Inglés", "Aprende algunos phrasal verbs con este mazo", "0"))
        cards.add(Card("To wake up", "Despertarse", "0"))

        decks.add(Deck("Futbol", "Conoce algunos datos curiosos del fútbol", "1" ))
        cards.add(Card("¿Qué jugador tiene más balones de oro?", "Lionel Messi", "1"))
        cards.add(Card("¿Qué jugador es el máximo goleador histórico?", "Cristiano Ronaldo","1"))

        var choice3 = ChoiceCard("¿A qué equipo pertenece el estadio Signal Iduna Park?", "Borussia Dortmund", "1")
        choice3.optionA = "Bayern Munich"
        choice3.optionB = "Manchester City"
        choice3.optionC = "Borussia Dortmund"
        cards.add(choice3)

        decks.add(Deck("Deportes y pasatiempos", "Aprende algunos conceptos básicos de tus pasatiempos favoritos", "2"))

        var choice1 = ChoiceCard("¿Qué pieza de ajedrez se mueve en L?", "Caballo", "2")
        choice1.optionA = "Alfil"
        choice1.optionB = "Torre"
        choice1.optionC = "Caballo"
        cards.add(choice1)

        var choice2 = ChoiceCard("¿Cuánto dura un set en tenis de mesa?", "21 puntos", "2")
        choice2.optionA = "21 puntos"
        choice2.optionB = "6 puntos"
        choice2.optionC = "11 puntos"
        cards.add(choice2)

    }*/

    override fun onCreate(){
        super.onCreate()
        val cardDatabase = CardDatabase.getInstance(applicationContext)
        executor.execute{

            //Limpieza base de datos actual
            cardDatabase.cardDao.deleteCards()
            cardDatabase.deckDao.deleteDecks()

            // Mazo Ingles
            cardDatabase.deckDao.addDeck(Deck("Inglés", "Aprende algunos phrasal verbs con este mazo", 1))
            cardDatabase.cardDao.addCard(Card("To wake up", "Despertarse", 1))
            cardDatabase.cardDao.addCard(Card("To rule out", "Descartar", 1))
            cardDatabase.cardDao.addCard(Card("To turn down", "Rechazar", 1))

            //Mazo Futbol
            cardDatabase.deckDao.addDeck(Deck("Futbol", "Conoce algunos datos curiosos del fútbol", 2))
            cardDatabase.cardDao.addCard(Card("¿Qué jugador tiene más balones de oro?", "Lionel Messi", 2))
            cardDatabase.cardDao.addCard(Card("¿Qué jugador es el máximo goleador histórico?", "Cristiano Ronaldo",2))

            var choice1 = ChoiceCard("¿A qué equipo pertenece el estadio Signal Iduna Park?", "Borussia Dortmund", 2)
            choice1.optionA = "Bayern Munich"
            choice1.optionB = "Manchester City"
            choice1.optionC = "Borussia Dortmund"
            cardDatabase.cardDao.addCard(choice1)

            //Mazo Deportes y Pasatiempos
            cardDatabase.deckDao.addDeck(Deck("Deportes y pasatiempos", "Aprende algunos conceptos básicos de tus pasatiempos favoritos", 3))

            var choice2 = ChoiceCard("¿Cuánto dura un set en tenis de mesa?", "21 puntos", 3)
            choice2.optionA = "21 puntos"
            choice2.optionB = "6 puntos"
            choice2.optionC = "11 puntos"

            var choice3 = ChoiceCard("¿Qué pieza de ajedrez se mueve en L?", "Caballo", 3)
            choice3.optionA = "Alfil"
            choice3.optionB = "Torre"
            choice3.optionC = "Caballo"

            cardDatabase.cardDao.addCard(choice2)
            cardDatabase.cardDao.addCard(choice3)

        }

        Timber.plant(Timber.DebugTree())
    }

    /*companion object {
        var cards: MutableList<Card> = mutableListOf()
        var decks: MutableList<Deck> = mutableListOf()

        fun numberOfDueCards(): Int{

            var number = 0

            cards.forEach{
                if(it.isDue(LocalDateTime.now())){
                    number += 1
                }
            }

            return number
        }

        fun getCard(id: String): Card?{

            var card : Card? = null

            cards.forEach{
                if(it.id == id){
                    card = it
                }
            }

            return card
        }

        fun addCard(card: Card){
            cards.add(card)
        }

        fun addDeck(deck: Deck){
            decks.add(deck)
        }

        fun getDeck(id: String): Deck?{

            var deck : Deck? = null

            decks.forEach{
                if(it.id == id){
                    deck = it
                }
            }

            return deck
        }

        fun getCardsOfDeck(id: String): List<Card>{
            val idCards: MutableList<Card> = mutableListOf()

            cards.forEach{
                if(it.deckId == id){
                    idCards.add(it)
                }
            }

            return idCards
        }

        fun removeCard(id: String){
            cards.forEach {
                if(it.id == id){
                    cards.remove(it)
                }
            }
        }

        fun removeDeck(id: String){
            cards.forEach {
                if(it.deckId == id){
                    cards.remove(it)
                }
            }

            decks.forEach {
                if(it.id == id){
                    decks.remove(it)
                }
            }
        }

    }*/

}