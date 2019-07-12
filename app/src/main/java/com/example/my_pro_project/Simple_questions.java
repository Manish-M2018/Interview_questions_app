package com.example.my_pro_project;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;



public class Simple_questions extends MainActivity {

    String[] simple_questions, simple_answers;
    TextView tvquestions, tvanswers, tvtotallength_yy, tvpresentindex_xx;
    Button bleft, answer, bright;
    int index = 0;
    private static final String default_answer = "Press A button for answer";

    //Variables and objects of TTS
    TextToSpeech ttsobject;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        if(savedInstanceState!= null)
        {
            index = savedInstanceState.getInt("index");
        }

        //Adding the title bar
        LinearLayout questions_ll = (LinearLayout) findViewById(R.id.questions_title_bar_page);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.questions_title_bar);

        Button bspeak = (Button) findViewById(R.id.text_to_speech_on) ;
        Button bstop_mute = (Button) findViewById(R.id.text_to_speech_off);

        TextView tv_category = (TextView) findViewById(R.id.tvquestions_title);
        tv_category.setText("Simple Questions");




        // Initialisation of TextViews
        tvquestions = (TextView) findViewById(R.id.tvQuestion);
        tvanswers = (TextView) findViewById(R.id.tvAnswer);
        tvpresentindex_xx = (TextView) findViewById(R.id.tvxx);
        tvtotallength_yy = (TextView) findViewById(R.id.tvyy);


        // Initialisation of Buttons
        bleft =(Button) findViewById(R.id.bleft);
        answer = (Button) findViewById(R.id.showanswer);
        bright = (Button) findViewById(R.id.bright);

        // Importing string arrays from values folder
        simple_questions= getResources().getStringArray(R.array.simple_ques);
        simple_answers = getResources().getStringArray(R.array.simple_ans);

        //Setting values for our 4 text views and variable
        tvquestions.setText(simple_questions[index]);
        tvanswers.setText(default_answer);
        tvpresentindex_xx.setText(String.valueOf(index + 1));
        tvtotallength_yy.setText(String.valueOf("/" + simple_questions.length));


        // Onclicklistener for the 3 buttons
        bleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvanswers.setText(default_answer);
                index--;
                if(index == -1)
                {
                    index = simple_questions.length - 1;
                }
                tvquestions.setText(simple_questions[index]);
                tvpresentindex_xx.setText(String.valueOf(index + 1));

            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    tvanswers.setText(simple_answers[index]);

            }
        });

        bright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvanswers.setText(default_answer);
                index++;
                if(index == simple_questions.length)
                {
                    index = 0;
                }

                tvquestions.setText(simple_questions[index]);
                tvpresentindex_xx.setText(String.valueOf(index + 1));

            }
        });


        //TTS object and listener initialisation
        ttsobject = new TextToSpeech(Simple_questions.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    result = ttsobject.setLanguage(Locale.US);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Feature not supported on your device", Toast.LENGTH_SHORT).show();
                }

            }
        });


        bspeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA)
                {
                    Toast.makeText(getApplicationContext(), "Feature not supported on your device", Toast.LENGTH_SHORT);
                }
                else {
                    if (tvanswers.getText().toString().equals(default_answer)) {

                    } else {
                        ttsobject.speak(simple_answers[index], TextToSpeech.QUEUE_FLUSH, null);

                    }
                }
            }
        });

        bstop_mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ttsobject != null)
                {
                    ttsobject.stop();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("index", index);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(ttsobject != null)
        {
            ttsobject.stop();
            ttsobject.shutdown();
        }
    }
}
