package com.example.fsh_notice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fsh_notice.notice_boards.BioTech;
import com.example.fsh_notice.notice_boards.ComputerApplications;
import com.example.fsh_notice.notice_boards.ComputerScience;
import com.example.fsh_notice.notice_boards.Language;
import com.example.fsh_notice.notice_boards.Mathematics;
import com.example.fsh_notice.notice_boards.VisCom;

public class CategorySelectionActivity extends AppCompatActivity {
    Button caButton,csButton,viscomButton,mathsButton,langButton,biotechButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);
        caButton=findViewById(R.id.ca_button);
        csButton=findViewById(R.id.cs_button);
        langButton=findViewById(R.id.lauguage_button);
        viscomButton=findViewById(R.id.vis_com_button);
        mathsButton=findViewById(R.id.maths_button);
        biotechButton=findViewById(R.id.bio_tech_button);
        caButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ComputerApplications.class));
            }
        });
        csButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ComputerScience.class));
            }
        });
        langButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Language.class));
            }
        });
        viscomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), VisCom.class));
            }
        });
        mathsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Mathematics.class));
            }
        });
        biotechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BioTech.class));
            }
        });

    }
}
