package com.example.zhouxuexuan.fb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
    private TextView textView2;
    private TextView textView3;
    public static String win ="nowinner";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        Firebase ref = new Firebase("https://wk5votebase.firebaseio.com/");
        Firebase aref = ref.child("A");
        Firebase bref = ref.child("B");
        aref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount()==3&&(!(win.equals("B")))){
                    Firebase ref = new Firebase("https://wk5votebase.firebaseio.com/");
                    textView2.setText("A: win");
                    win = "A";
                    ref.child("winner").push().setValue(win);
                }else {
                    textView2.setText("A: "+dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        bref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount()==3&&(!(win.equals("A")))){
                    Firebase ref = new Firebase("https://wk5votebase.firebaseio.com/");
                    textView3.setText("B: win");
                    win = "B";
                    ref.child("winner").push().setValue(win);
                }else {
                    textView3.setText("B: "+dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
    @Override
    public void onBackPressed() {
    }
}
