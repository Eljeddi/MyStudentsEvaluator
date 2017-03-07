package com.apps2life.mystudentsevaluator;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class TeacherOrStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_or_student);

    }
    public void onRadioButtonClicked(View view){
        boolean checked=((RadioButton)view).isChecked();

        switch (view.getId()){
            case R.id.teacherChoice:
                if(checked){
                    PreferenceManager.getDefaultSharedPreferences(TeacherOrStudent.this).edit().putString("job","Teacher").commit();
                    startActivity(new Intent(TeacherOrStudent.this,MainActivity.class));
                }
                break;
            case R.id.studentChoice:
                if(checked){
                    PreferenceManager.getDefaultSharedPreferences(TeacherOrStudent.this).edit().putString("job","Student").commit();
                    startActivity(new Intent(TeacherOrStudent.this,StudentMainActivity.class));
                }
                break;
        }
    }
    }

