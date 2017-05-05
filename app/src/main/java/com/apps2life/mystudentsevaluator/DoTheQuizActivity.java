 package com.apps2life.mystudentsevaluator;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoTheQuizActivity extends AppCompatActivity {

    String qn,op1,op2,op3;
    String ti;
    //TextView tv;
    LinearLayout linearLayout;
    //RadioGroup radioGroup;
    String[] qstAndOptions;
    CheckBox[]checkBoxes;
    int score,total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_the_quiz);
        //connect to the firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        //get the teacher name and the quiz name from the intent
        //tv=(TextView)findViewById(R.id.tv2);
        final Intent intent = getIntent();
        qn = intent.getStringExtra("QUIZ_NAME");
        ti=intent.getStringExtra("TEACHER_ID");
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);



        myRef.child(ti).child(qn).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                //tv.setText(qn+"  "+ti+value);
                qstAndOptions=value.split("\\|");
                checkBoxes=new CheckBox[qstAndOptions.length];
                //for (int j=0;j<qstAndOptions.length;j++)

                //prepare the linearLayout and get the radioGroup so you could put component into it later
                //ScrollView sv = new ScrollView(DoTheQuizActivity.this);
                //linearLayout=new LinearLayout(DoTheQuizActivity.this);
                //linearLayout.setOrientation(LinearLayout.VERTICAL);
                //sv.addView(linearLayout);
                //DoTheQuizActivity.this.setContentView(linearLayout);
                int i=0;
                //radioGroup=new RadioGroup(DoTheQuizActivity.this);
                do{

                    final TextView textView=new TextView(DoTheQuizActivity.this);
                    textView.setText(qstAndOptions[i]);
                    textView.setTextSize(25);
                    linearLayout.addView(textView);

                    //final RadioButton radioButton1=new RadioButton(DoTheQuizActivity.this);
                    //radioButton1.setText(qstAndOptions[i+1]);
                    //radioGroup.addView(radioButton1);
                    checkBoxes[i]=new CheckBox(DoTheQuizActivity.this);
                    checkBoxes[i].setId(i+1);
                    op1=qstAndOptions[i+1];
                    if((op1).contains("$"))
                        checkBoxes[i].setText(op1.substring(0,op1.length()-1));
                    else
                    checkBoxes[i].setText(qstAndOptions[i+1]);
                    linearLayout.addView(checkBoxes[i]);



                    //final RadioButton radioButton2=new RadioButton(DoTheQuizActivity.this);
                    //radioButton2.setText(qstAndOptions[i+2]);
                    //radioGroup.addView(radioButton2);
                    checkBoxes[i+1]=new CheckBox(DoTheQuizActivity.this);
                    checkBoxes[i+1].setId(i+2);
                    op2=qstAndOptions[i+2];
                    if(op2.contains("$"))
                        checkBoxes[i+1].setText(op2.substring(0,op2.length()-1));
                    else
                    checkBoxes[i+1].setText(op2);
                    linearLayout.addView(checkBoxes[i+1]);

                    //final RadioButton radioButton3=new RadioButton(DoTheQuizActivity.this);
                    //radioButton3.setText(qstAndOptions[i+3]);
                    //radioGroup.addView(radioButton3);
                    checkBoxes[i+2]=new CheckBox(DoTheQuizActivity.this);
                    checkBoxes[i+2].setId(i+3);
                    op3=qstAndOptions[i+3];
                    if (op3.contains("$"))
                        checkBoxes[i+2].setText(op3.substring(0,op3.length()-1));
                    else
                        checkBoxes[i+2].setText(op3);
                    linearLayout.addView(checkBoxes[i+2]);

                    //linearLayout.addView(radioGroup);

                    i+=4;


                }while (i<=qstAndOptions.length-3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    public void evaluer(View view){
    total=qstAndOptions.length-((qstAndOptions.length)/4);
        score=total;
        for (int i=1;i<qstAndOptions.length;i++){
            if(checkBoxes[i-1]!=null){
            if((qstAndOptions[i].contains("$")&&!(checkBoxes[i-1].isChecked()))||(!(qstAndOptions[i].contains("$"))&&checkBoxes[i-1].isChecked()))
            {score--;
                checkBoxes[i-1].setTextColor(getResources().getColor(R.color.colorAccent));

            }}

    }
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        info.setTitle("Résultat");
        info.setMessage(score+"/"+total);
        info.setPositiveButton(android.R.string.ok, null);
        info.show();

    }

}
