package com.example.android.speedquiz;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView result;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView question;
    TextView timer;
    Button playAgain;
    RelativeLayout gameLayout;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timer.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        playAgain.setVisibility(View.INVISIBLE);
        result.setText("");

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                result.setText("Done!");
                playAgain.setVisibility(View.VISIBLE);

            }
        }.start();

    }

    public void chooseAnswer(View view) {
       if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
           result.setText("Correct!");
           score++;

       } else {
           result.setText("Wrong!");
       }
       numberOfQuestions++;
       scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
       newQuestion();

    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timer));
    }

    public void newQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        question.setText(Integer.toString(a) + " + " + Integer.toString(b));

        answers.clear();

        for (int i=0; i<4; i++) {
            if (i== locationOfCorrectAnswer) {
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.question);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        result = findViewById(R.id.result);
        scoreTextView = findViewById(R.id.scoreTextView);
        timer = findViewById(R.id.timer);
        playAgain = findViewById(R.id.playAgain);
        gameLayout = findViewById(R.id.gameLayout);

        startButton = findViewById(R.id.startButton);

        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);



    }
}
