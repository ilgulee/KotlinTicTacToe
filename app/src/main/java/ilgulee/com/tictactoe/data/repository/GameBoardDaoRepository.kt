package ilgulee.com.tictactoe.data.repository

import androidx.lifecycle.LiveData

interface GameBoardDaoRepository {
    fun addGameBoard(row: Int, column: Int, char: Char)
    fun getGameBoard(): LiveData<Array<CharArray>>
    fun resetGameBoard()
    fun getElementRightBeforeDestroyingActivity(): Char
    var isNewGame: Boolean
}