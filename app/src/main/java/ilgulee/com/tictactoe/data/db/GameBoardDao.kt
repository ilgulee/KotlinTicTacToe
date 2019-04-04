package ilgulee.com.tictactoe.data.db

import androidx.lifecycle.LiveData

interface GameBoardDao {
    fun addGameBoard(row: Int, column: Int, char: Char)
    fun getGameBoard(): LiveData<Array<CharArray>>
    fun resetGameBoard()
    fun getElementRightBeforeDestroyingActivity(): Char
    var isNewGame: Boolean
}