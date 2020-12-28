package com.example.solaris;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class main_menu extends AppCompatActivity {
    //buat firebase list
    RecyclerView rv;
    List<ArticleList> articleLists;
    DatabaseReference databaseReference;


    //ini isi biasa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_ly);

    //INI BUAT DATABASE
        rv=findViewById(R.id.resikel);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decoration=new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(decoration);
        articleLists=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("gambarTempat");
        getImageData();

    //INI BUAT TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

            //INI BUAT PILIHAN
        ImageView mep = findViewById(R.id.near);
        mep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent deket = new Intent(getApplicationContext(), Lokasi_terdekat.class);
                startActivity(deket);
            }
        });
    }
    public void populer(View v)
    {
        Intent pop = new Intent(main_menu.this , populer.class);
        startActivity(pop);
    }

    public void jalan(View v)
    {
        Intent go = new Intent(main_menu.this , Jalan.class);
        startActivity(go);
    }

    public void settingan()
    {
        Intent setingan = new Intent(main_menu.this , Setting_menu.class);
        startActivity(setingan);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        switch (item.getItemId()) {
            case R.id.action_settings:
                settingan();
                return true;
            case R.id.action_profile:
                Toast.makeText(context, "chonky", duration).show();
                return true;
            case R.id.action_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),Splash.class));
        finish();
    }

    //Buat bkin list menu dari firebase
    private void getImageData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot di:dataSnapshot.getChildren()){
                    ArticleList articleList=di.getValue(ArticleList.class);
                    articleLists.add(articleList);
                }
                ArticleAdapter adapter=new ArticleAdapter(articleLists,getApplicationContext());
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
