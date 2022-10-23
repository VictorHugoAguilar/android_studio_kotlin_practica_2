package com.vhaa.practica2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.vhaa.practica2.databinding.ActivityHistoricalBinding
import helpers.FileHelper

/**
 * Vista para mostrar las notas historicas almacenados en el fichero local
 *
 * @author Victor Hugo Aguilar Aguilar
 */
class HistoricalActivity : AppCompatActivity() {
    private lateinit var adapter: HistoricAdapter
    private lateinit var binding: ActivityHistoricalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
    }

    /**
     * Configurar el recycler view con los datos que recuopera al leer los ficheros
     */
    private fun setUpRecyclerView() {
        val readFile = FileHelper.readFile(this)

        // Si hay datos o no ha surgido ningún error
        if (readFile.result) {
            // Esta opción a TRUE significa que el RV tendrá
            // hijos del mismo tamaño, optimiza su creación.
            binding.rvHistorical.setHasFixedSize(true)
            // Se indica el contexto para RV en forma de lista.
            //binding.rvHistorical.layoutManager = LinearLayoutManager(this)
            binding.rvHistorical.layoutManager = GridLayoutManager(this, 1)
            // Se genera el adapter.
            adapter = HistoricAdapter(readFile.notes, this)
            // Se asigna el adapter al RV.
            binding.rvHistorical.adapter = adapter
        } else {
            var messageError: String = getString(R.string.msg_not_found_data_file)
            if (readFile.message.isNotBlank()) {
                messageError = readFile.message
            }
            Toast.makeText(applicationContext, messageError, Toast.LENGTH_SHORT).show()
        }
    }

}