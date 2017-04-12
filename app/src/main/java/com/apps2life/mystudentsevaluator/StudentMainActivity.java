package com.apps2life.mystudentsevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.apps2life.mystudentsevaluator.R.id.lv;
import static com.apps2life.mystudentsevaluator.R.id.start;

public class StudentMainActivity extends AppCompatActivity {
    private ArrayList<String> Alist=new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
    }
    public void showQuiz(View view){
        final String teacherId=((EditText)findViewById(R.id.editText)).getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(teacherId);
        listView=(ListView)findViewById(R.id.quiz_list);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Alist);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TextView textView = (TextView) view.findViewById(R.id.quiz_list);
                //String text = textView.getText().toString();
                final String text = ((TextView)view).getText().toString();
                Log.d("StudentMainActivity",text);
                Intent intent=new Intent(StudentMainActivity.this,DoTheQuizActivity.class);
                intent.putExtra("QUIZ_NAME",text);
                intent.putExtra("TEACHER_ID",teacherId);
                startActivity(intent);
            }
        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //String quizName;
                String value=dataSnapshot.getKey();

                //if(value.equals(uid))
                //quizName=dataSnapshot.child(value).getKey();  //child(value).getKey();
                //else quizName="you have no quiz";
                Alist.add(value);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
