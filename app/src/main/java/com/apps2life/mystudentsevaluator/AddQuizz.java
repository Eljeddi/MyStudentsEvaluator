package com.apps2life.mystudentsevaluator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddQuizz extends AppCompatActivity {
    ArrayList<Question> QL=new ArrayList<>(100);
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void addMeth(View view){
        EditText question = (EditText)findViewById(R.id.qstid);
        EditText option1 = (EditText) findViewById(R.id.op1id);
        EditText option2 = (EditText) findViewById(R.id.op2id);
        EditText option3 = (EditText) findViewById(R.id.op3id);

        Question Qst = new Question();
        Qst.setQst(question.getText().toString().trim());
        Qst.setOp1(option1.getText().toString().trim());
        Qst.setOp2(option2.getText().toString().trim());
        Qst.setOp3(option3.getText().toString().trim());

        //for (int i = 0; i <= QL.size(); i++) {
        QL.add(Qst);
            /*if (Q[i] == null) {
                Q[i] = Qst;
                break;
            }*/
        //}
        question.setText("");
        option1.setText("");
        option2.setText("");
        option3.setText("");
        String Quizz;//="Quizz";
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Quizz");
        myRef.setValue(QL);
    }

}
