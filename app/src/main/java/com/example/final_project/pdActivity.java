package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class pdActivity extends AppCompatActivity {
    private TextView tv_BetHorse,tv_Deposit;
    private TextView Champion,Second,Third;
    private  int Deposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd);
        Champion=findViewById(R.id.champion);
        Second=findViewById(R.id.second);
        Third=findViewById(R.id.third);
        Intent intent = this.getIntent();
        int[] champion=intent.getIntArrayExtra("Winners");
        Deposit = intent.getIntExtra("Deposit",0);
        int myBetHorse=intent.getIntExtra("myBetHorse",0);
        int myBetValue=intent.getIntExtra("myBetValue",0);
        int myBetOdd=intent.getIntExtra("myBetOdd",0);
        tv_BetHorse=findViewById(R.id.tv_BetHorse);
        tv_Deposit=findViewById(R.id.tv_Deposit);
        Champion.setText(champion[0]+"號馬");
        Second.setText(champion[1]+"號馬");
        Third.setText(champion[2]+"號馬");

        if(champion[0]==myBetHorse){
            Deposit=(Deposit-myBetValue)+myBetValue*myBetOdd;
            tv_BetHorse.setText("您下注的"+String.valueOf(myBetHorse)+"號馬勝利!!!!");
        }else{
            Deposit=Deposit-myBetValue;
            tv_BetHorse.setText("您下注的"+String.valueOf(myBetHorse)+"號馬未勝利");
        }

        tv_Deposit.setText(String.valueOf(Deposit)+"元");

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


        Button btn1 =findViewById(R.id.btn_return);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(),betActivity.class);
                intent1.putExtra("Deposit",Deposit);
                startActivity(intent1);
            }
        });

    }
}