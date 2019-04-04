package ilgulee.com.tictactoe.data.db

class DatabaseImpl : Database {
    override val gameBoardDao: GameBoardDao = GameBoardDaoImpl()

}