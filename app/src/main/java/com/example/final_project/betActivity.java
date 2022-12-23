package com.example.final_project;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class betActivity extends AppCompatActivity {
    private Button btn_bet;
    private ListView horseList;
    private Spinner horseChoose;
    private ArrayList<String> horse=new ArrayList<>();
    private TextView tv_deposit;
    private EditText ed_betPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.betpage);
        btn_bet=findViewById(R.id.btn_bet);
        horseList=findViewById(R.id.horseList);
        horseChoose=findViewById(R.id.horseChoose);
        tv_deposit=findViewById(R.id.tv_deposit);
        ed_betPrice=findViewById(R.id.ed_betPrice);
        ArrayAdapter<String> horseAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,horse);
        String[] horseSpinnerArray=new String[]{"一號馬","二號馬","三號馬","四號馬","五號馬","六號馬"};
        ArrayAdapter<String> horseSpinnerAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,horseSpinnerArray);
        horseChoose.setAdapter(horseSpinnerAdapter);
        int newDeposit=50000;
        tv_deposit.setText("目前金額:"+newDeposit+"元");
        horseList.setAdapter(horseAdapter);

        boolean flag=false;
        horseInfo[] horses;
        horses=new horseInfo[6];
        for(int i=0;i<6;i++){
            horses[i]=new horseInfo();
        }
        while(!flag){
            int cnt=0;
            for(int i=0;i<6;i++){
                horses[i].betOdd=(int)Math.round(Math.random()*15)+1;
            }
            for(int i=0;i<6;i++){
                if(horses[i].betOdd<5){
                    cnt++;
                }
            }
            if(cnt>1){
                flag=true;
            }
        }
        double SpeedSum=0.0;
        for(int i=0;i<6;i++){
            double tmp=1/((double)(horses[i].betOdd+1));
            horses[i].Speed=Math.round(tmp*100.0)/100.0;
            SpeedSum=SpeedSum+horses[i].Speed;

        }

        for(int i=0;i<6;i++){
            double equityTmp=horses[i].Speed/SpeedSum;
           horses[i].equity=Math.round(equityTmp*10000.0)/100.0;
           horse.add((i+1)+"號馬"+"\t 賠率:"+horses[i].betOdd+"\t 綜合能力值:"+horses[i].Speed+"\t 勝率:"+horses[i].equity+"%");
            System.out.println(horses[i].equity);
        }
        horseAdapter.notifyDataSetChanged();




    }
}

class horseInfo{
    int betOdd;
    double Speed;
    double equity;
}