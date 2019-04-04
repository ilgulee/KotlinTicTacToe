package ilgulee.com.tictactoe.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ilgulee.com.tictactoe.data.repository.GameBoardDaoRepository


class GameBoardViewModelFactory(private val gameBoardDaoRepository: GameBoardDaoRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GameBoardViewModel(gameBoardDaoRepository) as T
    }
}