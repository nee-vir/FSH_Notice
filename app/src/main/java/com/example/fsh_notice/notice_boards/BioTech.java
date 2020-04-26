package com.example.fsh_notice.notice_boards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.fsh_notice.CAAdapter;
import com.example.fsh_notice.DepartmentPost;
import com.example.fsh_notice.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BioTech extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private List<DepartmentPost> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_tech);
        itemList=new ArrayList<>();
        recyclerView=findViewById(R.id.bt_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference= FirebaseDatabase.getInstance().getReference("BIOTECH_POSTS");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();//Removes Repeating items or clears the array to avoid duplication
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    DepartmentPost departmentPost=snapshot.getValue(DepartmentPost.class);
                    itemList.add(departmentPost);
                }
                Collections.reverse(itemList);
                CAAdapter adapter;
                adapter=new CAAdapter(BioTech.this,itemList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BioTech.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
