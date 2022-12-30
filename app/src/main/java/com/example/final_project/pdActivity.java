package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class pdActivity extends AppCompatActivity {
    private TextView textview3,textview4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd);
        Intent intent = this.getIntent();
        int[] champion=intent.getIntArrayExtra("Winners");
        textview3 = findViewById(R.id.textView3);
        textview3.setText(String.valueOf(champion[0]));
        ImageView wine = findViewById(R.id.winnerhorse);
        String img = "img_"+String.valueOf(champion[0]);
        int id =getResources().getIdentifier(img,"drawable",getPackageName());
        wine.setImageResource(id);

        ImageView wine2 = findViewById(R.id.winnerhorse2);
        String img2 = "img_"+String.valueOf(champion[1]);
        int id2 =getResources().getIdentifier(img2,"drawable",getPackageName());
        wine2.setImageResource(id2);

        ImageView wine3 = findViewById(R.id.winnerhorse3);
        String img3 = "img_"+String.valueOf(champion[2]);
        int id3 =getResources().getIdentifier(img3,"drawable",getPackageName());
        wine3.setImageResource(id3);

        textview4 = findViewById(R.id.textView4);
        Button btn1 =findViewById(R.id.btn_return);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(),betActivity.class);
                startActivity(intent1);
            }
        });

    }
}