package helpers

/**
 * Clase Nota, contiene todos los datos de una nota por estudianteﬁ
 * containt: 10;9;2020;David González del Arco;6.67;Bien;Presencial
 *
 * @author Victor Hugo Aguilar Aguilar
 */
class Note (date: Date, student: String, average: Float, description: String, mode: String  ) {
      var date: Date
      var student: String
      var average: Float
      var description: String
      var mode: String

    init {
        this.date = date
        this.student = student
        this.average = average
        this.description = description
        this.mode = mode
    }
}