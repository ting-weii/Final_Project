package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
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
        betInfo myBet=new betInfo();
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
            horses[i].Speed=(Math.round(tmp*100.0)/100.0)*2;
            SpeedSum=SpeedSum+horses[i].Speed;

        }
        for(int i=0;i<6;i++){
            double equityTmp=horses[i].Speed/SpeedSum;
           horses[i].equity=Math.round(equityTmp*10000.0)/100.0;
           horse.add((i+1)+"號馬"+"\t 賠率:"+horses[i].betOdd+"\t 綜合能力值:"+horses[i].Speed+"\t 勝率:"+horses[i].equity+"%");
            System.out.println(horses[i].equity);
        }
        horseAdapter.notifyDataSetChanged();
        myBet.horsesinfo=horses;
        String myDepositStr=tv_deposit.getText().toString();
        int Deposit=Integer.parseInt(myDepositStr.substring(myDepositStr.indexOf(":")+1,myDepositStr.length()-1));
        myBet.Deposit=Deposit;
        System.out.println(Deposit);

        horseChoose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedHorse="";
                selectedHorse=horseChoose.getSelectedItem().toString();
                myBet.betHorse=selectedHorse;
                System.out.println(selectedHorse);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        btn_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed_betPrice.getText().toString()==""){
                    Toast.makeText(betActivity.this,"請輸入數值",Toast.LENGTH_SHORT).show();
                }else{
                    myBet.betPrice=Integer.parseInt(ed_betPrice.getText().toString());

                    System.out.println("存款="+myBet.Deposit+"下注金額="+myBet.betPrice+"選擇的馬"+myBet.betHorse);
                    Bundle Bundle_bet=new Bundle();
                    Bundle_bet.putSerializable("bet_Info",myBet);
                    Intent  Intent_bet=new Intent();//(this,)
                    Intent_bet.putExtra("Bet",Bundle_bet);
                    startActivity(Intent_bet);

                }
            }
        });
    }
}

class betInfo  implements Serializable {
    horseInfo[] horsesinfo;
    String betHorse;
    int Deposit;
    int betPrice;
}


class horseInfo{
    int betOdd;
    double Speed;
    double equity;
}