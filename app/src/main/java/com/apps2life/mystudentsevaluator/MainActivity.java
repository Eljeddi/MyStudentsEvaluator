package com.apps2life.mystudentsevaluator;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String rslt;
    private static final String TAG = "MainActivity";
    public int checkedItem;
    DialogInterface.OnClickListener listener;
    AddQuizz AQ=new AddQuizz();
    private ArrayList<String> Alist=new ArrayList<>();
    private ListView listView;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getEmail();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isFirstTime())
            whoAreYouMsg();
        setContentView(R.layout.activity_main);
        int p=uid.indexOf("@");
        uid=uid.substring(0,p);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(uid);
        listView=(ListView)findViewById(R.id.lv);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Alist);
        listView.setAdapter(arrayAdapter);
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

    private String whoAreYouMsg(){
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        info.setTitle("Vous ete un");
        //info.setMessage("Cooool boy");
        info.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ListView lw = ((AlertDialog)dialog).getListView();
                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                Log.d(TAG, checkedItem.toString());
                rslt=checkedItem.toString();
                SharedPreferences pref=getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("job",rslt);
                editor.commit();
            }
        });
        //info.setPositiveButton(R.string.confirm, null);
        info.setSingleChoiceItems(R.array.job,checkedItem,listener);
        info.show();
        return rslt;
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

    @Override
    protected void onStart() {
        super.onStart();

    }
}
