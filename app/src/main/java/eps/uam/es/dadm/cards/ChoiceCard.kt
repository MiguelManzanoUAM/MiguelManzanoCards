package eps.uam.es.dadm.cards

import kotlin.math.round

class ChoiceCard(pregunta: String, respuesta: String, deckId: Long) : Card(pregunta, respuesta, deckId) {

    //Constructor secundario
    constructor():this(
        "question", "answer", 0
    )

    override fun show() {

        var option = 'a'

        println("   $question (INTRO para ver la respuesta)")
        print("¿Cúal crees que es la opción correcta? (INTRO para ver la respuesta)")

        print("   $answer (Teclea: 0 -> Difícil, 3 -> Dudo, 5 -> Fácil): ")
        quality = readLine()!!.toInt()
    }

    override fun toString(): String {
        return "choiceCard | $question | $optionA | $optionB | $optionC | $answer | $date | $id | ${round((easiness * 100.0) / 100.0)} | $quality | $repetitions | $nextPracticeDate\n"
    }


}