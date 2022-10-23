package helpers

/**
 * Clase custom de fecha
 *
 * @author Victor Hugo Aguilar Aguilar
 */
class Date(day: Int, month: Int, year: Int) {

    var day: Int
    var month: Int
    var year: Int

    init {
        this.day = day
        this.month = month
        this.year = year
    }

    /**
     * Comprueba si los datos introducidos son correctos y cumplen con el formato de fecha
     *
     * @return String con el mensaje de error en el campo
     */
    fun isCorrect(): String {
        val diasMes = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        if (year < 1900 || year >= 2100) {
            return "El año no puede ser inferior a 1900 ni superior a 2100"
        }
        if (month < 1 || month > 12) {
            return "El mes no puede ser inferior a 1 ni superior de 12"
        }
        if (isLeadYear()) {
            if (month == 2 && (day <= 0 || day > 29)) {
                return "El mes tiene 29 días, el año es bisiesto"
            }
        } else {
            if (day <= 0 || day > diasMes[month - 1]) {
                return "El día no es correcto para el mes informado"
            }
        }
        return ""
    }

    /**
     * Comprueba si el año es bisiesto
     *
     * @return true si es bisiesto
     */
    private fun isLeadYear(): Boolean {
        return year % 400 == 0 ||
                (year % 4 == 0 && year % 100 != 0)
    }

    /**
     * Devuelve la descripción del mes en efecto
     *
     * @return String con la descripción equivalente al mes en curso
     */
    fun getMonthDescriptionEs(): String {
        val listMonth: Array<String> = arrayOf(
            "Enero", "Febrero", "Marzo", "Abril", "Mayo",
            "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        )
        return listMonth[this.month - 1]
    }

}