package com.example.zhouxuexuan.fb;

import android.content.Intent;
import android.renderscript.Script;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import static com.example.zhouxuexuan.fb.Main2Activity.win;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        final Button btnClick1 = (Button) findViewById(R.id.button);
        final Button btnClick2 = (Button) findViewById(R.id.button2);
        final Button btnClick3 = (Button) findViewById(R.id.button3);
        Firebase ref = new Firebase("https://wk5votebase.firebaseio.com/");
        Firebase execref = ref.child("Execboolean");
        Firebase winref = ref.child("winner");
        try{
            winref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getChildrenCount()>0){
                        Firebase ref = new Firebase("https://wk5votebase.firebaseio.com/");
                        ref.child("Execboolean").setValue(0);
                    }
                    else {
                        Firebase ref = new Firebase("https://wk5votebase.firebaseio.com/");
                        ref.child("Execboolean").setValue(1);
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }catch (Exception e){
        }
        execref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int check = dataSnapshot.getValue(int.class);
                if (check==1){
                    btnClick1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Firebase ref = new Firebase("https://wk5votebase.firebaseio.com/");
                            ref.child("A").push().setValue("voted");
                            Intent intent1 = new Intent(getApplicationContext(), Main2Activity.class);
                            startActivity(intent1);
                        }
                    });
                    btnClick2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Firebase ref = new Firebase("https://wk5votebase.firebaseio.com/");
                            ref.child("B").push().setValue("voted");
                            Intent intent1 = new Intent(getApplicationContext(), Main2Activity.class);
                            startActivity(intent1);
                        }
                    });
                }
                if (check==0){
                    Toast.makeText(MainActivity.this,"Winner appeared already!",Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        btnClick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent1);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }

}
