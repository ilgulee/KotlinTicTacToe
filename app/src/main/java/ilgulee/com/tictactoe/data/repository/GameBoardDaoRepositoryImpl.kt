package ilgulee.com.tictactoe.data.repository

import androidx.lifecycle.LiveData
import ilgulee.com.tictactoe.data.db.GameBoardDao

class GameBoardDaoRepositoryImpl(private val gameBoardDao: GameBoardDao) : GameBoardDaoRepository {
    override var isNewGame: Boolean = gameBoardDao.isNewGame
        get() = field
        set(value) {
            field = value
        }

    override fun getElementRightBeforeDestroyingActivity() = gameBoardDao.getElementRightBeforeDestroyingActivity()

    override fun addGameBoard(row: Int, column: Int, char: Char) {
        gameBoardDao.addGameBoard(row, column, char)
    }

    override fun getGameBoard(): LiveData<Array<CharArray>> = gameBoardDao.getGameBoard()

    override fun resetGameBoard() {
        gameBoardDao.resetGameBoard()
    }

}