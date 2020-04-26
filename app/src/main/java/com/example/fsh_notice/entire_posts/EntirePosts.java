package com.example.fsh_notice.entire_posts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fsh_notice.DepartmentPost;
import com.example.fsh_notice.EntirePostActivity;
import com.example.fsh_notice.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EntirePosts extends AppCompatActivity {
    private TextView title,body;
    private ImageView image1,image2,image3,image4,image5;
    private DatabaseReference databaseReference;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entire_posts);
        dialog=new Dialog(this);
        title=findViewById(R.id.ep_title);
        body=findViewById(R.id.ep_body);
        image1=findViewById(R.id.image_1);
        image2=findViewById(R.id.image_2);
        image3=findViewById(R.id.image_3);
        image4=findViewById(R.id.image_4);
        image5=findViewById(R.id.image_5);
        image1.setVisibility(View.GONE);
        image2.setVisibility(View.GONE);
        image3.setVisibility(View.GONE);
        image4.setVisibility(View.GONE);
        image5.setVisibility(View.GONE);
        Intent intent=getIntent();
        String uniquePostID=intent.getStringExtra("UniqueID");
        String pCategory=intent.getStringExtra("Category");
        switch (pCategory){
            case "CA":{
                databaseReference= FirebaseDatabase.getInstance().getReference("COMPUTER_APPLICATIONS_POSTS").child(uniquePostID);
                break;
            }
            case "CS":{
                databaseReference= FirebaseDatabase.getInstance().getReference("COMPUTER_SCIENCE_POSTS").child(uniquePostID);
                break;
            }
            case "LG":{
                databaseReference= FirebaseDatabase.getInstance().getReference("LANGUAGE_POSTS").child(uniquePostID);
                break;
            }
            case "BT":{
                databaseReference= FirebaseDatabase.getInstance().getReference("BIOTECH_POSTS").child(uniquePostID);
                break;
            }
            case "MA":{
                databaseReference= FirebaseDatabase.getInstance().getReference("MATHS_POSTS").child(uniquePostID);
                break;
            }
            case "VC":{
                databaseReference= FirebaseDatabase.getInstance().getReference("VISCOM_POSTS").child(uniquePostID);
                break;
            }
            case "CP":{
                databaseReference= FirebaseDatabase.getInstance().getReference("COMMON_POSTS").child(uniquePostID);
                break;
            }
        }
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DepartmentPost departmentPost=dataSnapshot.getValue(DepartmentPost.class);
                title.setText(departmentPost.getPostTitle());
                body.setText(departmentPost.getPostBody());
                final String url1=departmentPost.getImageUrl1();
                final String url2=departmentPost.getImageUrl2();
                final String url3=departmentPost.getImageUrl3();
                final String url4=departmentPost.getImageUrl4();
                final String url5=departmentPost.getImageUrl5();
                if(!url1.equals("")){
                    Glide.with(EntirePosts.this)
                            .asBitmap()
                            .load(url1)
                            .centerCrop()
                            .into(image1);
                    image1.setVisibility(View.VISIBLE);
                }
                if(!url2.equals("")){
                    Glide.with(EntirePosts.this)
                            .asBitmap()
                            .load(url2)
                            .centerCrop()
                            .into(image2);
                    image2.setVisibility(View.VISIBLE);
                }
                if(!url3.equals("")){
                    Glide.with(EntirePosts.this)
                            .asBitmap()
                            .load(url3)
                            .centerCrop()
                            .into(image3);
                    image3.setVisibility(View.VISIBLE);
                }
                if(!url4.equals("")){
                    Glide.with(EntirePosts.this)
                            .asBitmap()
                            .load(url4)
                            .centerCrop()
                            .into(image4);
                    image4.setVisibility(View.VISIBLE);
                }
                if(!url5.equals("")){
                    Glide.with(EntirePosts.this)
                            .asBitmap()
                            .load(url5)
                            .centerCrop()
                            .into(image5);
                    image5.setVisibility(View.VISIBLE);
                }
                image1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageView fImage1;
                        Button cButton;
                        dialog.setContentView(R.layout.full_screen_image);
                        fImage1=dialog.findViewById(R.id.image_fs);
                        cButton=dialog.findViewById(R.id.close_fs);
                        Glide.with(EntirePosts.this)
                                .asBitmap().fitCenter().load(url1).into(fImage1);
                        dialog.show();
                        cButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                });
                image2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageView fImage;
                        Button cButton;
                        dialog.setContentView(R.layout.full_screen_image);
                        fImage=dialog.findViewById(R.id.image_fs);
                        cButton=dialog.findViewById(R.id.close_fs);
                        Glide.with(EntirePosts.this).asBitmap().fitCenter().load(url2).into(fImage);
                        dialog.show();
                        cButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                });
                image3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageView fImage;
                        Button cButton;
                        dialog.setContentView(R.layout.full_screen_image);
                        fImage=dialog.findViewById(R.id.image_fs);
                        cButton=dialog.findViewById(R.id.close_fs);
                        Glide.with(EntirePosts.this).asBitmap().fitCenter().load(url3).into(fImage);
                        dialog.show();
                        cButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                });
                image4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageView fImage;
                        Button cButton;
                        dialog.setContentView(R.layout.full_screen_image);
                        fImage=dialog.findViewById(R.id.image_fs);
                        cButton=dialog.findViewById(R.id.close_fs);
                        Glide.with(EntirePosts.this).asBitmap().fitCenter().load(url4).into(fImage);
                        dialog.show();
                        cButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                });
                image5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageView fImage;
                        Button cButton;
                        dialog.setContentView(R.layout.full_screen_image);
                        fImage=dialog.findViewById(R.id.image_fs);
                        cButton=dialog.findViewById(R.id.close_fs);
                        Glide.with(EntirePosts.this).asBitmap().fitCenter().load(url5).into(fImage);
                        dialog.show();
                        cButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EntirePosts.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
