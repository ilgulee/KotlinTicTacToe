package ilgulee.com.tictactoe

import android.view.View
import com.google.android.material.snackbar.Snackbar

class HelloKotlin(private var message: String) {
    fun displayMessage(view: View) =
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction("Action", null).show()
}