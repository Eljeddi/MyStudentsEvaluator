package com.apps2life.mystudentsevaluator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
public int checkedItem;
    DialogInterface.OnClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isFirstTime())
            whoAreYouMsg();
        setContentView(R.layout.activity_main);

    }
    private void whoAreYouMsg(){
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        info.setTitle("who are you");
        //info.setMessage("Cooool boy");
        info.setPositiveButton("Confirm", null);
        info.setSingleChoiceItems(R.array.job,checkedItem,listener);
        info.show();
    }
    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
public void addQuizz(View view){
    Intent intent=new Intent(MainActivity.this,AddQuizz.class);
    startActivity(intent);
}


}
