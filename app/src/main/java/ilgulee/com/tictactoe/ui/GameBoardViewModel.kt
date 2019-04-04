package ilgulee.com.tictactoe.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import ilgulee.com.tictactoe.data.repository.GameBoardDaoRepository

class GameBoardViewModel(private val gameBoardDaoRepository: GameBoardDaoRepository) : ViewModel() {
    companion object {
        private val TAG: String? = GameBoardViewModel::class.java.simpleName
    }

    var isNewGame: Boolean = gameBoardDaoRepository.isNewGame
        get() = field
        set(value) {
            field = value
        }

    fun addGameBoard(row: Int, column: Int, char: Char) {
        gameBoardDaoRepository.addGameBoard(row, column, char)
    }

    fun getGameBoard() = gameBoardDaoRepository.getGameBoard()

    fun resetGameBoard() {
        gameBoardDaoRepository.resetGameBoard()
    }

    fun getElementRightBeforeDestroyingActivity() = gameBoardDaoRepository.getElementRightBeforeDestroyingActivity()

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ViewModel Destroyed")
    }

}