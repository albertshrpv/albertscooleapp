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

public class MainActivity2 extends AppCompatActivity {

    TextView tv;
    TextView headtv;
    TextView rdtv;

    String randomPlayer;
    String randomPlayer2;
    String randomPlayer3;
    String randomTask;

    String[] playerArray;

    int roundCounter = 0;
    int quizOrNotTester = 25;

    List<Strike> strikeList = new ArrayList<>();
    List<Quiz> quizList = new ArrayList<>();

    String randomSipCount;

    String[] playerArray3 = new String[3];


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final String[] playerList = getIntent().getStringArrayExtra("playerArray");

        this.playerArray = playerList;

        tv = (TextView) findViewById(R.id.textView);
        headtv = (TextView) findViewById(R.id.textView2);
        rdtv = (TextView) findViewById(R.id.textView5);

        tv.setText("Tippe um anzufangen!");

        final Window window = getWindow();

        findViewById(R.id.mainLayout2).getRootView().setBackgroundColor(getResources().getColor(R.color.myColor));


        final ArrayList<List<String>> allTasks = getTasks();


        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.mainLayout2);
        layout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v)
            {

                tv.setTextSize(30);
                rdtv.setText("");

                roundCounter++;

                if( roundCounter > 75 && quizList.size() == 0) //hinzufügen, dass erst schließt, wenn strikeList.size() == 0. Sonst erst Strikes auflösen, einfach if statement imlementieren
                {
                    if(strikeList.size() == 0)
                    {
                        openEndscreen();
                        finish();
                        return;
                    }
                    else
                    {
                        Strike strike = strikeList.get(0);
                        strikeList.remove(0);
                        headtv.setText("");
                        tv.setText(strike.getSolution());

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            window.setStatusBarColor(getColor(R.color.myColorStrike));
                        }
                        else
                        {
                            window.setStatusBarColor(getResources().getColor(R.color.myColorStrike));
                        }
                        findViewById(R.id.mainLayout2).getRootView().setBackgroundColor(getResources().getColor(R.color.myColorStrike));

                        Animation striketv = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.striketvanim);
                        striketv.reset();
                        tv.clearAnimation();
                        tv.startAnimation(striketv);
                    }
                    return;

                }

                int strikeIndex = -1;
                boolean strikeReady = false;

                int quizIndex = -1;
                boolean quizReady = false;

                for(int i = 0; i < quizList.size(); i++)
                {
                    if(quizList.get(i).round())
                    {
                        quizReady = true;
                        quizIndex = i;
                        break;
                    }
                }

                if(quizReady)
                {
                    Quiz quiz = quizList.get(quizIndex);
                    quizList.remove(quizIndex);


                    headtv.setText("");
                    tv.setText(quiz.getQuizSolution());


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        window.setStatusBarColor(getColor(R.color.myColorQuiz));
                    }
                    else
                    {
                        window.setStatusBarColor(getResources().getColor(R.color.myColorQuiz));
                    }
                    findViewById(R.id.mainLayout2).getRootView().setBackgroundColor(getResources().getColor(R.color.myColorQuiz));

                    Animation quizText = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.headanim);
                    quizText.reset();
                    headtv.clearAnimation();
                    headtv.startAnimation(quizText);

                    Animation quiztv = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.textanimgame);
                    quiztv.reset();
                    tv.clearAnimation();
                    tv.startAnimation(quiztv);
                    return;
                }

                for(int i = 0; i < strikeList.size(); i++)
                {
                    if(strikeList.get(i).round())
                    {
                        strikeReady = true;
                        strikeIndex = i;
                        break;
                    }
                }

                if(strikeReady)
                {
                    Strike strike = strikeList.get(strikeIndex);
                    strikeList.remove(strikeIndex);

                    headtv.setText("");
                    tv.setText(strike.getSolution());


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        window.setStatusBarColor(getColor(R.color.myColorStrike));
                    }
                    else
                    {
                        window.setStatusBarColor(getResources().getColor(R.color.myColorStrike));
                    }
                    findViewById(R.id.mainLayout2).getRootView().setBackgroundColor(getResources().getColor(R.color.myColorStrike));

                    Animation strikeText = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.strikeanim);
                    strikeText.reset();
                    headtv.clearAnimation();
                    headtv.startAnimation(strikeText);

                    Animation striketv = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.striketvanim);
                    striketv.reset();
                    tv.clearAnimation();
                    tv.startAnimation(striketv);
                    return;
                }



                int randomNumber = getRandomNumber();
                randomSipCount = getRandomSipCount();

                shuffleArray(playerList);

                randomPlayer = playerList[0];
                randomPlayer2 = playerList[1];
                randomPlayer3 = playerList[2];

                playerArray3[0] = randomPlayer;
                playerArray3[1] = randomPlayer2;
                playerArray3[2] = randomPlayer3;




                if(randomNumber < 17)
                {
                    int gameNum = new Random().nextInt(allTasks.get(1).size());
                    randomTask = allTasks.get(1).get(gameNum);
                    headtv.setText("SPIEL");

                    allTasks.get(1).remove(gameNum);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        window.setStatusBarColor(getColor(R.color.myColorGame));
                    }
                    else
                    {
                        window.setStatusBarColor(getResources().getColor(R.color.myColorGame));
                    }
                    findViewById(R.id.mainLayout2).getRootView().setBackgroundColor(getResources().getColor(R.color.myColorGame));

                    Animation gameText = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.headanim);
                    gameText.reset();
                    headtv.clearAnimation();
                    headtv.startAnimation(gameText);

                    Animation gametv = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.textanimgame);
                    gametv.reset();
                    tv.clearAnimation();
                    tv.startAnimation(gametv);
                    randomTask = String.format(randomTask, randomPlayer, randomPlayer2, randomPlayer3, randomSipCount);
                    tv.setText(randomTask);



                }
                else if(allTasks.get(2).size() > 0 && randomNumber < 22)
                {
                    int strikeNum = new Random().nextInt(allTasks.get(2).size());
                    while(strikeListHasIndex(strikeNum))
                    {
                        strikeNum = new Random().nextInt(allTasks.get(2).size());
                    }
                    Strike strike = new Strike(allTasks.get(2).get(strikeNum), allTasks.get(3).get(strikeNum), playerArray3, strikeNum );
                    strikeList.add(strike);

                    tv.setText(strike.getStrike());
                    headtv.setText("STRIKE");

                    allTasks.get(2).remove(strikeNum);
                    allTasks.get(3).remove(strikeNum);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        window.setStatusBarColor(getColor(R.color.myColorStrike));
                    }
                    else
                    {
                        window.setStatusBarColor(getResources().getColor(R.color.myColorStrike));
                    }
                    findViewById(R.id.mainLayout2).getRootView().setBackgroundColor(getResources().getColor(R.color.myColorStrike));

                    Animation strikeText = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.strikeanim);
                    strikeText.reset();
                    headtv.clearAnimation();
                    headtv.startAnimation(strikeText);

                    Animation striketv = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.striketvanim);
                    striketv.reset();
                    tv.clearAnimation();
                    tv.startAnimation(striketv);

                }
                else if(allTasks.get(5).size() > 0 && randomNumber < 27)
                {
                    int quizNum = new Random().nextInt(allTasks.get(5).size());
                    while(quizListHasIndex(quizNum))
                    {
                        quizNum = new Random().nextInt(allTasks.get(5).size());
                    }
                    Quiz quiz = new Quiz(allTasks.get(5).get(quizNum), allTasks.get(6).get(quizNum), playerArray3, quizNum );
                    quizList.add(quiz);

                    tv.setText(quiz.getQuiz());
                    headtv.setText("Spiel");
                    if(quizNum < quizOrNotTester)
                    {
                        headtv.setText("Quiz");
                        rdtv.setText("Schätzfrage");
                        quizOrNotTester--;
                    }

                    allTasks.get(5).remove(quizNum);
                    allTasks.get(6).remove(quizNum);


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        window.setStatusBarColor(getColor(R.color.myColorQuiz));
                    }
                    else
                    {
                        window.setStatusBarColor(getResources().getColor(R.color.myColorQuiz));
                    }
                    findViewById(R.id.mainLayout2).getRootView().setBackgroundColor(getResources().getColor(R.color.myColorQuiz));

                    Animation quizText = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.strikeanim);
                    quizText.reset();
                    headtv.clearAnimation();
                    headtv.startAnimation(quizText);

                    Animation quiztv = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.striketvanim);
                    quiztv.reset();
                    tv.setTextSize(29);
                    tv.clearAnimation();
                    tv.startAnimation(quiztv);
                    rdtv.clearAnimation();
                    rdtv.startAnimation(quiztv);


                }
                else if(allTasks.get(7).size() > 0 &&randomNumber < 31)
                {
                    int duellNum = new Random().nextInt(allTasks.get(7).size());
                    randomTask = allTasks.get(7).get(duellNum);
                    headtv.setText("DUELL");

                    allTasks.get(7).remove(duellNum);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        window.setStatusBarColor(getColor(R.color.myColorDuell));
                    }
                    else
                    {
                        window.setStatusBarColor(getResources().getColor(R.color.myColorDuell));
                    }
                    findViewById(R.id.mainLayout2).getRootView().setBackgroundColor(getResources().getColor(R.color.myColorDuell));

                    Animation duellText = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.duellanim);
                    duellText.reset();
                    headtv.clearAnimation();
                    headtv.startAnimation(duellText);

                    Animation duelltv = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.duallanimtv);
                    duelltv.reset();
                    tv.clearAnimation();
                    tv.startAnimation(duelltv);
                    randomTask = String.format(randomTask, randomPlayer, randomPlayer2, randomPlayer3, randomSipCount);
                    tv.setText(randomTask);
                }

                else if(allTasks.get(4).size() > 0 && randomNumber < 36)
                {
                    int shotNum = new Random().nextInt(allTasks.get(4).size());
                    randomTask = allTasks.get(4).get(shotNum);
                    headtv.setText("SHOT");

                    allTasks.get(4).remove(shotNum);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        window.setStatusBarColor(getColor(R.color.myColorShot));
                    }
                    else
                    {
                        window.setStatusBarColor(getResources().getColor(R.color.myColorShot));
                    }
                    findViewById(R.id.mainLayout2).getRootView().setBackgroundColor(getResources().getColor(R.color.myColorShot));

                    Animation shotText = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.strikeanim);
                    shotText.reset();
                    headtv.clearAnimation();
                    headtv.startAnimation(shotText);

                    Animation shottv = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shotanimtv);
                    shottv.reset();
                    tv.clearAnimation();
                    tv.startAnimation(shottv);
                    randomTask = String.format(randomTask, randomPlayer, randomPlayer2, randomPlayer3, randomSipCount);
                    tv.setText(randomTask);
                }
                else
                {
                    int taskNum = new Random().nextInt(allTasks.get(0).size());
                    randomTask = allTasks.get(0).get(taskNum);
                    headtv.setText("");
                    allTasks.get(0).remove(taskNum);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        window.setStatusBarColor(getColor(R.color.myColor));
                    }
                    else
                    {
                        window.setStatusBarColor(getResources().getColor(R.color.myColor));
                    }
                    findViewById(R.id.mainLayout2).getRootView().setBackgroundColor(getResources().getColor(R.color.myColor));

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
                    animation.reset();
                    tv.clearAnimation();
                    tv.startAnimation(animation);
                    randomTask = String.format(randomTask, randomPlayer, randomPlayer2, randomPlayer3, randomSipCount);
                    tv.setText(randomTask);
                }



            }


        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.myColor));
        }
        else
        {
            window.setStatusBarColor(getResources().getColor(R.color.myColor));
        }

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

    ArrayList<List<String>> getTasks()
    {
        Resources res = getResources();
        ArrayList<List<String>> allTasks = new ArrayList<List<String>>();
        allTasks.add(new ArrayList<>(Arrays.asList(res.getStringArray(R.array.simpleTasks))));
        allTasks.add(new ArrayList<>(Arrays.asList(res.getStringArray(R.array.games))));
        allTasks.add(new ArrayList<>(Arrays.asList(res.getStringArray(R.array.viruses))));
        allTasks.add(new ArrayList<>(Arrays.asList(res.getStringArray(R.array.solutions))));
        allTasks.add(new ArrayList<>(Arrays.asList(res.getStringArray(R.array.shot))));
        allTasks.add(new ArrayList<>(Arrays.asList(res.getStringArray(R.array.quiz))));
        allTasks.add(new ArrayList<>(Arrays.asList(res.getStringArray(R.array.quizSolution))));
        allTasks.add(new ArrayList<>(Arrays.asList(res.getStringArray(R.array.duell))));
        return allTasks;
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
        int randomSipNumber = random.nextInt(4) + 2;
        String randomSipCount = "" + randomSipNumber;
        return randomSipCount;
    }

    boolean strikeListHasIndex(int index)
    {
        for(Strike strike : strikeList)
        {
            if(strike.getIndex() == index)
            {
                return true;
            }
        }
        return false;
    }

    boolean quizListHasIndex(int index)
    {
        for(Quiz quiz: quizList)
        {
            if(quiz.getQuizIndex() == index)
            {
                return true;
            }
        }
        return false;
    }

    public void openEndscreen()
    {
        Intent intent = new Intent(this, EndScreen.class);
        intent.putExtra("playerArray", playerArray);
        startActivity(intent);
    }

}
