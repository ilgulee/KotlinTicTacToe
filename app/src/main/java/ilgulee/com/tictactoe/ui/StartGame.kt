package ilgulee.com.tictactoe.ui

import android.view.View
import com.google.android.material.snackbar.Snackbar

class StartGame(private var message: String) {
    fun displayMessage(view: View) =
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction("Action", null).show()
}