package com.aneri;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private ImageButton incrementOne, incrementTwo;
    private TextView scoreTV1, scoreTV2;

    private int selectedScore = 1;

    private static final int MAX_SCORE = 10;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radio_group_score_options);
        incrementOne = findViewById(R.id.ib_increment_one);
        incrementTwo = findViewById(R.id.ib_increment_two);
        scoreTV1 = findViewById(R.id.tv_score_one);
        scoreTV2 = findViewById(R.id.tv_score_two);


        radioGroup.setOnCheckedChangeListener(scoreListener);


        incrementOne.setOnClickListener(scoreIncrementerOne);
        incrementTwo.setOnClickListener(scoreIncrementerTwo);


    }

    View.OnClickListener scoreIncrementerOne = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int currentScore = Integer.parseInt(scoreTV1.getText().toString());
            if(currentScore+selectedScore > MAX_SCORE){
                displayWinner("Team one Won!");
            }else{
                scoreTV1.setText(String.valueOf(currentScore + selectedScore));
                radioGroup.check(R.id.radio_button_one);
            }

        }
    };

    private void displayWinner(String message) {
        AlertDialog dialog = new AlertDialog
                .Builder(this)
                .setMessage(message)
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        radioGroup.check(R.id.radio_button_one);
                        selectedScore = 1;
                        scoreTV1.setText("0");
                        scoreTV2.setText("0");
                    }
                })
                .create();
        dialog.show();
    }

    View.OnClickListener scoreIncrementerTwo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int currentScore = Integer.parseInt(scoreTV2.getText().toString());
            if (currentScore + selectedScore > MAX_SCORE) {
                displayWinner("Team two Won!");
            } else {
                scoreTV2.setText(String.valueOf(currentScore + selectedScore));
                radioGroup.check(R.id.radio_button_one);
            }
        }
    };
    
    RadioGroup.OnCheckedChangeListener scoreListener = (radioGroup, checkedId) -> {
        switch (checkedId){
            case R.id.radio_button_one:
                selectedScore = 1;
                break;
            case R.id.radio_button_two:
                selectedScore = 2;
                break;
            case R.id.radio_button_three:
                selectedScore = 3;
                break;
        }
    };
}