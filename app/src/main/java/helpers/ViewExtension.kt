package helpers

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Metodo que extiende una funcion de view, para ocultar el teclado
 *
 * @author Victor Hugo Aguilar Aguilar
 */
fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken,0)
}
