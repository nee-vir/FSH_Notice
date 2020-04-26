package com.example.fsh_notice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //DrawerLayout drawerLayout;
    //NavigationView navigationDrawer;
    private RecyclerView someRecyclerView;
    private SomeAdapter adapter;
    private DatabaseReference databaseReference;
    private List<Upload> someUploadList;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout_item){
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId()==R.id.computer_applications_item){
            Toast.makeText(this, "CA", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "HAHAHAHAHAHHAAHHA", Toast.LENGTH_LONG).show();
        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout=findViewById(R.id.home_drawer_layout);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView=findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(this);
        someRecyclerView=findViewById(R.id.home_recycler_view);
        someRecyclerView.setHasFixedSize(true);
        someRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        someUploadList=new ArrayList<>();
        databaseReference=FirebaseDatabase.getInstance().getReference("uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Upload upload=postSnapshot.getValue(Upload.class);
                    someUploadList.add(upload);
                }
                Collections.reverse(someUploadList);
                adapter=new SomeAdapter(someUploadList,Home.this);
                someRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Home.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }
}
