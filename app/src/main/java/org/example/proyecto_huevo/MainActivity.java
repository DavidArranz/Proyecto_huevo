package org.example.proyecto_huevo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvTimer,tvDone;
    RadioButton rb90sec,rb4min,rb9min,rb3min;
    RadioGroup rg;
    ImageView ivHuevo;
    static CountDownTimer timer;
    boolean timerRunning = false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTimer = findViewById(R.id.textViewTimer);
        ivHuevo = findViewById(R.id.imageViewHuevo);
        rb3min = findViewById(R.id.radioButton3min);
        rb4min = findViewById(R.id.radioButton4min);
        rb9min = findViewById(R.id.radioButton9min);
        rb90sec = findViewById(R.id.radioButton90seg);
        rg = findViewById(R.id.radioGroup);
        tvDone=findViewById(R.id.textViewDone);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int id90 = rb90sec.getId(), id3 = rb3min.getId(), id4 = rb4min.getId(), id9 = rb9min.getId();
                int rb = rg.getCheckedRadioButtonId();
                long milis = 0;
                ivHuevo.setVisibility(View.VISIBLE);
                if (id90 == rb) {
                    milis = 90000;
                    ivHuevo.setImageResource(R.drawable.b_pocfet);
                } else if (id3 == rb) {
                milis = 3 * 60 * 1000;
                    ivHuevo.setImageResource(R.drawable.a_huevofrito);
                }else if(id4 == rb) {
                    milis = 4 * 60 * 1000;
                    ivHuevo.setImageResource(R.drawable.a_huevo_mollet);
                }else if(id9==rb){
                    milis = 9*60*1000;
                    ivHuevo.setImageResource(R.drawable.a_huevoduro);
                }
                stopTimer();
                startTimer(milis);

            }
        });


    }
    public void startTimer(long milis){
        timerRunning = true;
        timer = new CountDownTimer(milis, 1000) {

            public void onTick(long millisUntilFinished) {
                long minutes = millisUntilFinished / 60000;
                long seconds = (millisUntilFinished % 60000) / 1000;
                tvTimer.setText(String.format("%02d:%02d", minutes, seconds));                    }

            public void onFinish() {
                tvDone.setText("¡Ya esta echo!");
            }
        }.start();
    }
    private void stopTimer() {
        if (timerRunning) {
            timer.cancel();
            timerRunning = false;
        }
    }
}