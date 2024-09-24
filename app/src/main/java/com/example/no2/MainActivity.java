package com.example.no2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int min, sec;
    TextView tv;
    public Thread counterThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.text);
        sec = 0;
        min = 0;


    }

    public void startCount(View view) {
        counterThread = new Thread(() -> {
            try {
                while (true) {
                    if (sec == 59) {
                        sec = 0;
                        min++;
                        if(min>=10&&sec>=10){
                            tv.setText(min+":"+sec);
                        }
                        if(min<10&&sec>=10){
                            tv.setText("0"+min+":"+sec);
                        }
                        if(min<10&&sec<10){
                            tv.setText("0"+min+":"+"0"+sec);
                        }
                        if(min>=10&&sec<10){
                            tv.setText(min+":"+"0"+sec);
                        }
                        Thread.sleep(1000);
                    } else {
                        sec++;
                        if(min>=10&&sec>=10){
                            tv.setText(min+":"+sec);
                        }
                        if(min<10&&sec>=10){
                            tv.setText("0"+min+":"+sec);
                        }
                        if(min<10&&sec<10){
                            tv.setText("0"+min+":"+"0"+sec);
                        }
                        if(min>=10&&sec<10){
                            tv.setText(min+":"+"0"+sec);
                        }
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {

            }
        });
        counterThread.start();
    }

    public void stop(View view) {
        counterThread.interrupt();
    }

    public void reset(View view){
        counterThread.interrupt();
        min=0;
        sec=0;
        tv.setText("00:00");


    }
}
