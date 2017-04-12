package com.apps2life.mystudentsevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoTheQuizActivity extends AppCompatActivity {

    String qn;
    String ti;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_the_quiz);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        tv=(TextView)findViewById(R.id.tv2);
        Intent intent = getIntent();
        qn = intent.getStringExtra("QUIZ_NAME");
        ti=intent.getStringExtra("TEACHER_ID");
        String v;
        myRef.child(ti).child(qn).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv.setText(qn+"  "+ti+value);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
