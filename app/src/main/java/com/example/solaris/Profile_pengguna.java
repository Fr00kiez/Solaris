package com.example.solaris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Profile_pengguna extends Activity {
    Button keluar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_ly);

        keluar2 = findViewById(R.id.keluar2);

        keluar2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                keeluar();
            }
        });
    }

    //tombol keluar
    public void keeluar() {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),Splash.class));
        finish();
    }


}
