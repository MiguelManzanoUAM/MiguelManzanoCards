package eps.uam.es.dadm.cards
import java.time.LocalDateTime
import java.util.*
import kotlin.math.round

class Cloze(pregunta: String, respuesta: String, deckId: Long) : Card(pregunta, respuesta, deckId) {

    //Constructor secundario
    constructor():this(
        "question", "answer", 0
    )

    override fun show() {
        print("   $question (INTRO para ver la respuesta)")
        var intro = readLine()!!.toString()

        val respuestaCompleta = question.split("*")

        when {
            respuestaCompleta.size == 3 -> {
                print("   ${respuestaCompleta[0]} $answer ${respuestaCompleta[2]} (Teclea: 0 -> Difícil, 3 -> Dudo, 5 -> Fácil): ")

            }
            question[0] == '*' -> {
                print("   $answer ${respuestaCompleta[1]} (Teclea: 0 -> Difícil, 3 -> Dudo, 5 -> Fácil): ")
            }
            else -> {
                print("   ${respuestaCompleta[1]} $answer (Teclea: 0 -> Difícil, 3 -> Dudo, 5 -> Fácil): ")
            }
        }
        quality = readLine()!!.toInt()
    }

    override fun toString(): String {
        return "cloze | $question | $answer | $date | $id | ${round((easiness * 100.0) / 100.0)} | $quality | $repetitions | $nextPracticeDate\n"
    }

}