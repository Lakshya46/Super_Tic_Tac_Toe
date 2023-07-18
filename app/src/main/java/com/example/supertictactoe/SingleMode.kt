package com.example.supertictactoe

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.system.exitProcess
// This code work for both single and multiplayer
var playerTurn = true
class SingleMode : AppCompatActivity() {

    lateinit var player1TV: TextView
    lateinit var player2TV: TextView
    lateinit var box1btn: Button
    lateinit var box2btn: Button
    lateinit var box3btn: Button
    lateinit var box4btn: Button
    lateinit var box5btn: Button
    lateinit var box6btn: Button
    lateinit var box7btn: Button
    lateinit var box8btn: Button
    lateinit var box9btn: Button
    lateinit var resetBtn: Button
    var player1count = 0
    var player2count = 0
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptyCells = ArrayList<Int>()
    var activeUser = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_mode)
        player1TV = findViewById(R.id.play1)
        player2TV = findViewById(R.id.play2)
        box1btn = findViewById(R.id.idBtnBox1)
        box2btn = findViewById(R.id.idBtnBox2)
        box3btn = findViewById(R.id.idBtnBox3)
        box4btn = findViewById(R.id.idBtnBox4)
        box5btn = findViewById(R.id.idBtnBox5)
        box6btn = findViewById(R.id.idBtnBox6)
        box7btn = findViewById(R.id.idBtnBox7)
        box8btn = findViewById(R.id.idBtnBox8)
        box9btn = findViewById(R.id.idBtnBox9)
        resetBtn = findViewById(R.id.resetbtn)
        resetBtn.setOnClickListener {
            reset()
        }
    }
    private fun reset() {
        player1.clear()
        player2.clear()
        emptyCells.clear()
        activeUser = 1
        for (i in 1..9) {
            var buttonSelected: Button?
            buttonSelected = when (i) {
                1 -> box1btn
                2 -> box2btn
                3 -> box3btn
                4 -> box4btn
                5 -> box5btn
                6 -> box6btn
                7 -> box7btn
                8 -> box8btn
                9 -> box9btn
                else -> {
                    box1btn
                }
            }
            buttonSelected.isEnabled = true
            buttonSelected.text = ""
            player1TV.text = "Player 1 : $player1count"
            player2TV.text = "Player 2 : $player2count"
        }
    }
    fun buttonClick(view: View) {
        if (playerTurn) {
            val but = view as Button
            var cellID = 0
            when (but.id) {
                R.id.idBtnBox1 -> cellID = 1
                R.id.idBtnBox2 -> cellID = 2

                R.id.idBtnBox3 -> cellID = 3
                R.id.idBtnBox4 -> cellID = 4
                R.id.idBtnBox5 -> cellID = 5
                R.id.idBtnBox6 -> cellID = 6
                R.id.idBtnBox7 -> cellID = 7
                R.id.idBtnBox8 -> cellID = 8
                R.id.idBtnBox9 -> cellID = 9
            }
            playerTurn = false
            Handler().postDelayed(Runnable { playerTurn = true }, 600)
            playeNow(but, cellID)
        }
    }

    private fun playeNow(buttonSelected: Button, currcell: Int) {
        val audio = MediaPlayer.create(this,R.raw.drop)
        if (activeUser == 1) {
            buttonSelected.text = "X"
            buttonSelected.setTextColor(Color.parseColor("#a538ed"))
            player1.add(currcell)
            emptyCells.add(currcell)
            audio.start()
            buttonSelected.isEnabled = false
            Handler().postDelayed(Runnable { audio.release() },200)
            val checkWinner = checkWinner()
            if (checkWinner == 1) {
                Handler().postDelayed(Runnable { reset() }, 2000)
            } else if (singUser) {
                Handler().postDelayed(Runnable { robot() }, 500)
            } else {
                activeUser = 2
            }

        } else {
            buttonSelected.text = "O"
            audio.start()
            buttonSelected.setTextColor(Color.parseColor("#f2b851"))
            activeUser = 1
            player2.add(currcell)
            emptyCells.add(currcell)
            Handler().postDelayed(Runnable { audio.release() },200)
            buttonSelected.isEnabled = false
            val checkWinner = checkWinner()
            if (checkWinner == 1) {
                Handler().postDelayed(Runnable { reset() }, 4000)
            }
        }

    }


    private fun robot() {
        val rnd = (1..9).random()
        if(emptyCells.contains(rnd)){
            robot()
        }else {

            val buttonSelected : Button?
            buttonSelected = when(rnd){

                1-> box1btn
                2-> box2btn
                3-> box3btn
                4-> box4btn
                5-> box5btn
                6-> box6btn
                7-> box7btn
                8-> box8btn
                9-> box9btn
                else ->{ box1btn
                }
            }
            emptyCells.add(rnd)
            val audio = MediaPlayer.create(this,R.raw.drop)
            audio.start()
            Handler().postDelayed(Runnable {audio.release()  }, 500)
            buttonSelected.text  = "o"
            buttonSelected.setTextColor(Color.parseColor("#f2b851"))
            player2.add(rnd)
            buttonSelected.isEnabled = false
            var checkWinner = checkWinner()
            if (checkWinner== 1)
            {
                Handler().postDelayed(Runnable { reset() },2000)
            }
        }
    }

    private fun checkWinner():Int{

        val audio = MediaPlayer.create(this,R.raw.mario)
        if ((player1.contains(1) && player1.contains(2) && player1.contains(3) ) ||
            (player1.contains(1) && player1.contains(4) && player1.contains(7) )||
            (player1.contains(3) && player1.contains(6) && player1.contains(9) )||
            (player1.contains(7) && player1.contains(8) && player1.contains(9) )||
            (player1.contains(4) && player1.contains(5) && player1.contains(6) )||
            (player1.contains(1) && player1.contains(5) && player1.contains(9) )||
            (player1.contains(3) && player1.contains(5) && player1.contains(7) )||
            (player1.contains(2) && player1.contains(5) && player1.contains(8))
        ) {
            player1count+=1
            buttonDisable()
            audio.start()
            disableReset()
            Handler().postDelayed(Runnable { audio.release() },4000)
            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 1 Wins \n\n"+"Do you want to Play again")
            build.setPositiveButton("ok"){ dialog,which ->
                reset()
                audio.release()
            }
            build.setNegativeButton("Exit"){dialog,which ->
                audio.release()
                exitProcess (1)
            }
            Handler().postDelayed(Runnable { build.show() },2000)
            return  1
        }
        else if ((player2.contains(1) && player2.contains(2) && player2.contains(3) ) ||
            (player2.contains(1) && player2.contains(4) && player2.contains(7) )||
            (player2.contains(3) && player2.contains(6) && player2.contains(9) )||
            (player2.contains(7) && player2.contains(8) && player2.contains(9) )||
            (player2.contains(4) && player2.contains(5) && player2.contains(6) )||
            (player2.contains(1) && player2.contains(5) && player2.contains(9) )||
            (player2.contains(3) && player2.contains(5) && player2.contains(7) )||
            (player2.contains(2) && player2.contains(5) && player2.contains(8)))


        {
            player2count+=1
            audio.start()
            buttonDisable()
            disableReset()
            Handler().postDelayed((Runnable { audio.release() }),4000)


            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Opponent have won the game \n\n"+"Do you want to Play again")
            build.setPositiveButton("ok"){
                    dialog,which ->
                reset()
                audio.release()
            }
            build.setNegativeButton("Exit"){dialog,which ->
                audio.release()
                exitProcess (1)
            }
            Handler().postDelayed(Runnable { build.show() },2000)
            return  1
        }  else if (emptyCells.contains(1) && emptyCells.contains(2) && emptyCells.contains(3)
            && emptyCells.contains(4)&& emptyCells.contains(5)&&
            emptyCells.contains(6)&& emptyCells.contains(7)&& emptyCells.contains(8)
            && emptyCells.contains(9)){

            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Game Draw \n\n"+"Do you want to Play again")
            build.setPositiveButton("ok"){  dialog,which ->
                reset()

            }
            build.setNegativeButton("Exit"){dialog,which ->
                exitProcess (1)
            }
            build.show()
            return  1
        }

        return 0

    }
    private  fun buttonDisable(){
        player1.clear()
        player2.clear()
        emptyCells.clear()
        activeUser = 1
        for( i in 1..9){
            var buttonSelected :Button?
            buttonSelected= when(i){
                1-> box1btn
                2-> box2btn
                3-> box3btn
                4-> box4btn
                5-> box5btn
                6-> box6btn
                7-> box7btn
                8-> box8btn
                9-> box9btn
                else ->{
                    box1btn
                }
            }
            //if (buttonSelected.isEnabled == true)
            // buttonSelected.isEnabled = false
            buttonSelected.isEnabled = true
            buttonSelected.text = ""
            player1TV.text = "Player 1 : $player1count"
            player2TV.text = "Player 2 : $player2count"
        }
    }




    private fun disableReset(){
        resetBtn.isEnabled = false
        Handler().postDelayed(Runnable { resetBtn.isEnabled = true },2200)

    }




}