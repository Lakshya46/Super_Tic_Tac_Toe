package com.example.supertictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinDialog extends Dialog {

    private final String message ;
    private  final MultiPlayer mainActivity;


    public WinDialog(@NonNull Context context , String message , MultiPlayer mainActivity) {
        super(context);
        this.message = message ;
        this.mainActivity  = mainActivity ;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_win_dialog);

        final TextView messagetxt = findViewById(R.id.messageTxt);
        final Button startAgainbtn = findViewById(R.id.startagainbtn);

        MediaPlayer audio1 = MediaPlayer.create(getContext(), R.raw.mario);
        audio1.start();

        messagetxt.setText(message);



        startAgainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.restartMatch();
                 audio1.release();
                dismiss();

            }
        });


    }
}
