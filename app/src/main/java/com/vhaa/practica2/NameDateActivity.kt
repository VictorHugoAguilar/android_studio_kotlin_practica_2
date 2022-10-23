package com.vhaa.practica2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vhaa.practica2.databinding.ActivityNameDateBinding
import helpers.Date

/**
 * Página de obtención de datos del estudiante
 *
 * @author Victor Hugo Aguilar Aguilar
 */
class NameDateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNameDateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNameDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentName = intent.getStringExtra(MainActivity.STUDENT_NAME)
        binding.lblStudentName.text = studentName

        binding.btnCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            Log.d(MainActivity.STUDENT_DATA, "Cancelar cálculo")
            finish()
        }

        binding.btnAceptar.setOnClickListener {
            if (allFieldFilled() && validateDate() && validateNotes()) {
                val resultDateNoteIntent = Intent(this, MainActivity::class.java).apply {
                    putExtra( MainActivity.NOTE_ONE, binding.inputEval1.text.toString().toFloat())
                    putExtra( MainActivity.NOTE_TWO, binding.inputEval2.text.toString().toFloat())
                    putExtra( MainActivity.NOTE_TREE, binding.inputEval3.text.toString().toFloat())
                    putExtra( MainActivity.DATE_DAY, binding.inputDiaFecha.text.toString().toInt() )
                    putExtra( MainActivity.DATE_MONTH, binding.inputMesFecha.text.toString().toInt())
                    putExtra( MainActivity.DATE_YEAR, binding.inputAnioFecha.text.toString().toInt())
                }
                setResult(Activity.RESULT_OK, resultDateNoteIntent)
                Log.d(MainActivity.STUDENT_DATA, "Aceptar cálculo")
                finish()
            }
        }
    }

    /**
     * Verifica que se han introducido todos los valores de los campos
     *
     * @return true si se han introducido los valores del formulario
     */
    private fun allFieldFilled(): Boolean {

        var errorMessage = ""
        if (binding.lblStudentName.text.toString().isEmpty()) {
            errorMessage += "El nombre es un dato obligatorio\n"
        }
        if (binding.inputDiaFecha.text.isEmpty()) {
            errorMessage += "El día es un dato obligatorio\n"
        }
        if (binding.inputMesFecha.text.isEmpty()) {
            errorMessage += "El mes es un dato obligatorio\n"
        }
        if (binding.inputAnioFecha.text.isEmpty()) {
            errorMessage += "El año es un dato obligatorio\n"
        }
        if (binding.inputEval1.text.isEmpty() || binding.inputEval2.text.isEmpty() || binding.inputEval3.text.isEmpty()) {
            errorMessage += "Es obligatorio el valor de todas las evaluaciones.\n"
        }
        if (errorMessage.isNotBlank()) {
            Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
        }
        return errorMessage.isBlank()
    }


    /**
     * Validar los datos de la fecha introducidos
     *
     * @return true si no hay errores en los datos introducidos
     */
    private fun validateDate(): Boolean {
        val date = Date(
            binding.inputDiaFecha.text.toString().toInt(),
            binding.inputMesFecha.text.toString().toInt(),
            binding.inputAnioFecha.text.toString().toInt()
        )
        val errorMessageDate: String = date.isCorrect()

        if (errorMessageDate.isNotBlank()) {
            Toast.makeText(
                applicationContext,
                errorMessageDate,
                Toast.LENGTH_SHORT
            ).show()
        }
        return errorMessageDate.isBlank()
    }


    /**
     * Valida que hemos introducido todas las notas con los valores necesarios
     *
     * @return si esta correcto true : Boolean
     */
    private fun validateNotes(): Boolean {
        var errorMessage = ""

        if (!noteValid(binding.inputEval1.text.toString().toFloat())) {
            errorMessage += "La nota 1 no esta en el rango válido \n"
        }
        if (!noteValid(binding.inputEval2.text.toString().toFloat())) {
            errorMessage += "La nota 2 no esta en el rango válido \n"
        }
        if (!noteValid(binding.inputEval3.text.toString().toFloat())) {
            errorMessage += "La nota 3 no esta en el rango válido \n"
        }
        if (errorMessage.isNotBlank()) {
            Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
        }
        return errorMessage.isBlank()
    }

    /**
     * Validar si la nota por parámetro esta en el rango estimado de 0..10
     *
     * @return true si esta dentro del rango : Boolean
     */
    private fun noteValid(note: Float): Boolean {
        return note in 0.0..10.0
    }
}