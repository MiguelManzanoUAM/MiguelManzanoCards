package eps.uam.es.dadm.cards

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*
import kotlin.math.max
import kotlin.math.round

@Entity(tableName = "cards_table")
open class Card(
    @ColumnInfo(name = "card_question")
    var question: String,
    var answer: String,
    var deckId: Long = 0,
    var date: String = LocalDateTime.now().toString(),
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
){
    // Añadimos las nuevas propiedades
    var quality: Int = 0
    var repetitions : Int = 0
    var interval: Long = 1L
    var nextPracticeDate: String = date
    var easiness: Double = 2.5
    var answered: Boolean = false
    var aditionalInfo: Boolean = false
    var optionA: String = ""
    var optionB: String = ""
    var optionC: String = ""


    //Constructor secundario
    constructor():this("question", "answer", 0,
        LocalDateTime.now().toString(), UUID.randomUUID().toString()
    )

    //Función show
    open fun show(){

        print("   $question (INTRO para ver la respuesta)")
        var intro = readLine()
        print("   $answer (Teclea: 0 -> Difícil, 3 -> Dudo, 5 -> Fácil): ")
        quality = readLine()!!.toInt()
    }

    //Metodo que implementa el algoritmo para actualizar easiness

    fun update(currentDate: LocalDateTime){

        easiness = max(1.3, (easiness + 0.1 - (5-quality) * (0.08 + (5-quality) * 0.02)))

        repetitions = when(quality){
            0 -> 0
            else -> repetitions + 1
        }

        interval = when {
            repetitions <= 1 -> {
                1
            }
            repetitions == 2 -> {
                6
            }
            else -> {
                (round(interval.toDouble() * easiness)).toLong()
            }
        }

        nextPracticeDate = currentDate.plusDays(interval).toString()
    }

    //Metodo que muestra los detalles de la tarjeta
    /*private fun details(){
        val next = LocalDateTime.parse(nextPracticeDate)
        println("   eas = ${"%.2f".format(easiness)} rep = $repetitions int = $interval next = ${next.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}")
        println("")
    }*/


    override fun toString(): String {
        return "card | $question | $answer | $date | $id | ${round((easiness * 100.0) / 100.0)} | $quality | $repetitions | $nextPracticeDate\n"
    }

    //Para utilizar referencias a métodos
    /*fun updateFromView(view: View) {
        quality = when(view.id) {
            R.id.easy_button_view -> 5
            R.id.doubt_button_view -> 3
            R.id.difficult_button_view -> 0
            else -> throw Exception("Unavailable quality")
        }
        update(LocalDateTime.now())
    }*/

    //Para utilizar vinculación de escuchadores
    /*fun updateCard(quality: Int) {
        this.quality = quality
        update(LocalDateTime.now())
    }*/

    fun isDue(date: LocalDateTime):Boolean{
        return LocalDateTime.parse(nextPracticeDate).dayOfYear <= date.dayOfYear
    }

    /*fun isCloze():Boolean{
        if (this is Cloze){
            return true
        }
        return false
    }*/

    fun isChoiceCard():Boolean{
        //if (this is ChoiceCard){
        if (optionA != ""){
            return true
        }
        return false
    }
}