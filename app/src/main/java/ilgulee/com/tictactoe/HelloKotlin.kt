package ilgulee.com.tictactoe

import android.view.View
import com.google.android.material.snackbar.Snackbar

class HelloKotlin {
    fun displayMessage(view: View){
        Snackbar.make(view,"Hello Kotlin!!",Snackbar.LENGTH_SHORT).setAction("Action",null).show()
    }
}