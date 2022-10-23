package helpers

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vhaa.practica2.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * Clase helper de ficheros
 *
 * @author Victor Hugo Aguilar Aguilar
 */
class FileHelper {

    companion object {
        private const val RESULT_OK = "OK"
        private const val FILE_HELPER = "FileHelper"

        /**
         * Función para escribir un string en el fichero.
         * Si no existe el fichero lo creamos y si existe añadimos la información
         *
         * @return String resultado de la grabación en el fichero OK si es correcto
         * si no el mensaje del error
         */
        fun saveInFile(context: Context, dates: String) : String {
            return try {
                val osw = OutputStreamWriter(
                    context.openFileOutput(context.getString(R.string.FIlE_NAME_SAVED), AppCompatActivity.MODE_APPEND)
                )
                // escribimos en el fichero línea a línea.
                osw.write("$dates\n")
                // Se confirma la escritura
                osw.flush()
                // Cerramos el flujo
                osw.close()
                // Devolución del estado ok
                RESULT_OK
            } catch (e: IOException) {
                Log.d(FILE_HELPER, e.message.toString())
                // Si hay error retornamos el mensaje
                e.message.toString()
            }
        }

        /**
         * Función que leemos el fichero linea a linea por cada linea creamos un objeto del tipo
         * nota y lo añadimos a la lista que devolvemos
         *
         * ejemplo de linea a leer 10;9;2020;David González del Arco;6.67;Bien;Presencial
         *
         * @return Historical
         */
        fun readFile(context: Context) : Historical {
            val historical = Historical()
            val notes: MutableList<Note> = arrayListOf()
            // Se comprueba si existe el fichero.
            if (context.fileList().contains(context.getString(R.string.FIlE_NAME_SAVED))) {
                try {
                    val entrada = InputStreamReader(
                        context.openFileInput(context.getString(R.string.FIlE_NAME_SAVED)))
                    val br = BufferedReader(entrada)
                    // Leemos la primera línea
                    var linea = br.readLine()
                    while (!linea.isNullOrEmpty()) {
                        // Obtenemos los datos separandolo por el ;
                        val datos: List<String> = linea.split(";")
                        val date = Date(datos[0].toInt(), datos[1].toInt(), datos[2].toInt())
                        val student: String = datos[3]
                        val average: Float = datos[4].toFloat()
                        val description = datos[5]
                        val mode = datos[6]
                        val note = Note(date,student, average, description, mode)
                        notes.add(note)
                        // Leemos la siguiente línea del fichero
                        linea = br.readLine()
                    }
                    br.close()
                    entrada.close()
                } catch (e: IOException) {
                    historical.result = false
                    historical.message = e.message.toString()
                }
            } else {
                historical.result = false
                historical.message = context.getString(R.string.msn_error_file_not_exists)
            }
            historical.notes = notes
            historical.result = true
            return historical
        }
    }

}