package de.albert.drinkordie;

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

public class SelectGameMode extends AppCompatActivity {

    String[] playerArray;

    Button normalButton;
    Button hardcoreButton;
    Button nieButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game_mode);

        normalButton = (Button) findViewById(R.id.Button_normal);
        hardcoreButton = (Button) findViewById(R.id.Button_hardcore);
        nieButton = (Button) findViewById(R.id.Button_nie);


        startHardcoreButtonAnimation();
        startNormalButtonAnimation();
        startNieButtonAnimation();
        startTitleAnimation();

        final String[] playerList = getIntent().getStringArrayExtra("playerArray");
        this.playerArray = playerList;

        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
                finish();
            }
        });

        hardcoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHardcoreMode();
                finish();
            }
        });

        nieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNieMode();
                finish();
            }
        });

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.myColor));
        }
        else
        {
            window.setStatusBarColor(getResources().getColor(R.color.myColor));
        }


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

    public void startTitleAnimation() {

        final TextView headline = findViewById(R.id.headline1);
        headline.setVisibility(View.VISIBLE);


        Animation headZoom = AnimationUtils.loadAnimation(SelectGameMode.this, R.anim.title);
        headZoom.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                Animation headZoom = AnimationUtils.loadAnimation(SelectGameMode.this, R.anim.title);
                headZoom.setAnimationListener(this);
                headline.startAnimation(headZoom);

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
        headline.startAnimation(headZoom);
    }

    public void startNormalButtonAnimation() {
        final Button playB = findViewById(R.id.Button_normal);
        playB.setVisibility(View.VISIBLE);

        Animation buttonZoom = AnimationUtils.loadAnimation(SelectGameMode.this, R.anim.playbuttonanim);
        buttonZoom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation buttonZoom = AnimationUtils.loadAnimation(SelectGameMode.this, R.anim.playbuttonanim);
                buttonZoom.setAnimationListener(this);
                playB.startAnimation(buttonZoom);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        playB.startAnimation(buttonZoom);

    }

    public void startHardcoreButtonAnimation() {
        final Button playB = findViewById(R.id.Button_hardcore);
        playB.setVisibility(View.VISIBLE);

        Animation buttonZoom = AnimationUtils.loadAnimation(SelectGameMode.this, R.anim.playbuttonanim);
        buttonZoom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation buttonZoom = AnimationUtils.loadAnimation(SelectGameMode.this, R.anim.playbuttonanim);
                buttonZoom.setAnimationListener(this);
                playB.startAnimation(buttonZoom);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        playB.startAnimation(buttonZoom);

    }

    public void startNieButtonAnimation() {
        final Button playB = findViewById(R.id.Button_nie);
        playB.setVisibility(View.VISIBLE);

        Animation buttonZoom = AnimationUtils.loadAnimation(SelectGameMode.this, R.anim.playbuttonanim);
        buttonZoom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation buttonZoom = AnimationUtils.loadAnimation(SelectGameMode.this, R.anim.playbuttonanim);
                buttonZoom.setAnimationListener(this);
                playB.startAnimation(buttonZoom);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        playB.startAnimation(buttonZoom);

    }
}
