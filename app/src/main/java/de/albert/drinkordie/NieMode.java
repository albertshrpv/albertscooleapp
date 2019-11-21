package de.albert.drinkordie;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NieMode extends AppCompatActivity {

    TextView tv;
    TextView headtv;
    TextView rdtv;

    String randomPlayer;
    String randomPlayer2;
    String randomPlayer3;
    String randomTask;

    boolean isNieMode = true;

    String[] playerArray;

    int roundCounter = 0;

    String randomSipCount;

    String[] playerArray3 = new String[3];


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nie_mode);

        final String[] playerList = getIntent().getStringArrayExtra("playerArray");

        this.playerArray = playerList;

        tv = (TextView) findViewById(R.id.textView);
        headtv = (TextView) findViewById(R.id.textView2);
        rdtv = (TextView) findViewById(R.id.textView5);

        tv.setText("Tippe um anzufangen!");

        findViewById(R.id.nieMode).getRootView().setBackgroundColor(getResources().getColor(R.color.myColorNieMode));


        final List<String> allTasks = getTasks();


        final Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.myColorNieMode));
        }
        else
        {
            window.setStatusBarColor(getResources().getColor(R.color.myColorNieMode));
        }


        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.nieMode);
        layout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                tv.setTextSize(30);
                rdtv.setText("");

                roundCounter++;

                if(roundCounter > 75)
                {
                    openEndscreen();
                    finish();
                    return;
                }

                shuffleArray(playerList);

                randomPlayer = playerList[0];
                randomPlayer2 = playerList[1];
                randomPlayer3 = playerList[2];

                playerArray3[0] = randomPlayer;
                playerArray3[1] = randomPlayer2;
                playerArray3[2] = randomPlayer3;


                int randomNumber = getRandomNumber();
                randomSipCount = getRandomSipCount();

                headtv.setText("");

                int taskNum = new Random().nextInt(allTasks.size());
                randomTask = allTasks.get(taskNum);
                allTasks.remove(taskNum);


                randomTask = String.format(randomTask, randomPlayer, randomPlayer2, randomPlayer3, randomSipCount);

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
                animation.reset();
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.striketvanim);
                animation2.reset();
                tv.clearAnimation();
                tv.startAnimation(animation);
                rdtv.startAnimation(animation2);

                tv.setText(randomTask);
                rdtv.setText(getRandomSipCount() + " Schluck(e)");


            }

        });
    }

    <T> void shuffleArray(T[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            T a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }


    int getRandomNumber()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(100)+1;
        return randomNumber;
    }

    String getRandomSipCount()
    {
        Random random = new Random();
        int randomSipNumber = random.nextInt(3) + 1;
        String randomSipCount = "" + randomSipNumber;
        return randomSipCount;
    }

    ArrayList<String> getTasks()
    {
        Resources res = getResources();
        ArrayList<String> allTasks = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.nie)));
        return allTasks;
    }



    public void openEndscreen()
    {
        Intent intent = new Intent(this, EndScreen.class);
        intent.putExtra("playerArray", playerArray);
        intent.putExtra("NieModeTester", isNieMode);
        startActivity(intent);
    }

}