package com.apps2life.mystudentsevaluator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddQuizz extends AppCompatActivity {

    String quizName;
    StringBuilder builder=new StringBuilder();
    String delimiter="|";
    EditText question ;
    EditText option1 ;
    EditText option2 ;
    EditText option3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quizz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show()

                // Write a message to the database
                quizName=((EditText)findViewById(R.id.QuizName)).getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                DatabaseReference myRefChild=myRef.child(quizName);
                myRefChild.setValue(builder.toString());
                //myRef.child(quizName).push().setValue(builder.toString());
            }
        });
    }
    public void addMeth(View view){

         question = (EditText)findViewById(R.id.qstid);
         option1 = (EditText) findViewById(R.id.op1id);
         option2 = (EditText) findViewById(R.id.op2id);
         option3 = (EditText) findViewById(R.id.op3id);


        builder.append(question.getText().toString().trim());
        builder.append(delimiter);
        builder.append(option1.getText().toString().trim());
        builder.append(delimiter);
        builder.append(option2.getText().toString().trim());
        builder.append(delimiter);
        builder.append(option3.getText().toString().trim());
        builder.append(delimiter);
        question.setText("");
        option1.setText("");
        option2.setText("");
        option3.setText("");

    }


}
