package com.apps2life.mystudentsevaluator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
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
    String uid;
    TextView tv;
    int qstNum=0;
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
                Snackbar.make(view, "Votre quiz a été ajouté avec succès", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // Write a message to the database
                //String email=myRef.getAuth().password.email;
                uid = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                int p=uid.indexOf("@");
                uid=uid.substring(0,p);//getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(uid);
                quizName=((EditText)findViewById(R.id.QuizName)).getText().toString();

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


        //toggleButton
        ToggleButton toggle1 = (ToggleButton) findViewById(R.id.tgq1);
        ToggleButton toggle2 = (ToggleButton) findViewById(R.id.tgq2);
        ToggleButton toggle3 = (ToggleButton) findViewById(R.id.tgq3);

        qstNum++;
        tv=(TextView)findViewById(R.id.tv);
        tv.setText("q"+qstNum);


        builder.append(question.getText().toString().trim());
        builder.append(delimiter);
        if(toggle1.isChecked())
        builder.append(option1.getText().toString().trim()+"$");
        else
            builder.append(option1.getText().toString().trim());
        builder.append(delimiter);
        if(toggle2.isChecked())
        builder.append(option2.getText().toString().trim()+"$");
        else
            builder.append(option2.getText().toString().trim());
        builder.append(delimiter);
        if (toggle3.isChecked())
        builder.append(option3.getText().toString().trim()+"$");
        else
            builder.append(option3.getText().toString().trim());
        builder.append(delimiter);
        question.setText("");
        option1.setText("");
        option2.setText("");
        option3.setText("");

    }


}
