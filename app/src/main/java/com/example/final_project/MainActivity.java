package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private int progresshorse_1 = 0;
    private int progresshorse_2 = 0;
    private int progresshorse_3 = 0;
    private int progresshorse_4 = 0;
    private int progresshorse_5 = 0;
    private int progresshorse_6 = 0;
    private int[] WinnerHorse = new int[3];
    private int Deposit ;
    private int myBetValue;
    private int myBetHorse;
    private int myBetOdd;
    private Boolean intentFlag=false;
    private static int Winnerindex = 0;
    private SeekBar sb_horse_1, sb_horse_2, sb_horse_3, sb_horse_4, sb_horse_5, sb_horse_6;

    private Button btn_start;
    private Button text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer gunsound = MediaPlayer.create(this, R.raw.gun);
        Button platgun = (Button) this.findViewById(R.id.btn_start);
        platgun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gunsound.start();
            }
        });




        btn_start = findViewById(R.id.btn_start);
        sb_horse_1 = findViewById(R.id.sb_horse_1);
        sb_horse_2 = findViewById(R.id.sb_horse_2);
        sb_horse_3 = findViewById(R.id.sb_horse_3);
        sb_horse_4 = findViewById(R.id.sb_horse_4);
        sb_horse_5 = findViewById(R.id.sb_horse_5);
        sb_horse_6 = findViewById(R.id.sb_horse_6);
        Bundle bundle = getIntent().getExtras();
        betInfo myBet = (betInfo) bundle.getSerializable("bet_Info");
        Deposit=myBet.Deposit;
        myBetValue=myBet.betPrice;
        myBetHorse=Integer.parseInt(myBet.betHorse.split("號馬")[0]);
        myBetOdd=myBet.horsesinfo[myBetHorse-1].betOdd;
        System.out.println(myBetOdd);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_start.setEnabled(false);
                progresshorse_1 = 0;
                progresshorse_2 = 0;
                progresshorse_3 = 0;
                progresshorse_4 = 0;
                progresshorse_5 = 0;
                progresshorse_6 = 0;
                Winnerindex=0;
                intentFlag=false;
                sb_horse_1.setProgress(0);
                sb_horse_2.setProgress(0);
                sb_horse_3.setProgress(0);
                sb_horse_4.setProgress(0);
                sb_horse_5.setProgress(0);
                sb_horse_6.setProgress(0);

                double[] equity = new double[myBet.horsesinfo.length];
                double[] Speed = new double[myBet.horsesinfo.length];
                for (int i = 0; i < myBet.horsesinfo.length; i++) {
                    equity[i] = myBet.horsesinfo[i].equity;
                    Speed[i] = myBet.horsesinfo[i].Speed;
                }

                Arrays.sort(equity);
                Integer[] randomWeight = new Integer[myBet.horsesinfo.length];
                for (int i = 0; i < myBet.horsesinfo.length; i++) {
                    for (int j = 0; j < myBet.horsesinfo.length; j++) {
                        if (equity[i] == myBet.horsesinfo[j].equity) {
                            randomWeight[i] = j;
                        }
                    }
                }
                System.out.println();
                runhorse_1(Speed, Arrays.asList(randomWeight).indexOf(0));
                runhorse_2(Speed, Arrays.asList(randomWeight).indexOf(1));
                runhorse_3(Speed, Arrays.asList(randomWeight).indexOf(2));
                runhorse_4(Speed, Arrays.asList(randomWeight).indexOf(3));
                runhorse_5(Speed, Arrays.asList(randomWeight).indexOf(4));
                runhorse_6(Speed, Arrays.asList(randomWeight).indexOf(5));
            }
        });
    }

    private final Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            System.out.println("runnning");
            if(Winnerindex<3){


            if (msg.what == 1) {
                sb_horse_1.setProgress(progresshorse_1);
                if (progresshorse_1 >= 100) {
                    Toast.makeText(MainActivity.this, "馬兒1勝利", Toast.LENGTH_SHORT).show();
                    WinnerHorse[Winnerindex++] = 1;
                    System.out.println(Winnerindex);
                    btn_start.setEnabled(true);
                }
            } else if (msg.what == 2) {
                sb_horse_2.setProgress(progresshorse_2);
                if (progresshorse_2 >= 100 ) {
                    Toast.makeText(MainActivity.this, "馬兒2勝利", Toast.LENGTH_SHORT).show();
                    WinnerHorse[Winnerindex++] = 2;
                    System.out.println(Winnerindex);
                    btn_start.setEnabled(true);
                }
            } else if (msg.what == 3) {
                sb_horse_3.setProgress(progresshorse_3);
                if (progresshorse_3 >= 100) {
                    Toast.makeText(MainActivity.this, "馬兒3勝利", Toast.LENGTH_SHORT).show();
                    WinnerHorse[Winnerindex++] = 3;
                    System.out.println(Winnerindex);
                    btn_start.setEnabled(true);
                }
            } else if (msg.what == 4) {
                sb_horse_4.setProgress(progresshorse_4);
                if (progresshorse_4 >= 100 ) {
                    Toast.makeText(MainActivity.this, "馬兒4勝利", Toast.LENGTH_SHORT).show();
                    WinnerHorse[Winnerindex++] = 4;
                    System.out.println(Winnerindex);
                    btn_start.setEnabled(true);
                }
            } else if (msg.what == 5) {
                sb_horse_5.setProgress(progresshorse_5);
                if (progresshorse_5 >= 100 ) {
                    Toast.makeText(MainActivity.this, "馬兒5勝利", Toast.LENGTH_SHORT).show();
                    WinnerHorse[Winnerindex++] = 5;
                    System.out.println(Winnerindex);
                    btn_start.setEnabled(true);
                }
            } else if (msg.what == 6) {
                sb_horse_6.setProgress(progresshorse_6);
                if (progresshorse_6 >= 100 ) {
                    Toast.makeText(MainActivity.this, "馬兒6勝利", Toast.LENGTH_SHORT).show();
                    WinnerHorse[Winnerindex++] = 6;
                    System.out.println(Winnerindex);
                    btn_start.setEnabled(true);
                }
            }

            }else{
                if(!intentFlag){
                    intent();
                    intentFlag=!intentFlag;
                }

            }
            return false;
        }
    });

    private void runhorse_1(double[] Speed, int randomWeight) {
        int runWeight = Speed.length - randomWeight;
        boolean[] SpeedUpProbability = new boolean[Speed.length];
        for (int i = 0; i < SpeedUpProbability.length; i++) {
            SpeedUpProbability[i] = false;
        }
        for (int i = 0; i < runWeight; i++) {
            SpeedUpProbability[i] = true;
        }
        new Thread(() -> {
            double speed = Speed[0];

            if (Winnerindex < 3) {
                while (progresshorse_1 < 100) {
                    try {
                        Thread.sleep(500);
                        if (SpeedUpProbability[(int) (Math.random() * 6)]) {
                            progresshorse_1 += 2 * speed;
                        } else {
                            progresshorse_1 += speed;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    private void runhorse_2(double[] Speed, int randomWeight) {
        int runWeight = Speed.length - randomWeight;
        boolean[] SpeedUpProbability = new boolean[Speed.length];
        for (int i = 0; i < SpeedUpProbability.length; i++) {
            SpeedUpProbability[i] = false;
        }
        for (int i = 0; i < runWeight; i++) {
            SpeedUpProbability[i] = true;
        }
        new Thread(() -> {


            double speed = Speed[1];
            if (Winnerindex < 3) {
                while (progresshorse_2 < 100) {
                    try {
                        Thread.sleep(500);
                        if (SpeedUpProbability[(int) (Math.random() * 6)]) {
                            progresshorse_2 += 2 * speed;
                        } else {
                            progresshorse_2 += speed;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message msg = new Message();
                    msg.what = 2;
                    handler.sendMessage(msg);
                }
            }

        }).start();
    }

    private void runhorse_3(double[] Speed, int randomWeight) {
        int runWeight = Speed.length - randomWeight;
        boolean[] SpeedUpProbability = new boolean[Speed.length];
        for (int i = 0; i < SpeedUpProbability.length; i++) {
            SpeedUpProbability[i] = false;
        }
        for (int i = 0; i < runWeight; i++) {
            SpeedUpProbability[i] = true;
        }
        new Thread(() -> {
            double speed = Speed[2];
            if (Winnerindex < 3) {
                while (progresshorse_3 < 100 ) {
                    try {
                        Thread.sleep(500);
                        if (SpeedUpProbability[(int) (Math.random() * 6)]) {
                            progresshorse_3 += 2 * speed;
                        } else {
                            progresshorse_3 += speed;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message msg = new Message();
                    msg.what = 3;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    private void runhorse_4(double[] Speed, int randomWeight) {
        int runWeight = Speed.length - randomWeight;
        boolean[] SpeedUpProbability = new boolean[Speed.length];
        for (int i = 0; i < SpeedUpProbability.length; i++) {
            SpeedUpProbability[i] = false;
        }
        for (int i = 0; i < runWeight; i++) {
            SpeedUpProbability[i] = true;
        }
        new Thread(() -> {
            double speed = Speed[3];
            if (Winnerindex < 3) {
                while (progresshorse_4 < 100 ) {
                    try {
                        Thread.sleep(500);
                        if (SpeedUpProbability[(int) (Math.random() * 6)]) {
                            progresshorse_4 += 2 * speed;
                        } else {
                            progresshorse_4 += speed;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message msg = new Message();
                    msg.what = 4;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    private void runhorse_5(double[] Speed, int randomWeight) {
        int runWeight = Speed.length - randomWeight;
        boolean[] SpeedUpProbability = new boolean[Speed.length];
        for (int i = 0; i < SpeedUpProbability.length; i++) {
            SpeedUpProbability[i] = false;
        }
        for (int i = 0; i < runWeight; i++) {
            SpeedUpProbability[i] = true;
        }
        new Thread(() -> {
            double speed = Speed[4];
            if (Winnerindex < 3) {
                while (progresshorse_5 < 100 ) {
                    try {
                        Thread.sleep(500);
                        if (SpeedUpProbability[(int) (Math.random() * 6)]) {
                            progresshorse_5 += 2 * speed;
                        } else {
                            progresshorse_5 += speed;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message msg = new Message();
                    msg.what = 5;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    private void runhorse_6(double[] Speed, int randomWeight) {
        int runWeight = Speed.length - randomWeight;
        boolean[] SpeedUpProbability = new boolean[Speed.length];
        for (int i = 0; i < SpeedUpProbability.length; i++) {
            SpeedUpProbability[i] = false;
        }
        for (int i = 0; i < runWeight; i++) {
            SpeedUpProbability[i] = true;
        }
        new Thread(() -> {


            double speed = Speed[5];
            if (Winnerindex < 3) {
                while (progresshorse_6 < 100 ) {
                    try {
                        Thread.sleep(500);
                        if (SpeedUpProbability[(int) (Math.random() * 6)]) {
                            progresshorse_6 += 2 * speed;
                        } else {
                            progresshorse_6 += speed;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message msg = new Message();
                    msg.what = 6;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    public void intent(){

        Intent intent =new Intent(this,pdActivity.class);
        Bundle bundle =new Bundle();
        bundle.putIntArray("Winners",WinnerHorse);
        bundle.putInt("Deposit",Deposit);
        bundle.putInt("myBetHorse",myBetHorse);
        bundle.putInt("myBetValue",myBetValue);
        bundle.putInt("myBetOdd",myBetOdd);
        intent.putExtras(bundle);
        startActivity(intent);


    }
}