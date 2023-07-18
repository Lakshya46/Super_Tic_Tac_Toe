package com.example.supertictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MultiPlayer extends AppCompatActivity {


    private final List<int[]> combinationList = new ArrayList<>();
    private int[] boxpositions = {0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1;
    private int totalSelectedBoxes = 1 ;

    private LinearLayout playerOneLayout,playertwoLayout;
    private TextView playerOnename, playerTwoname;
    private ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
        playerOnename = findViewById(R.id.playeronename);
        playerTwoname = findViewById(R.id.playertwoname);
        playerOneLayout = findViewById(R.id.player1layout);
        playertwoLayout = findViewById(R.id.player2layout);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);


        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});
        final String getplayerOneName = getIntent().getStringExtra("playerOne");
        final String getplayerTwoName = getIntent().getStringExtra("playerTwo");
            playerOnename.setText(getplayerOneName);
            playerTwoname.setText(getplayerTwoName);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(0))
                {performAction( (ImageView)view, 0);
                }
            }});
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(1))
                {performAction( (ImageView)view, 1);
                }
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(2))
                {performAction( (ImageView)view, 2);
                }
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(3))
                {performAction( (ImageView)view, 3);
                }
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(4))
                {performAction( (ImageView)view, 4);
                }
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(5))
                {performAction( (ImageView)view, 5);
                }
            }
        });




        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(6))
                {performAction( (ImageView)view, 6);
                }
            }
        });



        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(7))
                {performAction( (ImageView)view, 7);
                }
            }
        });


        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(8)) {
                    performAction( (ImageView)view, 8);
                }
            }
        });


    }



    private void performAction(   ImageView imageView , int selectedBoxPosition) {
        boxpositions[selectedBoxPosition] = playerTurn;
        MediaPlayer audio = MediaPlayer.create(this,R.raw.drop);
        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.cross);
             audio.start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    audio.release();
                }
            },1500);

            if (checkPlayerWin()) {

                WinDialog winDialog = new WinDialog(MultiPlayer.this, playerOnename.getText().toString() + " has won the match", MultiPlayer.this);
                winDialog.setCancelable(false);

                winDialog.show();


            } else if (totalSelectedBoxes == 9) {
                WinDialog winDialog = new WinDialog(MultiPlayer.this, "It is a Draw", MultiPlayer.this);
                winDialog.setCancelable(false);
                winDialog.show();


            } else {

                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        }
        else{
            imageView.setImageResource(R.drawable.circlee);
            audio.start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    audio.release();
                }
            },1500);
            if (checkPlayerWin()){
                WinDialog winDialog = new WinDialog(MultiPlayer.this, playerTwoname.getText().toString() + " has won the match", MultiPlayer.this);
                winDialog.setCancelable(false);
                winDialog.show();



            } else if (selectedBoxPosition == 9) {
                WinDialog winDialog = new WinDialog(MultiPlayer.this, "It is a Draw", MultiPlayer.this);
                winDialog.setCancelable(false);
                winDialog.show();


            }
            else {
                changePlayerTurn(1);

                totalSelectedBoxes++ ;
            }

        }
    }

    private void changePlayerTurn( int currentPlayerTurn){
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1 )
        {
            playerOneLayout.setBackgroundResource(R.drawable.roundbackblueborder);
            playertwoLayout.setBackgroundResource(R.drawable.roundbackdarkblue);
        }
        else { playertwoLayout.setBackgroundResource(R.drawable.roundbackblueborder);
            playerOneLayout.setBackgroundResource(R.drawable.roundbackdarkblue);

        }
    }

    private boolean checkPlayerWin() {
        boolean response = false;
        for (int i = 0; i < combinationList.size(); i++) {

            final int[] combination = combinationList.get(i);
            if (boxpositions[combination[0]] == playerTurn && boxpositions[combination[1]] == playerTurn && boxpositions[combination[2]] == playerTurn) {
                response = true;

            }
        }
        return response;
    }

    private boolean isBoxSelectable( int boxposition){

        boolean response = false;
        if (boxpositions[boxposition]== 0){
            response = true;
        }
        return response ;
    }

    public void restartMatch(){


        boxpositions = new  int[]{0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedBoxes = 1;
        image1.setImageResource(R.drawable.roundbackdarkblue);
        image2.setImageResource(R.drawable.roundbackdarkblue);
        image3.setImageResource(R.drawable.roundbackdarkblue);
        image4.setImageResource(R.drawable.roundbackdarkblue);
        image5.setImageResource(R.drawable.roundbackdarkblue);
        image6.setImageResource(R.drawable.roundbackdarkblue);
        image7.setImageResource(R.drawable.roundbackdarkblue);
        image8.setImageResource(R.drawable.roundbackdarkblue);
        image9.setImageResource(R.drawable.roundbackdarkblue);





    }
}