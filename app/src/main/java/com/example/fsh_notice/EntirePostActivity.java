package com.example.fsh_notice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntirePostActivity extends AppCompatActivity {
    private TextView entireTitle,entireBody;
    private ImageView entireImage;
    private List<Upload> pUploadList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entire_post);
        entireTitle=findViewById(R.id.entire_title_s);
        entireBody=findViewById(R.id.entire_body_s);
        entireImage=findViewById(R.id.entire_image_s);
        pUploadList=new ArrayList<>();
        Intent intent=getIntent();
        final int pos=intent.getIntExtra("iPos",0);
        databaseReference=FirebaseDatabase.getInstance().getReference("uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Upload upload=postSnapshot.getValue(Upload.class);
                    pUploadList.add(upload);
                }
                Collections.reverse(pUploadList);
                String postImageUrl=pUploadList.get(pos).getPostImageUrl();
                entireTitle.setText(pUploadList.get(pos).getTitle());
                entireBody.setText(pUploadList.get(pos).getBody());
                if(!postImageUrl.equals("No Image")){
                    Glide.with(EntirePostActivity.this)
                            .asBitmap()
                            .load(postImageUrl)
                            .centerCrop()
                            .into(entireImage);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EntirePostActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        /*databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapShot:dataSnapshot.getChildren()){
                    Upload upload=postSnapShot.getValue(Upload.class);
                    pUploadList.add(upload);
                }
                Collections.reverse(pUploadList);
                String postImageUrl=pUploadList.get(pos).getPostImageUrl();
                entireTitle.setText(pUploadList.get(pos).getTitle());
                entireBody.setText(pUploadList.get(pos).getBody());
                /*if(!postImageUrl.equals("No image")){
                    Glide.with(EntirePostActivity.this)
                            .asBitmap()
                            .load(postImageUrl)
                            .centerCrop()
                            .into(entireImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EntirePostActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });*/

    }
}
