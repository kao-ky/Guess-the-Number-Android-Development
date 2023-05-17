package com.example.t2kao.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.t2kao.R
import com.example.t2kao.adapters.ResultAdapter
import com.example.t2kao.models.Result

class MainActivity : AppCompatActivity() {

    lateinit var resultList: MutableList<Result>
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultList = mutableListOf()
        val spGuess: Spinner = findViewById(R.id.spinnerGuess)
        val btnPlay: Button = findViewById(R.id.btnPlay)

        // setting spinner
        val availGuesses: Array<Int> = Array(10) { it + 1 }
        val spAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, availGuesses)
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spGuess.adapter = spAdapter

        // setting list adapter
        val resultAdapter = ResultAdapter(this, resultList)
        val lvGameResults: ListView = findViewById(R.id.lvGameResults)
        lvGameResults.adapter = resultAdapter

        btnPlay.setOnClickListener {
            val computerGuess: Int = (1..10).random()
            val playerGuess: Int = spGuess.selectedItem.toString().toInt()

            // cancel previous toast upon PLAY button clicked in case the message stays too long
            toast?.cancel()

            // check results
            if ( check(playerGuess, computerGuess) ) {
                toast = Toast.makeText(this, "You won!", Toast.LENGTH_SHORT)
            } else {
                toast = Toast.makeText(this, "You lost.", Toast.LENGTH_SHORT)
            }

            toast!!.show()

            resultAdapter.notifyDataSetChanged()
        }
    }

    private fun check( playerGuess: Int, pcGuess: Int ): Boolean {
        val playerWins: Boolean = playerGuess == pcGuess
        val result = Result( playerGuess, pcGuess, playerWins )
        resultList.add( 0, result )
        return playerWins
    }
}