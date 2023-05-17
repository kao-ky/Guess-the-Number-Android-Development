package com.example.t2kao.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.t2kao.R
import com.example.t2kao.models.Result

class ResultAdapter(context: Context, resultList: List<Result>) :
    ArrayAdapter<Result>(context, 0, resultList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item_result, parent, false)

        val tvComputerGuess = view?.findViewById<TextView>(R.id.tvComputerGuess)
        val tvPlayerGuess = view?.findViewById<TextView>(R.id.tvPlayerGuess)
        val ivResult = view?.findViewById<ImageView>(R.id.imgResult)
        val imgWin = R.drawable.win
        val imgLoss = R.drawable.loss

        val result = getItem(position)
        result?.let {
            tvComputerGuess?.text = "Computer Guess: ${it.computerGuess}"
            tvPlayerGuess?.text = "Your Guess: ${it.playerGuess}"

            val playerWins: Boolean = it.playerWins

            if (playerWins) {
                ivResult?.setImageResource(imgWin)
            } else {
                ivResult?.setImageResource(imgLoss)
            }
        }

        return view
    }
}