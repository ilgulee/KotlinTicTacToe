package ilgulee.com.tictactoe

import android.app.Application
import ilgulee.com.tictactoe.data.db.Database
import ilgulee.com.tictactoe.data.db.DatabaseImpl
import ilgulee.com.tictactoe.data.db.GameBoardDao
import ilgulee.com.tictactoe.data.repository.GameBoardDaoRepository
import ilgulee.com.tictactoe.data.repository.GameBoardDaoRepositoryImpl
import ilgulee.com.tictactoe.ui.GameBoardViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class GameApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<Database>() with singleton { DatabaseImpl() }
        bind<GameBoardDao>() with singleton { instance<Database>().gameBoardDao }
        bind<GameBoardDaoRepository>() with singleton { GameBoardDaoRepositoryImpl(instance()) }
        bind() from provider { GameBoardViewModelFactory(instance()) }
    }
}