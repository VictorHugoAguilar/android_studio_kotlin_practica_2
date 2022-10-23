package helpers

/**
 * Clase de soporte con los historicos
 *
 * @author Victor Hugo Aguilar Aguilar
 */
class Historical {
      var notes: MutableList<Note> = arrayListOf()
      var result: Boolean = false
      var message: String = ""

    constructor( notes: MutableList<Note>, result: Boolean, message: String){
        this.notes = notes
        this.result = result
        this.message = message
    }

    constructor()

}