package com.vhaa.practica2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import helpers.Note

/**
 * Clase con el ViewHolder para convertir una colección de datos
 * en una lista de views que serán añadidos al RecyclerView y enlazados con dichos datos
 *
 * @author Victor Hugo Aguilar Aguilar
 */
class HistoricAdapter(historical: MutableList<Note>, context: Context) :
    RecyclerView.Adapter<HistoricAdapter.HistoricViewHolder>() {
    private var historical: MutableList<Note>
    private var context: Context

    init {
        this.historical = historical
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_historical, parent, false)
        return HistoricViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoricViewHolder, position: Int) {
        val item = historical[position]
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
        return historical.size
    }

    /**
     * Contiene la referencia del view creado para un ítem y la
     * información de su lugar en el RecyclerView. Esto te permitirá vincular directamente tu
     * información sobre los widgets del ítem.
     */
    class HistoricViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val _student = view.findViewById(R.id.lbl_historic_student) as TextView
        private val _mode = view.findViewById(R.id.lbl_historic_mode) as TextView

        private val _month = view.findViewById(R.id.lbl_historic_month) as TextView
        private val _day = view.findViewById(R.id.lbl_historic_day) as TextView
        private val _year = view.findViewById(R.id.lbl_historic_year) as TextView

        private val _note = view.findViewById(R.id.lbl_historic_note) as TextView
        private val _description = view.findViewById(R.id.lbl_historic_description) as TextView

        fun bind(note: Note, context: Context) {
            Log.d("bind", note.student)
            _student.text = note.student
            _mode.text = "(" + note.description + ")"

            _day.text = note.date.day.toString()
            _month.text = note.date.getMonthDescriptionEs()
            _year.text = note.date.year.toString()

            _note.text = note.average.toString()
            _description.text = note.description
        }
    }
}
