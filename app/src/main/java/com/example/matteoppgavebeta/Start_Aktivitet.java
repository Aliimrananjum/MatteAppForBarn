package com.example.matteoppgavebeta;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Start_Aktivitet extends AppCompatActivity {

    TextView input_svar;
    TextView oppgave;

    int currentQuestionIndex = 0;
    String[] shuffledProblems;
    String[] shuffledSolutions;
    int questionAmountInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_aktivitet);



        //lager alle tall knappene
        ImageButton knapp_1 = (ImageButton) findViewById(R.id.input1);
        knapp_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("1");
            }
        });
        ImageButton knapp_2 = (ImageButton) findViewById(R.id.input2);
        knapp_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("2");
            }
        });
        ImageButton knapp_3 = (ImageButton) findViewById(R.id.input3);
        knapp_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("3");
            }
        });
        ImageButton knapp_4 = (ImageButton) findViewById(R.id.input4);
        knapp_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("4");
            }
        });
        ImageButton knapp_5 = (ImageButton) findViewById(R.id.input5);
        knapp_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("5");
            }
        });
        ImageButton knapp_6 = (ImageButton) findViewById(R.id.input6);
        knapp_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("6");
            }
        });
        ImageButton knapp_7 = (ImageButton) findViewById(R.id.input7);
        knapp_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("7");
            }
        });
        ImageButton knapp_8 = (ImageButton) findViewById(R.id.input8);
        knapp_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("8");
            }
        });
        ImageButton knapp_9 = (ImageButton) findViewById(R.id.input9);
        knapp_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("9");
            }
        });
        ImageButton knapp_0 = (ImageButton) findViewById(R.id.input0);
        knapp_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("0");
            }
        });

        Button knapp_send = (Button) findViewById(R.id.inputOK);

        knapp_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });



        Button knapp_slett = (Button) findViewById(R.id.delete);
        knapp_slett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDeleteButtonClick();
            }
        });

        //Kode for å legge data på textView
        input_svar = (TextView) findViewById(R.id.input_svar);
        oppgave = (TextView) findViewById(R.id.oppgave);

        //logikken for å legge regnestykket på skjermen

        //henter array data fra /res/values/arrays
        String[] mathProblems = getResources().getStringArray(R.array.mathProblems);
        String[] mathSolutions = getResources().getStringArray(R.array.mathSolutions);

        //først henter data fra SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String questionAmount = sharedPreferences.getString("question_count", "10");
        Log.d("MyApp", "Hentet question amount: " + questionAmount);
        questionAmountInt = Integer.parseInt(questionAmount);

        //lager en ny array med index
        List<Integer> indexes = new ArrayList<>();
        for (int i=0; i < questionAmountInt; i++){
            indexes.add(i);
        }

        //stokker om indexene
        Collections.shuffle(indexes);

        //lager to nye Arrays
        shuffledProblems = new String[questionAmountInt];
        shuffledSolutions = new String[questionAmountInt];
        Log.d("Debug", "Initialized Question Amount: " + questionAmountInt);

        //legger tilfeldige oppgaver og svar i nye array
        for (int i = 0; i < indexes.size(); i++) {
            shuffledProblems[i] = mathProblems[indexes.get(i)];
            shuffledSolutions[i] = mathSolutions[indexes.get(i)];
        }
        Log.d("MyApp", "Question Amount Int initialized: " + questionAmountInt);


        //oppgave.setText(String.valueOf(mathProblems[3]));

        setNextQuestion();
        Log.d("MyApp", "Question Amount Int initialized: " + questionAmountInt);

    }
    //logikken for å legge til verdiene fra knappene på skjermen
    private void handleButtonClick(String value) {
        Log.d("Button Clicked", "Value: " + value);
        String tidligereTekst = input_svar.getText().toString();
        input_svar.setText(String.valueOf(tidligereTekst+value));
    }
    private void handleDeleteButtonClick() {
        String existingText = input_svar.getText().toString();
        if (!existingText.isEmpty()) {
            input_svar.setText(existingText.substring(0, existingText.length() - 1));
        }
    };

    @Override
    public void onBackPressed() {
        Intent avslutt = new Intent(this, MainActivity.class);
        new AlertDialog.Builder(this)
                .setTitle(R.string.bekrefte)
                .setPositiveButton(R.string.avslutt, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(avslutt);

                    }
                })
                .setNegativeButton(R.string.nei, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    private void checkAnswer() {
        String userAnswer = input_svar.getText().toString();
        String correctAnswer = shuffledSolutions[currentQuestionIndex];

        if (userAnswer.equals(correctAnswer)) {
            TextView correctMessage = findViewById(R.id.correct_message);
            Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.face_in);
            Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.face_out);
            correctMessage.startAnimation(fadeIn);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    correctMessage.startAnimation(fadeOut);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    correctMessage.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


            currentQuestionIndex++;
            setNextQuestion();
        } else {
            // svaret var feil, vis en melding til brukeren
            //Toast.makeText(this, "Feil svar, prøv igjen.", Toast.LENGTH_SHORT).show();
            TextView errorMessage = findViewById(R.id.error_message);

            Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.face_in);
            Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.face_out);

            errorMessage.startAnimation(fadeIn);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {
                    errorMessage.startAnimation(fadeOut);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });

            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {
                    errorMessage.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
        }
    }

    private void setNextQuestion() {
        //lager en intent
        Intent ferdig = new Intent(this, Ferdig.class);
        Log.d("Debug", "Current Question Index: " + currentQuestionIndex);
        Log.d("Debug", "Question Amount: " + questionAmountInt);

        if (shuffledProblems != null) {
            Log.d("Debug", "Shuffled Problems Length: " + shuffledProblems.length);
        } else {
            Log.d("Debug", "Shuffled Problems is null");
        }

        if (currentQuestionIndex < questionAmountInt) {
            if (shuffledProblems != null && shuffledProblems[currentQuestionIndex] != null) {
                oppgave.setText(shuffledProblems[currentQuestionIndex]);
            } else {
                oppgave.setText("Error fetching problem");
                Log.e("Error", "Error fetching problem at index: " + currentQuestionIndex);
            }
            input_svar.setText("");
        } else {
            // Alle spørsmål er besvart, du kan vise en melding her eller navigere til en annen aktivitet
            Toast.makeText(this, "Du har fullført alle spørsmålene!", Toast.LENGTH_LONG).show();
            startActivity(ferdig);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);

        TextView textView = (TextView) findViewById(R.id.input_svar);
        outstate.putString("svar", textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        TextView tw = (TextView) findViewById(R.id.input_svar);
        tw.setText(savedInstanceState.getString("svar"));
    }



}

