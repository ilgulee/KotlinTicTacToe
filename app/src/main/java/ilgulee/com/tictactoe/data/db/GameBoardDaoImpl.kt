package ilgulee.com.tictactoe.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GameBoardDaoImpl : GameBoardDao {
    override var isNewGame: Boolean = true
        get() = field
        set(value) {
            field = value
        }

    companion object {
        private val TAG: String? = GameBoardDaoImpl::class.java.simpleName
    }


    private var lastAddedChar = ' '
    private var gameBoard = Array(3) { CharArray(3) { ' ' } }
    private val mutableGameBoard = MutableLiveData<Array<CharArray>>()

    init {
        mutableGameBoard.value = gameBoard
    }


    override fun addGameBoard(row: Int, column: Int, char: Char) {
        lastAddedChar = char
        gameBoard[row][column] = char
    }

    override fun getElementRightBeforeDestroyingActivity() = lastAddedChar

    override fun getGameBoard(): LiveData<Array<CharArray>> = mutableGameBoard as LiveData<Array<CharArray>>

    override fun resetGameBoard() {
        gameBoard = Array(3) { CharArray(3) { ' ' } }
        mutableGameBoard.value = gameBoard
    }
}