package com.example.supertictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        final EditText playerone = findViewById(R.id.Player1name);
        final EditText playertwo = findViewById(R.id.Player2name);
        final Button startgamebtn = findViewById(R.id.startGamebtn);

        startgamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getPlayerOneName = playerone.getText().toString();
                final String getPlayerTwoName = playertwo.getText().toString();

                if(getPlayerOneName.isEmpty()|| getPlayerTwoName.isEmpty()){
                    Toast.makeText(AddPlayer.this, "Please enter player names", Toast.LENGTH_SHORT).show();
                }

                else {
                    Intent intent = new Intent(AddPlayer.this,MultiPlayer.class);
                    intent.putExtra("playerOne",getPlayerOneName);
                    intent.putExtra("playerTwo",getPlayerTwoName);
                    startActivity(intent);

                }
            }});


    }
}