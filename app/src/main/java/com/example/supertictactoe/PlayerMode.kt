package com.example.supertictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

var singUser = false
class PlayerMode : AppCompatActivity() {
    lateinit var singleplayerbtn: Button
    lateinit var multiplayerbtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_mode)

        singleplayerbtn = findViewById(R.id.splayer)
        multiplayerbtn = findViewById(R.id.mplayer)
        singleplayerbtn.setOnClickListener {
             Toast.makeText(this , "coming soon!", Toast.LENGTH_SHORT).show()
        }


        multiplayerbtn.setOnClickListener {

            startActivity(Intent(this,AddPlayer::class.java))
            singUser = false
        }




    }
}