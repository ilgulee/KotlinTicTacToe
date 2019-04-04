package ilgulee.com.tictactoe.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ilgulee.com.tictactoe.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    companion object {
        private val TAG: String? = MainActivity::class.java.simpleName
    }

    override val kodein by closestKodein()
    val viewModelFactory: GameBoardViewModelFactory by instance()
    var gameBoard: Array<CharArray> = Array(3) { CharArray(3) } //3 rows X 3 columns
    var turn = ' '
    var isNewGame: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val viewModel = getViewModel()
        this.isNewGame = viewModel.isNewGame
        startGame(this.isNewGame)

        fab.setOnClickListener { view ->
            StartGame("New TicTacToe Game starts").displayMessage(view)
            viewModel.resetGameBoard()
            startGame(true)
        }


    }

    private fun startGame(newGame: Boolean) {
        val viewModel = getViewModel()
        if (newGame) {
            turn = 'X'
            viewModel.isNewGame = false
        } else {
            turn = if (viewModel.getElementRightBeforeDestroyingActivity() == 'X') 'O' else 'X'
        }
        text_view_turn.text = String.format(resources.getString(R.string.turn), turn)


        viewModel.getGameBoard().observe(this, Observer {
            for (i in 0 until gameBoard.size) {
                for (j in 0 until gameBoard[i].size) {
                    gameBoard[i][j] = it[i][j]
                    val cell = ((table_layout?.getChildAt(i)) as TableRow).getChildAt(j) as TextView
                    cell.text = gameBoard[i][j].toString()

                    cell.setOnClickListener {
                        cellClickListener(i, j)
                    }
                }
            }
        })
    }

    private fun cellClickListener(row: Int, column: Int) {
        val viewModel = getViewModel()
        if (gameBoard[row][column] == ' ') {
            gameBoard[row][column] = turn
            viewModel.addGameBoard(row, column, turn)
            val cell = ((table_layout?.getChildAt(row)) as TableRow).getChildAt(column) as TextView
            cell.text = turn.toString()
            turn = if (turn == 'X') 'O' else 'X'
            text_view_turn.text = String.format(resources.getString(R.string.turn), turn)
            checkGameStatus()
        }
    }

    private fun isBoardFull(gameBoard: Array<CharArray>): Boolean {
        for (i in 0 until gameBoard.size) {
            for (j in 0 until gameBoard[i].size) {
                if (gameBoard[i][j] == ' ') {
                    return false
                }
            }
        }
        return true
    }

    private fun checkGameStatus() {
        val viewModel = getViewModel()
        var state: String? = null

        if (isWinner(gameBoard, 'X')) {
            state = String.format(resources.getString(R.string.winner), 'X')
        } else if (isWinner(gameBoard, 'O')) {
            state = String.format(resources.getString(R.string.winner), 'O')
        } else {
            if (isBoardFull(gameBoard)) {
                state = resources.getString(R.string.draw)
            }
        }

        if (state != null) {
            text_view_turn?.text = state
            val builder = AlertDialog.Builder(this)
            builder.setMessage(state)
            builder.setPositiveButton(android.R.string.ok) { dialog, id ->
                Log.d(TAG, "dialog: $dialog, $id")
                viewModel.resetGameBoard()
                startGame(true)
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun isWinner(gameBoard: Array<CharArray>, w: Char): Boolean {
        for (i in 0 until gameBoard.size) {
            if (gameBoard[i][0] == w && gameBoard[i][1] == w && gameBoard[i][2] == w) {
                return true
            }

            if (gameBoard[0][i] == w && gameBoard[1][i] == w && gameBoard[2][i] == w) {
                return true
            }
        }
        if ((gameBoard[0][0] == w && gameBoard[1][1] == w && gameBoard[2][2] == w) ||
            (gameBoard[0][2] == w && gameBoard[1][1] == w && gameBoard[2][0] == w)
        ) {
            return true
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getViewModel(): GameBoardViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(GameBoardViewModel::class.java)
    }
}
