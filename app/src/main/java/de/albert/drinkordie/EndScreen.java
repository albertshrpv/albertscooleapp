package de.albert.drinkordie;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {

    TextView tv;
    Button bt;
    TextView title;
    Button btplayers;

    boolean isHardcore;
    boolean isNieMode;

    String[] playerArray;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        this.playerArray = getIntent().getStringArrayExtra("playerArray");
        this.isHardcore = getIntent().getBooleanExtra("hardcoreTester", isHardcore);
        this.isNieMode = getIntent().getBooleanExtra("NieModeTester", isNieMode);


        tv = (TextView) findViewById(R.id.textView4);
        bt = (Button) findViewById(R.id.button_play_again);
        title = (TextView) findViewById(R.id.headline1);
        btplayers = (Button) findViewById(R.id.button_change_players);


        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.myColor));
        }
        else
        {
            window.setStatusBarColor(getResources().getColor(R.color.myColor));
        }

        startButtonAnimation();
        startTitleAnimation();

        Animation texttv = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shotanimtv);
        tv.clearAnimation();
        tv.startAnimation(texttv);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isHardcore)
                {
                    openHardcoreMode();
                    finish();
                }
                else if(isNieMode)
                {
                    openNieMode();
                    finish();
                }
                else
                {
                    openActivity2();
                    finish();
                }
            }
        });

        btplayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }

    public void startTitleAnimation() {

        final TextView tv = findViewById(R.id.headlineEnd);
        tv.setVisibility(View.VISIBLE);


        Animation headZoom = AnimationUtils.loadAnimation(EndScreen.this, R.anim.title);
        headZoom.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                Animation headZoom = AnimationUtils.loadAnimation(EndScreen.this, R.anim.title);
                headZoom.setAnimationListener(this);
                tv.startAnimation(headZoom);

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

        });


        tv.startAnimation(headZoom);
    }

    public void startButtonAnimation() {
        final Button bt = findViewById(R.id.button_play_again);
        bt.setVisibility(View.VISIBLE);

        Animation buttonZoom = AnimationUtils.loadAnimation(EndScreen.this, R.anim.playbuttonanim);
        buttonZoom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation buttonZoom = AnimationUtils.loadAnimation(EndScreen.this, R.anim.playbuttonanim);
                buttonZoom.setAnimationListener(this);
                bt.startAnimation(buttonZoom);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bt.startAnimation(buttonZoom);

    }

    public void openActivity2()
    {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("playerArray", playerArray);
        startActivity(intent);
    }

    public void openHardcoreMode()
    {
        Intent intent = new Intent(this, hardcoreMode.class);
        intent.putExtra("playerArray", playerArray);
        startActivity(intent);
    }

    public void openNieMode()
    {
        Intent intent = new Intent(this, NieMode.class);
        intent.putExtra("playerArray", playerArray);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();

        finish();
    }
}
